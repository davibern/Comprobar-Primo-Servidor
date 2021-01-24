package comprobarprimoservidor;

// Librerías
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Davibern
 * @version 1.2
 */
public class Servidor {
    
    // Atributos o Constantes
    private final int PUERTO = 16061;
    private long divisor = 0;
    private long numero;
    private List<Long> numeros = new LinkedList<>();
    
    /**
     * Constructor del servidor. Iniciará el servidor
     */
    public Servidor() {
        
        StringBuilder resultado = new StringBuilder();
                       
        try {
            
            // Levantar servidor
            ServerSocket servidor = new ServerSocket(this.PUERTO);
            System.out.println(this.menuServidor());
                       
            // Mientras haya conexión...
            while (true) {
                Socket cliente = servidor.accept();
                OutputStream output = cliente.getOutputStream();
                DataOutputStream outputData = new DataOutputStream(output);
                InputStream input = cliente.getInputStream();
                DataInputStream inputData = new DataInputStream(input);
                outputData.writeUTF(this.menuServidor());
                System.out.println("CLIENTE CONECTADO.");
                System.out.println("\n##########################################");
                String cadena = inputData.readUTF();
                
                // Se pasan los argumentos a una lista de números
                for (String separarCadena : cadena.split(" ")) {
                    this.numeros.add(Long.parseLong(separarCadena.trim()));
                }
                
                // Se muestran los números por pantalla
                System.out.println("Los números a comprobar son: " + cadena);
                
                // Se comprueba cada número almacenado en la lista
                for (int i = 0; i < this.numeros.size() - 1; i++) {
                    
                    this.numero = this.numeros.get(i);
                   
                    if (this.esPrimo()) {
                        resultado.append(this.numeros.get(i).toString()).append(" ");
                    }
                                       
                }
                
                // Salidas y cierre del programa
                outputData.writeUTF("Los números primos son: " + resultado.toString());
                System.out.println("Los números primos son: " + resultado.toString());
                System.out.println("Terminada la ejecución.");
                inputData.close();
                outputData.close();
                servidor.close();
                cliente.close();
                
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    /**
     * Método que devuelve el menú incial del Servidor y que sirve para el cliente
     * @return Mensaje de texto de bienvenida del servidor
     */
    private String menuServidor() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("##########################################\n");
        mensaje.append("\nSERVIDOR PARA COMPROBAR NÚMEROS PRIMOS");
        mensaje.append("\nPUERTO: ").append(this.PUERTO).append("\n");
        mensaje.append("\n##########################################");
        mensaje.append("\n");
        
        return mensaje.toString();
    }
    
    /**
     * Método que comprueba si un número es tipo primo
     * @param numero El número a comprobar
     * @return Verdadero o Falso si el número es o no primo
    */
    boolean esPrimo() {
        boolean primo = true;
        long candidatoDivisor = 2;
        while (candidatoDivisor < this.numero && primo) {
            try {
                Thread.sleep (0, 2);
            } catch (InterruptedException ex) {
                System.out.printf ("Error en sleep: %s.\n", ex.getMessage());
            }
            if (this.numero % candidatoDivisor == 0) {
                primo = false;
                this.divisor = candidatoDivisor;
            } else
                candidatoDivisor++;                       
        }        
        return primo;
    }
    
    /**
     * Método que levanta el servidor
     * @param args El número a analizar si se cumplimenta como argumento
     */
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
    }
    
}