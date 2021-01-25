package comprobarprimoservidor;

// Librerías
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Davibern
 * @version 1.2
 */
public class Cliente {
    
    // Atributos
    private final int PUERTO = 16061;
    private final String SERVIDOR = "localhost";
    private Long numero;
    private String numeros;
    
    /**
     * Constructor del cliente que se conectará al servidor.
     */
    public Cliente() {
        try {
            Socket cliente = new Socket(this.SERVIDOR, this.PUERTO);
            InputStream input = cliente.getInputStream();
            DataInputStream dataInput = new DataInputStream(input);
            OutputStream output = cliente.getOutputStream();
            DataOutputStream outputData = new DataOutputStream(output);
            System.out.println(dataInput.readUTF());
            Scanner sc = new Scanner(System.in);
            System.out.println("NÚMERO PARA COMPROBAR: ");
            
            try {
                Long cadena = sc.nextLong();
                outputData.writeUTF(cadena.toString());
                System.out.println(dataInput.readUTF());
                cliente.close();
            } catch (InputMismatchException e) {
                System.err.println("Error -> No ha ingresado un número válido.");
            }            
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Constructor sobrecargado que recibe un número por argumento para comprobar
     * @param numeros números pasados como argumentos de ejecución del programa
     */
    public Cliente(String numeros){
        this.numeros = numeros;
        
        try {
            Socket cliente = new Socket(this.SERVIDOR, this.PUERTO);
            InputStream input = cliente.getInputStream();
            DataInputStream dataInput = new DataInputStream(input);
            OutputStream output = cliente.getOutputStream();
            DataOutputStream outputData = new DataOutputStream(output);
            System.out.println(dataInput.readUTF());

            outputData.writeUTF(this.numeros);
            System.out.println(dataInput.readUTF());
            cliente.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Método que lanza el cliente y que se conectará al servidor
     * @param args Número para analizar si es primo o no
     */
    public static void main(String[] args) {
        
        StringBuilder cadena = new StringBuilder();
        
        // Comprobar si el cliente recibe como argumentos un número
        if (args.length == 0) {
            // Ejecutar el cliente usando el número que recibe como parámetro
            Cliente cliente = new Cliente();
        } else {
            // Ejecutar el cliente usando el número pasado como argumento
            try {
                // Se almacena en una cadena los números a analizar
                for (String arg : args) {
                    cadena.append(arg).append(" ");
                }
                Cliente cliente = new Cliente(cadena.toString());
            } catch (NumberFormatException e) {
                System.err.println("Error -> No ha ingresado un número válido.");
            }

        }
        
    }
    
}