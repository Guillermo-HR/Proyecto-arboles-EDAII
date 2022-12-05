import java.util.InputMismatchException;
import java.util.Scanner;

public class AVL {
    ArbolAVL nuevoArbol = new ArbolAVL();
    public int menuAVL() {
        Scanner entrada = new Scanner(System.in);
        boolean salida = false;
        int opcion;
        while (!salida) {
            System.out.println("-------------------------\nMenu AVL\n-------------------------");
            System.out.println("Elige la opcion deseada. ");
            System.out.println("1.Trabajar con  un nuevo arbol");
            System.out.println("2.Insertar en el arbol");
            System.out.println("3.Eliminar en el arbol");
            System.out.println("4.Buscar en el arbol");
            System.out.println("5.Recorrer el arbol");
            System.out.println("0.Salir");

            try {
                opcion = entrada.nextInt();
                switch (opcion) {
                    default:
                        System.out.println("Opcion no valida");
                        break;

                    case 1:
                        System.out.println("Generando nuevo arbol");
                        nuevoArbol = new ArbolAVL();
                        System.out.println("Arbol generado exitosamente\n");
                        break;

                    case 2:
                        System.out.println("Escogiste insertar. Ingresa el valor a insertar dentro del arbol");
                        int n = entrada.nextInt();
                        nuevoArbol.insertar(n);
                        System.out.println("Valor ingresado exitosamente al arbol: " + n + "\n");
                        break;

                    case 3:
                        System.out.println("Escogiste eliminar. Ingresa el valor que deseas eliminar dentro del arbol");
                        int m = entrada.nextInt();
                        nuevoArbol.eliminacion(m);
                        System.out.println("La operacion fue realizada exitosamente\n");
                        break;

                    case 4:
                        System.out.println("Escogiste buscar. Ingresa el valor que deseas buscar dentro del arbol");
                        int b = entrada.nextInt();
                        nuevoArbol.busqueda(b);
                        System.out.println("La operacion fue realizada exitosamente\n");
                        break;

                    case 5:
                        System.out
                                .println("A continuacion se mostrara el arbol con la ayuda de busqueda por expansion");
                        nuevoArbol.recorridoExpansion();
                        System.out.println("\n");
                        break;

                    case 0:
                        System.out.println("Escogiste salir.Regresa pronto =) ");
                        salida = true;
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("\nIntroduce un numero valido\n");
                entrada.next();
            }
        }
        return 0;
    }

}
