public class Main {
    static ExpAritmetica exp= new ExpAritmetica();
    static AVL avl= new AVL();
    public static void main(String[] args) throws Exception {
        int opcion = -1;
        while (opcion != 0) {
            exp.menuExp();
            avl.menuAVL();
        }
    }
}
