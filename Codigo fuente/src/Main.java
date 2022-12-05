import java.util.Scanner;
public class Main {
    static ExpAritmetica exp= new ExpAritmetica();
    static AVL avl= new AVL();
    public static void main(String[] args) throws Exception {
        int opcion;
        Scanner sc= new Scanner(System.in);
        do{
            try {
                System.out.println("Menu general");
                System.out.println("1. Expresion aritmetica");
                System.out.println("2. Arbol AVL");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                opcion= sc.nextInt();
                switch (opcion) {
                case 1:
                    exp.menuExp();
                    break;
                case 2:
                    avl.menuAVL();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Opcion no valida");
                opcion=-1;
            }
        }while(opcion!=0);
        System.out.println("Fin del programa");
    }
}
