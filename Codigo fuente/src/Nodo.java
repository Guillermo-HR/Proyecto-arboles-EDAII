public class Nodo {
    String valorS;
    int valorI, factor;
    Nodo izq = null;
    Nodo der = null;
    Nodo padre = null;

    public Nodo() {
        izq = der = null;
        factor=0;
    }

    public Nodo(String data) {
        this(data, null, null);
    }

    public Nodo(int data) {
        this(data, null, null);
    }

    public Nodo(String data, Nodo lt, Nodo rt) {
        valorS = data;
        izq = lt;
        der = rt;
    }

    public Nodo(int data, Nodo lt, Nodo rt) {
        valorI = data;
        izq = lt;
        der = rt;
    }

    /**
     * @param izq
     */
    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    /**
     * @param der
     */
    public void setDer(Nodo der) {
        this.der = der;
    }
}
