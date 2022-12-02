public class Nodo {
    String valor;
    Nodo izq = null;
    Nodo der = null;

    public Nodo() {
        izq = der = null;
    }

    public Nodo(String data) {
        this(data, null, null);
    }

    public Nodo(String data, Nodo lt, Nodo rt) {
        valor = data;
        izq = lt;
        der = rt;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
}
