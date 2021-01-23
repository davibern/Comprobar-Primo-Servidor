package comprobarprimoservidor;

// Librerías
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Davibern
 * @version 1.0
 */
public class Servidor {
    
    // Atributos o Constantes
    private final int PUERTO = 16061;
    private long divisor = 0;
    private long numero;
    
    /**
     * Constructor del servidor. Iniciará el servidor
     */
    public Servidor() {
        
        String resultado = null;
        
        try {
            ServerSocket servidor = new ServerSocket(this.PUERTO);
            System.out.println(this.menuServidor());
            Socket cliente = servidor.accept();
            OutputStream output = cliente.getOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(output);
            InputStream input = cliente.getInputStream();
            DataInputStream inputData = new DataInputStream(input);
            dataOutput.writeUTF(this.menuServidor());
            System.out.println("CLIENTE CONECTADO.");
            System.out.println("\n##########################################");
            String cadena = inputData.readUTF();
            this.numero = Long.parseLong(cadena);
            System.out.println("\nCOMPROBAR NÚMERO: " + cadena);
            System.out.println("\n##########################################\n");
            if (this.esPrimo()) {
                resultado = ("RESULTADO: " + this.numero + " es primo.");
            } else {
                resultado = ("RESULTADO: " + this.numero + " no es primo.");
            }
            dataOutput.writeUTF(resultado);
            System.out.println(resultado);
            servidor.close();
            cliente.close();
            System.out.println("\n##########################################\n");
            System.out.println("Conexión con el cliente cerrada correctamente.");
            System.out.println("\n##########################################\n");
        } catch (IOException ex) {
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
