import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;

public class ExpAritmetica extends ArbolBin {
    Expresion expresion;

    public ExpAritmetica() {
        expresion = new Expresion();
        root = null;
    }

    private void leer() {
        boolean leer = true;
        if (root != null) {
            System.out.print("Ya existe una expresion, desea reemplazarla? (s/n): ");
            Scanner sc = new Scanner(System.in);
            try {
                leer = (sc.nextLine().equals("s")) ? true : false;
            } catch (Exception e) {
                System.out.println("Error interno -Leer remplazo-: " + e);
            }
        }
        if (leer) {
            root = null;
            expresion.notPolInv.clear();
            expresion.leerExpresion();
            if (!(expresion.expresion.isEmpty())) {
                notPolInv();
            }
        }
    }

    private void notPolInv() {
        Nodo actual;
        Stack<Nodo> padres = new Stack<Nodo>();
        Stack<String> notPolInv = expresion.notPolInv;
        crearArbol();
        actual = root;
        if (root == null) {
            System.out.println("Error interno al crear el arbol");
            return;
        }
        while (actual != null) {
            if (actual.valorS != null) {
                notPolInv.push(actual.valorS);
            }
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
                } catch (Exception e) {
                    actual = null;
                }
            }
        }

    }

    private void crearArbol() {
        root = new Nodo();
        Stack<Nodo> padres = new Stack<Nodo>();
        Nodo actual = root;
        LinkedList<String> exp = expresion.expresion;
        int len = exp.size();
        String elemento;
        padres.push(null);
        for (int i = 0; i < len; i++) {
            elemento = exp.get(i);
            if (elemento == "(") { // parentesis abierto
                validarNodo(actual.izq);
                if (root == null) {
                    return;
                }
                padres.push(actual);
                actual.setIzq(new Nodo());
                actual = actual.izq;
            } else if (elemento == ")") { // parentesis cerrado
                try {
                    actual = padres.pop();
                    if (actual != null && expresion.codigos.contains(actual.valorS)) {
                        actual = padres.pop();
                    }
                } catch (Exception e) {
                    System.out.println("Error interno padres vacio"); // La pila de padres esta vacia
                    root = null;
                    return;
                }
            } else if (expresion.operadores.contains(elemento)) { // operadores
                validarNodo(actual.der);
                if (root == null) {
                    return;
                }
                actual.valorS = elemento;
                padres.push(actual);
                actual.setDer(new Nodo());
                actual = actual.der;
            } else if (expresion.codigos.contains(elemento)) { // especiales
                actual.valorS = elemento;
                if (actual.valorS != "p" && actual.valorS != "e") { // especiales operadores
                    validarNodo(actual.izq);
                    if (root == null) {
                        return;
                    }
                    padres.push(actual);
                    actual.setIzq(new Nodo());
                    actual = actual.izq;
                } else { // especiales constantes
                    try {
                        actual = padres.pop();
                        if (actual != null && expresion.codigos.contains(actual.valorS)) {
                            actual = padres.pop();
                        }
                    } catch (Exception e) {
                        System.out.println("Error interno padres vacio"); // La pila de padres esta vacia
                        root = null;
                        return;
                    }
                }
            } else { // numeros
                actual.valorS = elemento;
                try {
                    actual = padres.pop();
                    if (actual != null && expresion.codigos.contains(actual.valorS)) {
                        actual = padres.pop();
                    }
                } catch (Exception e) {
                    System.out.println("Error interno padres vacio"); // La pila de padres esta vacia
                    root = null;
                    return;
                }
            }
        }
        if (actual != null) {
            root = null;
            return;
        }
    }

    /**
     * @param n
     */
    private void validarNodo(Nodo n) {
        if (n != null) {
            root = null;
        }
    }

    /**
     * @return float
     */
    private float evaluar() {
        Stack<Double> pila = new Stack<Double>();
        Stack<String> notPolInv = expresion.notPolInv;
        String actual;
        double n1;
        double n2;
        while (!notPolInv.empty()) {
            actual = notPolInv.pop();
            if (expresion.operadores.contains(actual) || expresion.codigos.contains(actual)) { // operadores y
                                                                                               // especiales
                try {
                    switch (actual) {
                        case "+":
                            n2 = pila.pop();
                            n1 = pila.pop();
                            pila.add(n1 + n2);
                            break;
                        case "-":
                            n2 = pila.pop();
                            n1 = pila.pop();
                            pila.add(n1 - n2);
                            break;
                        case "/":
                            n2 = pila.pop();
                            n1 = pila.pop();
                            if (n2 == 0) {
                                System.out.println("No se puede dividir entre 0");
                                root = null;
                                return 0;
                            }
                            pila.add(n1 / n2);
                            break;
                        case "*":
                            n2 = pila.pop();
                            n1 = pila.pop();
                            pila.add(n1 * n2);
                            break;
                        case "^":
                            n2 = pila.pop();
                            n1 = pila.pop();
                            pila.add(Math.pow(n1, n2));
                            break;
                        case "s":
                            n1 = pila.pop();
                            pila.add(Math.sin(n1 * Math.PI));
                            break;
                        case "c":
                            n1 = pila.pop();
                            pila.add(Math.cos(n1 * Math.PI));
                            break;
                        case "t":
                            n1 = pila.pop();
                            pila.add(Math.tan(n1 * Math.PI));
                            break;
                        case "a":
                            n1 = pila.pop();
                            pila.add(Math.asin(n1 * Math.PI));
                            break;
                        case "o":
                            n1 = pila.pop();
                            pila.add(Math.acos(n1 * Math.PI));
                            break;
                        case "n":
                            n1 = pila.pop();
                            pila.add(Math.atan(n1 * Math.PI));
                            break;
                        case "l":
                            n1 = pila.pop();
                            pila.add(Math.log(n1));
                            break;
                        case "r":
                            n1 = pila.pop();
                            if (n1 < 0) {
                                System.out.println("No se puede sacar raiz cuadrada de un numero negativo");
                                root = null;
                                return 0;
                            }
                            pila.add(Math.sqrt(n1));
                            break;
                        case "b":
                            n1 = pila.pop();
                            pila.add(Math.abs(n1));
                            break;
                        case "p":
                            pila.add(Math.PI);
                            break;
                        case "e":
                            pila.add(Math.E);
                            break;
                        case "x":
                            n1 = pila.pop();
                            pila.add(Math.exp(n1));
                            break;
                        case "i":
                            n1 = pila.pop();
                            pila.add(Double.valueOf(Math.round(n1)));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error interno no se pudo recuperar la informacion");
                    root = null;
                    return 0;
                }
            } else { // numeros
                try {
                    pila.add(Double.valueOf(actual));
                } catch (Exception e) {
                    System.out.println("Error interno no se pudo convertir: " + actual);
                    root = null;
                    return 0;
                }
            }
        }
        try {// retornar el elemento de la pila redondeado a 3 decimales
            if (pila.size() == 1) {
                return (float) Math.round(pila.pop() * 1000) / 1000;
            }
            System.out.println("Error interno al evaluar la expresion");
            root = null;
            return 0;
        } catch (Exception e) {
            System.out.println("Error interno pila vacia: " + e);
            root = null;
            return 0;
        }
    }

    /**
     * @return int
     */
    public int menuExp() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        float resultado;
        do {

            do {
                System.out.println("-------------------------\nMenu Expresion aritmetica\n-------------------------");
                System.out.println("1. Ingresar expresion");
                System.out.println("2. Mostrar arbol");
                System.out.println("3. Evaluar expresion");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                System.out.println("-------------------------\n");
            } while (opcion < 0 || opcion > 3);
            switch (opcion) {
                case 1: // Ingresar expresion
                    leer();
                    break;
                case 2: // Mostrar arbol
                    if (root != null) {
                        System.out.println("Arbol generado: ");
                        breadthFrist();
                    } else {
                        System.out.println("Debes ingresar una expresion primero");
                    }
                    break;
                case 3: // Evaluar expresion
                    if (root != null) {
                        resultado = evaluar();
                        if (root != null) {
                            System.out.println("Resultado: " + resultado);
                        }
                    } else {
                        System.out.println("Debes ingresar una expresion primero");
                    }
                    break;
            }
        } while (opcion != 0);
        System.out.println("Regresando al menu principal");
        return 0;
    }
}
