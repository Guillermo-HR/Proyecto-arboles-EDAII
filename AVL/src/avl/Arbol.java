
package avl;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol {
    Nodo root;
    
    //Constructores
    public Arbol(){}
    
    public Arbol(Nodo r){
        this.root = r;
    }
    
    public Arbol(int val){
        root = new Nodo(val);
    }
    
    //Insercion
    public Nodo insercionAVL(Nodo n, Nodo padre){
        Nodo copia = padre;
        if(n.valor < padre.valor){
            if(padre.izq == null){
                padre.izq = n;
            }
            else{
                padre.izq = insercionAVL(n, padre.izq);
                if((retornarFactor(padre.izq) - retornarFactor(padre.der)) == 2 ){
                    if(n.valor<padre.izq.valor){
                        copia = rIzq(padre);
                    }
                    else{
                        copia = rDIzq(padre);
                    }
                }
            }
            
        }
        else
            if(n.valor > padre.valor){
                if(padre.der == null){
                    padre.der = n;
                }
                else{
                    padre.der = insercionAVL(n, padre.der);
                    if((retornarFactor(padre.der) - retornarFactor(padre.izq)) == 2){
                        if(n.valor > padre.der.valor){
                            copia = rDer(padre);
                        }
                        else{
                            copia = rDDer(padre);
                        }
                    }
                }
            }
            else{
                System.out.println("Nodo duplicado");
            }
            
            if(padre.izq == null && padre.der!= null){ //Actualizar factor
                padre.factor = padre.der.factor +1;
            }
            else
                if(padre.der == null && padre.izq!=null){
                    padre.factor = padre.izq.factor +1;
                }
                else{
                    padre.factor = Math.max(retornarFactor(padre.izq),retornarFactor(padre.der) )+1;
                }
            n.padre = copia;
            return copia;
    }
    
    public void insertar(int n){
        Nodo nuevo = new Nodo(n);
        if(root == null){
            root = nuevo;
        }
        else{
            root = insercionAVL(nuevo, root);
        }
    }
    
    
    //Busqueda
    /*public boolean busqueda(int val, Nodo referencia){
        if(root == null){
            System.out.println("El arbol se encuentra vacio");
            return false;
        }
        else{
            if(referencia.valor == val){
                System.out.println("El valor se encuentra dentro de el nodo "+ referencia);
                return true;
            }
            else{
                if(val<referencia.valor){
                    busqueda(val, referencia.izq);
                }
                else{
                    busqueda(val, referencia.der);
                }
            }
        }
        System.out.println("No se pudo encontrar el nodo");
        return false;
    }*/
    public Nodo busqueda(int n){
    Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (Nodo) queue.poll();
                if (r.valor == n){
                    System.out.println("Valor encontrado");
                    return r;
                }
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
        System.out.println("No fue posible encontrar un nodo con ese valor");
        return null;}
    //Eliminacion
    public void eliminacion(int e){
        if(root == null){
            System.out.println("El nodo se encuentra vacio");
        }
        else{
            Nodo aux = root;
            Nodo padre = root;
            boolean izq = true; //hijo izquierdo -> true hijo derecho -> false
            while(aux.valor != e){
                padre = aux;
                if(e < aux.valor){
                    izq = true;
                    aux = aux.izq;
                }
                else{
                    izq = false;
                    aux = aux.der;
                }
                if(aux == null){
                    System.out.println("No se encontró el nodo");
                    return;
                }
            }
            if(aux.izq == null && aux.der == null){
                if(aux == root)
                    root = null;
                else
                    if(izq){
                        padre.izq = null;
                    }
                    else{
                        padre.der = null;
                    }
            }
            else if(aux.der == null){
                if(aux == root)
                    root = aux.izq;
                else
                    if(izq){
                        padre.izq = aux.izq;
                    }
                    else{
                        padre.der = aux.izq;
                    }
            }
            else if(aux.izq == null){
                if(aux == root)
                    root = aux.der;
                else 
                    if(izq){
                        padre.izq = aux.der;
                    }
                    else{
                        padre.der = aux.der;
                    }
            }
            else{
                Nodo reemplazo = obtenerReemplazo(aux);
                if(aux == root){
                    root = reemplazo;  
                }
                else if(izq){
                    padre.izq = reemplazo;
                }
                else{
                    padre.der = reemplazo;
                }
                reemplazo.izq = aux.izq;
            }
        }     
    }
    
    public Nodo obtenerReemplazo(Nodo aux){
        Nodo rpadre = aux;
        Nodo reemplazo = aux;
        Nodo auxiliar = aux.der;
        while(auxiliar != null){ 
            rpadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.izq;
            }
        if(reemplazo != aux.der){
            rpadre.izq = reemplazo.der;
            reemplazo.der = aux.der;
        }
        return reemplazo;
    }
    
    //Impresion
    public void visit(Nodo n){
        System.out.println(n.valor + " ");
}
    
    public void recorridoExpansion(){
        System.out.println("Recorrido:");
        Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (Nodo) queue.poll();
                visit(r);
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
    }
    
    
    //Obtener factor
    public int retornarFactor(Nodo evaluado){
        if(evaluado == null){
            return -1;
        }
        else
            return evaluado.factor;
    }
    
    //Rotaciones
    public Nodo rIzq(Nodo n){ //rotacion por izquierda simple
        Nodo tmp = n.izq;
        n.izq = tmp.der;
        tmp.der = n;
        n.factor = Math.max(retornarFactor(n.izq),retornarFactor(n.der))+1 ;
        tmp.factor = Math.max(retornarFactor(tmp.izq),retornarFactor(tmp.der))+1 ;
        return tmp;
    }
    
    public Nodo rDer(Nodo n){ //rotacion por derecha simple
        Nodo tmp = n.der;
        n.der = tmp.izq;
        tmp.izq = n;
        n.factor = Math.max(retornarFactor(n.izq),retornarFactor(n.der))+1 ;
        tmp.factor = Math.max(retornarFactor(tmp.izq),retornarFactor(tmp.der))+1 ;
        return tmp;      
    }
    
    public Nodo rDIzq(Nodo n){ //Izquierda doble
        Nodo tmp;
        n.izq = rDer(n.izq);
        tmp = rIzq(n);
        return tmp;
    }
    
    public Nodo rDDer(Nodo n){ //Derecha doble
        Nodo tmp;
        n.der = rIzq(n.der);
        tmp = rDer(n);
        return tmp;
    }
    
}