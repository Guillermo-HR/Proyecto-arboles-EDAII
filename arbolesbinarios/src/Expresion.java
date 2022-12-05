import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Expresion {
    LinkedList<String> expresion; // lista con cada elemento de la expresion
    Stack<String> notPolInv; // Notacion polaca inversa
    // listas para verificar
    LinkedList<String> especiales = new LinkedList<>();
    LinkedList<String> operadores = new LinkedList<>();
    LinkedList<String> codigos = new LinkedList<>();
    LinkedList<String> caracteres = new LinkedList<>();

    public Expresion() {
        // Operadores
        operadores.add("+");
        operadores.add("-");
        operadores.add("/");
        operadores.add("*");
        operadores.add("^");

        // Operadores especiales
        especiales.add("sin_"); // seno "s"
        especiales.add("cos_"); // coseno "c"
        especiales.add("tan_"); // tangente "t"
        especiales.add("asin"); // arcoseno "a"
        especiales.add("acos"); // arcocoseno "o"
        especiales.add("atan"); // arcotangente "n"
        especiales.add("log_"); // logaritmo "l"
        especiales.add("sqrt"); // raiz cuadrada "r"
        especiales.add("abs_"); // valor absoluto "b"
        especiales.add("pi__"); // pi "p"
        especiales.add("eul_"); // e "e"
        especiales.add("e___"); // exponencial "x"
        especiales.add("int_"); // parte entera "i"
        especiales.add("\\");

        // Codigos
        codigos.add("s"); // seno
        codigos.add("c"); // coseno
        codigos.add("t"); // tangente
        codigos.add("a"); // arcoseno
        codigos.add("o"); // arcocoseno
        codigos.add("n"); // arcotangente
        codigos.add("l"); // logaritmo
        codigos.add("r"); // raiz cuadrada
        codigos.add("b"); // valor absoluto
        codigos.add("p"); // pi
        codigos.add("e"); // e
        codigos.add("x"); // exponencial
        codigos.add("i"); // parte entera

        // Caracteres
        caracteres.add("0");
        caracteres.add("1");
        caracteres.add("2");
        caracteres.add("3");
        caracteres.add("4");
        caracteres.add("5");
        caracteres.add("6");
        caracteres.add("7");
        caracteres.add("8");
        caracteres.add("9");
        caracteres.add(".");

        // Inicializar listas
        expresion = new LinkedList<>();
        notPolInv = new Stack<String>();
    }

    protected void leerExpresion() {
        Scanner sc = new Scanner(System.in);
        boolean repetir;
        String exp;
        try {
            do {
                expresion.clear();
                repetir = false;
                System.out.print("Ingrese la expresion: ");
                exp = sc.nextLine();
                if (!validar(exp)) {
                    expresion.clear();
                    System.out.print("Quieres volver a intentarlo? (s/n): ");
                    repetir = (sc.nextLine().equals("s")) ? true : false;
                }
            } while (repetir);
        } catch (Exception e) {
            expresion.clear();
            System.out.println("Error interno -Leer expresion-: " + e); // Error con Scanner
        }
    }

    /**
     * @param exp
     * @return boolean
     */
    // Metodos usados en leerExpresion()
    private boolean validar(String exp) {
        int len = exp.length();
        int i = 0;
        int contA = 0; // contador de parentesis abiertos
        int contC = 0; // contador de parentesis cerrados
        String value = "";
        String actual;
        String codigo;
        if (exp.charAt(0) != '(') { // validar formato inicial
            System.out.println("Expresion no valida, debes de empezar con un parentesis abierto");
            return false;
        } else if (exp.charAt(len - 1) != ')') {
            System.out.println("Expresion no valida, debes de terminar con un parentesis cerrado");
            return false;
        }
        do { // recorrer la expresion
            actual = String.valueOf(exp.charAt(i));
            if (especiales.contains(actual)) { // operadores especial
                addValue(value);
                value = "";
                value += exp.substring(i + 1, i + 5);
                if (!especiales.contains(value)) {
                    System.out.println("Comando especial no valido: " + value);
                    listar();
                    return false;
                }
                codigo = String.valueOf(codigos.get(especiales.indexOf(value)));
                expresion.add(codigo);
                i += 5;
                value = "";
            } else if (caracteres.contains(actual)) { // numero o punto
                value += actual;
                i++;
            } else if (actual.equals("(")) { // parentesis abierto
                addValue(value);
                value = "";
                contA++;
                expresion.add("(");
                i++;
            } else if (actual.equals(")")) { // parentesis cerrado
                addValue(value);
                value = "";
                contC++;
                expresion.add(")");
                i++;
            } else if (operadores.contains(actual)) { // operadores
                addValue(value);
                value = "";
                expresion.add(actual);
                i++;
            } else {
                System.out.println("Caracter no valido: " + actual);
                listar();
                return false;
            }
        } while (i < len);
        if (contA != contC) {
            System.out.println("Los parentesis no estan equilibrados, faltan parentesis de apertura y/o cierre");
            return false;
        }
        return true;
    }

    private void listar() {
        System.out.println("Opciones validas: ");
        for (int i = 0; i < caracteres.size(); i++) {
            System.out.print(caracteres.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < operadores.size(); i++) {
            System.out.print(operadores.get(i) + " ");
        }
        System.out.println();
        System.out.println("Para ingresar comandos especiales, debes de escribir el caracter \\ antes del camando");
        for (int i = 0; i < especiales.size(); i++) {
            System.out.print(especiales.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * @param value
     */
    private void addValue(String value) {
        if (value.length() != 0) {
            expresion.add(value);
        }
    }
}
