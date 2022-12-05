package avl;

public class Nodo {
    int valor, factor;
    Nodo izq = null;
    Nodo der = null;
    Nodo padre = null;
    
    public Nodo(){
        izq = der = null;
        factor = 0;
    }
    
    public Nodo(int data, Nodo lt, Nodo rt){
        valor = data;
        izq = lt;
        der = rt;
    }
    
    public Nodo(int data){
        this(data, null, null);   
    } 
}
