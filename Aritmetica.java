import java.util.*;
public class Aritmetica {
    // Hecho por Michel Lujano A01636172
    // Comentarios importantes
    /*Simbolos generadores = {S,O,S}
     Simbolos no generadores = {+,*,-,(,),[0-9]}
     Producciones S-> [0-9]|SOS|OS|epsilon
     ... O-> *,+,-,(,)
     Si son expresiones muy grandes, tardará un poco el programa en dar el print del árbol
     Comentar las funciones de print y print2 si se requiere ir al resultado directamente.
     Línea 35, y 37 aprox funciones de print árbol.
     Probablemente se quede ciclado si es una operación grande*/
    private static String entrada = "-3*(5)";
    //private static String entrada = "5+5+3+1+2+6";
    private static String[] tokens;
    private Queue<String> cola = new LinkedList<>();
    private Nodo root;
    private static int size = 0;
    public Aritmetica() {
        this.root = null;
    }
    public static void main(String[] args){
        boolean validarExpresion = validarEntrada(Aritmetica.entrada);
        if(validarExpresion){
            System.out.println(Aritmetica.entrada + " : Entrada válida");
            //System.out.println("Tokens Array: ");
            // Array tokens
            //Aritmetica.printTokens();
            /******** start of print ********/
            Aritmetica a = new Aritmetica();
            a.start(Aritmetica.entrada);
            System.out.println();
            System.out.println("Hacer zoom out para ver el arbol en consola");
            System.out.println("Función exponencial para imprimir puede tardar mucho");
            System.out.println("Opcion 1 **Árbol** (Visualizar): ");
            // Si la operación es muy grande, mejor comentar está función.
            //a.print();
            System.out.println("Opcion 2 (Visualizar): ");
            // Si la operación es muy grande, mejor comentar está función.
            a.print2();
            a.imprimirSalida();
            System.out.println("Valor esperado: " + Aritmetica.entrada);
            System.out.print("Valor después del arbol de derivación: ");
            a.preOrder(a.root);
        }else{
            System.out.println(Aritmetica.entrada + " : Entrada no válida");
        }
    }
    public void start(String entrada) {
        Aritmetica.entrada = entrada;
        Aritmetica.size = tokens.length;
        //System.out.println(size + "tamaño string");
        if (this.root == null) {
            this.root = new Nodo("S");
        }
        Nodo parent = null;
        Nodo current = this.root;
        //System.out.println(current.toString());
        //System.out.println(current.toString());
        //System.out.println(this.root.toString());
        /** Casos menores a length 2 **/
        if(Aritmetica.size == 1){
            current.setLeft(new Nodo(tokens[0]));
            return;
        }
        if(Aritmetica.size == 2){
            current.setLeft(new Nodo("O"));
            current.setMid(new Nodo("S"));
            Nodo izq = current.getLeftNull();
            Nodo mid = current.getMidNull();
            izq.setLeft(new Nodo(tokens[0]));
            mid.setMid(new Nodo(tokens[1]));
            //System.out.println(izq.getLeftNull().toString());
            //System.out.println(mid.getMidNull().toString());
        }else{

            // SOS
            boolean config1 = false;
            // OS
            boolean config2 = false;
            // Configuraciones de inicio
            if(tokens[0].matches(("[+,-]"))){
                // Config2
                current.setLeft(new Nodo("O"));
                current.setMid(new Nodo("S"));

                current.left.left = new Nodo(tokens[0]);
                current = current.mid;

                current.left = new Nodo("S");
                current.left.left = new Nodo(tokens[1]);

                current.mid = new Nodo("O");
                current.mid.mid = new Nodo(tokens[2]);

                current.right = new Nodo("S");
                current = current.right;

                config2=true;
            }else if(tokens[0].matches("[0-9]+")){
                // Config1
                current.setLeft(new Nodo("S"));
                current.setMid(new Nodo("O"));
                current.setRight(new Nodo("S"));
                config1=true;
                if(Aritmetica.size == 3){
                    current.left.left = new Nodo(tokens[0]);
                    current.mid.left = new Nodo(tokens[1]);
                    current.right.left = new Nodo(tokens[2]);
                }

            }else if(tokens[0].equals("(")) {
                // Config3
                System.out.println(current.toString());
                current.left = new Nodo("O");
                current.mid = new Nodo("S");
                current.left.left = new Nodo(tokens[0]);
                current = current.mid;
                current.left = new Nodo("S");
                current.left.left = new Nodo(tokens[1]);
                current.mid = new Nodo("O");
                current.mid.mid = new Nodo(tokens[2]);
                current.right = new Nodo ("S");
                current = current.right;
                config2 = true;
                if(size == 3){
                    current.left.left = new Nodo(tokens[0]);
                    current = current.mid;
                    current.left = new Nodo("S");
                    current.mid = new Nodo("O");
                    current.right = new Nodo("S");
                    current.left.left = new Nodo(tokens[1]);
                    current.mid.mid = new Nodo(tokens[2]);
                }
            }
            //System.out.println(tokens[Aritmetica.tokens.length-1] + "algo");
           // System.out.println("Parent : "+this.root.toString());
            //System.out.println("Current : "+current.toString());
            for (int i = 2; i < Aritmetica.tokens.length-1; i++) {
                switch(tokens[i]){
                    case "*":
                        break;
                    case "+":
                    case "-":
                        if(config2 && i==2){
                            //current.left.left = new Nodo(tokens[0]);
                            /*current = current.mid;
                            current.left = new Nodo("S");
                            current.mid = new Nodo("O");
                            current.right = new Nodo("S");

                            current.left.left = new Nodo(tokens[1]);
                            current.mid.mid = new Nodo(tokens[i]);*/

                            /*current = current.right;
                            System.out.println(current.toString() + "valor");
                            System.out.println("Valor i: " + i);*/
                            if(current.getValue().equals(("S")) && (tokens.length - (i+1)) >=1){
                                current.left = new Nodo(tokens[tokens.length-1]);
                            }
                        }
                        break;
                    case "(":
                        if(config1 && i==2){
                            current.left.left = new Nodo(tokens[0]);
                            current.mid.left = new Nodo(tokens[1]);
                            current = current.right;
                        }
                        if(tokens[i+1].matches("[0-9]+")){
                            //System.out.println(current.toString());
                            current.left = new Nodo("O");
                            current.left.left = new Nodo(tokens[i]);
                            current.mid = new Nodo("S");
                            current = current.mid;
                            //System.out.println(tokens[i] + "ultimo valor");
                            continue;
                        }
                        if(tokens[i+1].equals("(")){
                            current.left = new Nodo("O");
                            current.left.left = new Nodo(tokens[i]);
                            current.mid = new Nodo("S");
                            current = current.mid;
                            //System.out.println(tokens[i] + "ultimo valor");
                            continue;
                        }
                        break;
                    case ")":
                        if(tokens[i+1].matches("[*,+,-]") ||
                                tokens[i+1].equals("(") || tokens[i+1].equals(")")){
                            //System.out.println(tokens[i] + "valor actual )");
                            if(config1){
                                current.left = new Nodo("O");
                                current.left.left = new Nodo(tokens[i+1]);
                                current.mid = new Nodo("S");
                                current = current.mid;
                            }
                            if(config2){
                                current.left = new Nodo("S");
                                //current.left.left = new Nodo(tokens[i]);
                                System.out.println(tokens[i] + " : Valor con i");
                                current.mid = new Nodo("O");
                                current.mid.mid = new Nodo(tokens[i+1]);
                                current.right = new Nodo("S");
                                current = current.right;
                            }
                            if(config2 && current.getValue().equals(("S")) && i+2 == tokens.length-1){
                                current.left = new Nodo(tokens[tokens.length-1]);
                                break;
                            }
                        }
                        break;
                    default:
                        if(config1 && i==2){
                            //System.out.println("entro");
                            //System.out.println(current.toString());
                            // Primero creamos el nodo
                            // De left
                            // Y después creamos su siguiente nodo.
                            current.left.left = new Nodo(tokens[0]);
                            current.mid.mid = new Nodo(tokens[1]);
                            current = current.right;

                        }else if(config2 && i==2){
                            // Parentesis
                        }

                        //System.out.println(tokens[i+1] + "Valor : ");
                        if(tokens[i+1].matches(("[+,*,-]"))){
                            current.left = new Nodo("S");
                            current.left.left = new Nodo(tokens[i]);
                            current.mid = new Nodo("O");
                            current.mid.mid = new Nodo(tokens[i+1]);

                            if(i==tokens.length-1){
                                current.right = new Nodo("S");
                                current = current.right;
                                current.left = new Nodo("epsilon");
                            }else{
                                current.right = new Nodo("S");
                                current = current.right;
                                if(i+1 != tokens.length){
                                    current.left = new Nodo("S");
                                    current.mid = new Nodo("O");
                                    current.right = new Nodo("S");
                                   // System.out.println(current.toString() + "if");
                                    //System.out.println("Indice I: " + i);
                                }
                                //System.out.println(current.right.toString() + "q es");
                                if(current.right.getValue().equals(("S")) && i+2 == tokens.length-1){
                                    Nodo last = current.right;
                                    last.left = new Nodo(tokens[size-1]);
                                    //System.out.println("Entro??");
                                }
                            }
                            //System.out.println("Antes del continue");
                        }
                        // Si es un numero
                        else if(tokens[i+1].matches(("[0-9]+"))){
                            current.left = new Nodo("O");
                            current.left.left = new Nodo(tokens[i]);
                            current.mid = new Nodo("S");
                            current.mid.mid = new Nodo(tokens[i+1]);
                            //System.out.println("entraria?");
                        }
                        // Detectar parentesis
                        if(tokens[i+1].equals(")")){

                            System.out.println("entra?");
                            System.out.println(i + "Indice");
                            //System.out.println("Entro parentesis derecho?");
                            //System.out.println("Derecho : " + tokens[i]);
                            //System.out.println(current.toString() + "Derecho");

                            current.left = new Nodo("S");
                            current.left.left = new Nodo(tokens[i]);
                            current.mid = new Nodo("O");
                            current.mid.mid = new Nodo(tokens[i+1]);
                            current.right = new Nodo("S");
                            current = current.right;

                            //current.left.left = new Nodo(tokens[i]);
                            //current.mid.mid = new Nodo(tokens[i+1]);
                            //current = current.right;
                        }
                        break;
                }
            }
        }
    }
    /** Validar entrada **/
    public static boolean validarEntrada(String entrada){
        boolean parentesis = false;
        parentesis = Parentesis.evaluar(Aritmetica.entrada);
        if(!parentesis)return false;

        Aritmetica.tokens = entrada.split("");

        if(tokens.length == 2 && tokens[0].matches("[0-9]+") && tokens[1].matches("[+,*,-]"))return false;

        if(tokens.length == 1){
            if(tokens[0].matches("[0-9]+")){
                return true;
            }else{
                return false;
            }
        }
        for (int i = 0; i < Aritmetica.tokens.length; i++) {
            //System.out.println("False1");
            if(tokens.length == 2 && tokens[0].equals("(") && tokens[1].equals(")"))return false;

            if(tokens.length == 2){
                // 1. Caso:  -5 (Entrada valida)
                if(tokens[0].equals("-") || tokens[0].equals("+") ){
                    if(tokens[1].matches("[0-9]+")){
                        //System.out.println("entro??");
                        //System.out.println("True1");
                        return true;
                    }
                }
                //System.out.println("False2");
                if(tokens.length >= 2 && tokens[0].matches("[+,*,-]") && tokens[1].matches("[+,*,-]"))return false;
            }else{
                //System.out.println("Entro segunda condición?");
                //System.out.println("False3");
                if(tokens[0].equals("*"))return false;
                //if(tokens[0].equals("("))return true;
                //System.out.println("False4");
                if(tokens.length == 2 && tokens[1].matches("[+,*,-]"))return false;
                //System.out.println("True2");
                if(tokens[0].matches("[0-9]+") && tokens.length < 2){
                    return true;
                }
                if(i>=1 && i<tokens.length-1){
                    if(tokens[i].matches(("[0-9]+"))){
                        //System.out.println("False5");

                        if(!tokens[i+1].matches("[*,+,,),-]"))return false;
                    }
                    if(tokens[i].matches(("[*,+,-]"))){
                        if(tokens[i-1].matches("[0-9]+") && tokens[i+1].matches("[0-9]+")){
                            //System.out.println("False6");
                            continue;
                        }else if(tokens[i-1].matches("[0-9]+") && tokens[i+1].equals("(")){
                            continue;
                        }else if(tokens[i-1].equals(")") && tokens[i+1].equals("(")){
                            continue;
                        }else if(tokens[i-1].equals("(") && tokens[i+1].equals("(")){
                            //System.out.println("False6.1");
                            return false;
                        }
                    }
                    if(tokens[i].equals(("("))){
                        if(tokens[i-1].matches("[*,+,-]") && tokens[i+1].matches("[*,+,-]")){
                            //System.out.println("False7.0");
                            return false;
                        }else if(tokens[i-1].matches("[0-9]") && tokens[i+1].matches("[*,+,-]")){
                            //System.out.println("False7.5");
                            return false;
                        }
                    }
                    if(tokens[i].equals((")"))){
                        if(tokens[i-1].matches("[*,+,-]") && tokens[i+1].matches("[*,+,-]")){
                            //System.out.println("False7.1");
                            return false;
                        }else if(tokens[i-1].matches("[*,+,-]") && tokens[i+1].matches("[0-9]+")){
                            //System.out.println("False7.2");
                            return false;
                        }
                    }
                    int pos = i;
                    //System.out.println(tokens[i] + " : Array");
                    while(pos>= 1 && pos<tokens.length-1 && tokens[pos+1].equals(")")) {
                        //System.out.println(tokens[pos] + " : Valor entro");
                       // System.out.println("False7");
                        if (tokens[pos + 1].matches("[0-9]+")) return false;

                        if(tokens[pos-1].matches("[0-9]+") && tokens[pos+1].matches("[*,+,-]+")){
                            //System.out.println("False7.3");
                            return false;
                        }
                        pos++;
                    }
                    int pos2 = i;
                    //System.out.println(tokens[i] + " : Array");
                    while(pos2>= 1 && pos2<tokens.length-1 && tokens[pos2].equals("(")) {
                        //System.out.println("False8");
                        if (tokens[pos2 + 1].equals(")")) return false;
                        pos2++;
                    }
                    if(tokens[i].matches("[+,*,-]")){
                        //System.out.println("entro");
                        System.out.println("False9");
                        if(tokens[i-1].equals("(")
                                && tokens[i+1].equals(")"))
                            return false;
                    }
                    //System.out.println("False10");
                    if(tokens[tokens.length-1].matches("[+,*,-]"))return false;
                    //System.out.println("entro for?");
                    if(tokens[i].matches("[+,*]") || tokens[i].equals("-")){
                        //System.out.println("Entro??");
                        //System.out.println("entro?");
                        if(tokens[i-1].matches("[+,*]")
                                || tokens[i-1].equals("-")
                                ||  (tokens[i+1].matches("[+,*]"))
                                || tokens[i+1].equals("-")){
                            //System.out.println("False11");
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
    /** Imprimir arbol **/
    private boolean haveNodesToPrint(Nodo[] nodes) {
        for (Nodo node : nodes) {
            if (node != null) {
                return true;
            }
        }
        return false;
    }
    public void print() {
        Nodo[] nodes = {this.root};
        if (this.root == null) {
            return;
        }
        int height1 = Math.max(getHeight(this.root.getLeft()), getHeight(this.root.getRight()));
        int height2 = Math.max(height1, getHeight(this.root.getMid()));
        while (haveNodesToPrint(nodes)) {
            int offset = (int) Math.pow(2, height2 + 1);
            for (Nodo node : nodes) {
                printSpace(offset); // offset before every number
                if (node != null) {
                    System.out.print("["+node.getValue()+"]");
                } else {
                    System.out.print("");
                }
                printSpace(offset - 2); // minus 2 because we expect the number to have length of 2
            }
            //print the spaces
            for (int i = 0; i <= height2; i++) {
                System.out.println();
            }
            // prepare for printing next line
            Nodo[] newNodes = new Nodo[nodes.length * 3]; // every node have 2 childs
            int index = 0;
            for (Nodo node : nodes) {
                if (node != null) {
                    newNodes[index] = node.getLeftNull();
                    index++;
                    newNodes[index] = node.getMidNull();
                    index++;
                    newNodes[index] = node.getRightNull();
                    index++;
                } else {
                    //even if it is null you need to print spaces
                    newNodes[index] = null;
                    index++;
                    newNodes[index] = null;
                    index++;
                    newNodes[index] = null;
                    index++;
                }
            }
            nodes = newNodes;
            height2--;
        }
    }
    public void print2() {
        Nodo[] nodes = {this.root};
        if (this.root == null) {
            return;
        }
        int height1 = Math.max(getHeight(this.root.getLeft()), getHeight(this.root.getRight()));
        int height2 = Math.max(height1, getHeight(this.root.getMid()));
        while (haveNodesToPrint(nodes)) {
            int offset = (int) Math.pow(2, height2 + 1);
            for (Nodo node : nodes) {
                printSpace2(offset); // offset before every number
                if (node != null) {
                    System.out.print("["+node.getValue()+"]");
                } else {
                    System.out.print("");
                }
                printSpace2(offset - 2); // minus 2 because we expect the number to have length of 2

            }

            System.out.println();
            Nodo[] newNodes = new Nodo[nodes.length * 3]; // every node have 3 childs
            int index = 0;
            for (Nodo node : nodes) {
                if (node != null) {
                    newNodes[index] = node.getLeftNull();
                    index++;
                    newNodes[index] = node.getMidNull();
                    index++;
                    newNodes[index] = node.getRightNull();
                    index++;
                } else {
                    //even if it is null you need to print spaces
                    newNodes[index] = null;
                    index++;
                    newNodes[index] = null;
                    index++;
                    newNodes[index] = null;
                    index++;

                }
            }
            nodes = newNodes;
            height2--;
        }
    }
    public void imprimirSalida(){
        Iterator iterator = cola.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + ",");
        }
    }
    private void printSpace(int n) {
        while (n != 0) {
            System.out.print(" ");
            n--;
        }
    }
    private void printSpace2(int n) {
        while (n != 0) {
            System.out.print("");
            n--;
        }

    }
    /** Recorridos and GetAltura **/
    private int getHeight(Nodo current) {

        if(Aritmetica.size <= 2)return 2;
        if (current == null) {
            return 0;
        }

        return 1 + Math.max(
                Math.max(
                        getHeight(current.getLeftNull()),
                        getHeight(current.getMidNull())
                ),
                getHeight(current.getRightNull())
        );
    }
    public void preOrder(Nodo node){
        if(node==null)return;

        if(node.getValue().matches("[0-9]+") || node.getValue().matches("[*,+,-]") ||
                node.getValue().equals("(") || node.getValue().equals(")")){
            System.out.print(node.getValue()+"");
        }
        preOrder(node.getLeftNull());
        preOrder(node.getMidNull());
        preOrder(node.getRightNull());
    }
}
