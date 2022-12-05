import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArbolBin {
    Nodo root;

    public ArbolBin() {
        root = null;
    }

    /**
     * @param padre
     */
    public void setRoot(Nodo padre) {
        root = padre;
    }

    /**
     * @param padre
     * @param hijo
     * @param lado
     */
    public void add(Nodo padre, Nodo hijo, int lado) {
        if (lado == 0)
            padre.setIzq(hijo);
        else
            padre.setDer(hijo);
    }

    /**
     * @param n
     */
    public void visit(Nodo n) {
        System.out.println(n.valorS + " ");
    }

    public void breadthFrist() {
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

    /**
     * @param dato
     */
    public void eliminarNodo(String dato) {
        Nodo nodo = buscarNodo(dato);
        if (nodo == null) {
            System.out.println("No existe un nodo con valor: " + dato);
            return;
        }
        if (nodo.izq == null && nodo.der == null) {
            nodo = buscarPadre(nodo.valorS);
            if (nodo.izq != null && nodo.izq.valorS == dato)
                nodo.izq = null;
            else
                nodo.der = null;
            System.out.println("Se elimino el nodo: " + dato);
            return;
        }
        Nodo sustituto = nodo.izq;
        if (sustituto == null) {
            sustituto = nodo.der;
            while (sustituto.izq != null)
                sustituto = sustituto.izq;
        } else {
            while (sustituto.der != null)
                sustituto = sustituto.der;
        }
        Nodo padre = buscarPadre(sustituto.valorS);
        nodo.valorS = sustituto.valorS;
        if (padre.izq != null && padre.izq.valorS == nodo.valorS)
            padre.izq = null;
        else
            padre.der = null;
        System.out.println("Se elimino el nodo: " + dato);
    }

    /**
     * @param n
     * @return Nodo
     */
    public Nodo buscarPadre(String n) {
        Nodo r = root;
        if (r.valorS == n)
            return r;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (Nodo) queue.poll();
                if ((r.izq != null && r.izq.valorS == n) || (r.der != null && r.der.valorS == n))
                    return r;
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
        return null;
    }

    /**
     * @param n
     * @return Nodo
     */
    public Nodo buscarNodo(String n) {
        Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (Nodo) queue.poll();
                if (r.valorS == n)
                    return r;
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
        return null;
    }

    public void postorden() {
        Nodo actual = root;
        Stack<Nodo> recorridoInverso = new Stack<Nodo>();
        Stack<Nodo> padres = new Stack<Nodo>();
        while (actual != null) {
            recorridoInverso.push(actual);
            if (actual.der != null) {
                padres.push(actual);
                actual = actual.der;
            } else if (actual.izq != null) {
                actual = actual.izq;
            } else {
                try {
                    actual = padres.pop().izq;
                    while (actual == null && !padres.isEmpty())
                        actual = padres.pop().izq;
                } catch (EmptyStackException e) {
                    actual = null;
                }
            }
        }
        while (!recorridoInverso.isEmpty()) {
            visit(recorridoInverso.pop());
        }
    }

    /**
     * @param n
     * @return boolean
     */
    public boolean buscar(String n) {
        Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (Nodo) queue.poll();
                if (r.valorS == n)
                    return true;
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
        return false;
    }
}
