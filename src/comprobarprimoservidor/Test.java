package comprobarprimoservidor;

/**
 *
 * @author david
 */
public class Test {
     
    public static void main(String[] args) {
        
        StringBuilder cadena = new StringBuilder();
        
        if (args.length == 0) {
            System.out.println("Sin argumentos.");
        } else {
            System.out.println("\nArgumentos:");
                        
            for (String arg : args) {
                cadena.append(arg).append(" ");
            }   
        }
        
        System.out.println("Cadena: " + cadena.toString());
    }
    
}
