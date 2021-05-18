import java.util.NoSuchElementException;
import java.util.Stack;

public class Parentesis {
    //Por ejemplo la cadena "{{[({})}}}" es invalida, mientras que la cadena
    // mientras que
    // "{()[()]}" es valida
    public static void main(String[] args) {
        String input_inicial = "(5+3)*(2-3)";
        boolean res = evaluador(input_inicial);
        String resultado = (res) ? "Fue valida la cadena para: " + input_inicial + ", " + res :
                "Fue inválida la cadena para: " + input_inicial + ", " + res;
        //System.out.println(resultado);
    }

    public static boolean evaluar(String input){
        String input_inicial = input;
        boolean res = evaluador(input_inicial);
        String resultado = (res) ? "Fue valida la cadena para: " + input_inicial + ", " + res :
                "Fue inválida la cadena para: " + input_inicial + ", " + res;
        System.out.println(resultado);
        if(res)return true;
        return false;
    }

    public static boolean evaluador(String exp) {
        exp = exp.replace(""," ");
        try {
            Stack<String> stack = new Stack<String>();
            String[] elementos = exp.split(" ");
            String pop="";
            for (int i = 0; i < elementos.length; i++) {
                switch (elementos[i]) {
                    case "(":
                    case "[":
                    case "{":
                        stack.push(elementos[i]);
                        break;
                    case ")":
                        if(!stack.isEmpty()){
                            pop = stack.pop();
                        }else{
                            return false;
                        }
                        if (!"(".equals(pop)) {
                            return false;
                        }
                        break;
                    case "]":
                        if(!stack.isEmpty()){
                            pop = stack.pop();
                        }else{
                            return false;
                        }
                        if (!"[".equals(pop)) {
                            return false;
                        }
                        break;
                    case "}":
                        if(!stack.isEmpty()){
                            pop = stack.pop();
                        }else{
                            return false;
                        }
                        if (!"{".equals(pop)) {
                            return false;
                        }
                        break;
                }
            }
            return stack.isEmpty();
        } catch (NoSuchElementException err) {
            return false;
        }

    }

}