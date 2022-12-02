public class Main {
    public static void main(String[] args) throws Exception {
        Nodo n2=new Nodo("2");
        Nodo n3=new Nodo("3");
        Nodo n1=new Nodo("1", n2, n3);
        Nodo n4=new Nodo("4");
        Nodo n5=new Nodo("5");
        Nodo n6=new Nodo("6");

        ExpAritmetica arbol=new ExpAritmetica();
        arbol.setRoot((n1));
        arbol.add(n2, n4, 0);
        arbol.add(n2, n5, 1);
        arbol.add(n3, n6, 1);

        arbol.postorden();

    }
}
