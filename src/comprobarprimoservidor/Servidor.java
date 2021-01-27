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
 * Sevidor que comprueba los números que recibe del cliente e indica cuáles son primos
 * 
 * @author Davibern
 * @version 1.3
 */
public class Servidor {
    
    // Atributos o Constantes
    private final int PUERTO = 16061;
    private long divisor = 0;
    private long numero;
    private List<Long> numeros = new LinkedList<>();
    public static List<Long> listaNumeros = new LinkedList<>();
    
    /**
     * Constructor del servidor. Iniciará el servidor
     */
    public Servidor() {
        
        List<Thread> hilos = new LinkedList<>();
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
                
                while (!cadena.endsWith("F")) {
                    System.out.println("Número para analizar: " + cadena);
                    this.numeros.add(Long.parseLong(cadena));
                    cadena = inputData.readUTF();
                    outputData.flush();
                }
                
                System.out.println("Añadido todos los números: " + this.numeros);
                // Se crean tantos hilos como números haya que comprobar
                for (Long numero : this.numeros) {
                   ComprobadorPrimo comprobarNumero = new ComprobadorPrimo(numero);
                   hilos.add(comprobarNumero);
                }
                
                // Se ejecutan los hilos
                for (Thread hilo : hilos) {
                    hilo.start();
                }
                
                // Cuando finalice la ejecución del hilo se sumará al contador de hilos finalizados
                for (Thread hilo : hilos) {
                    try {
                        hilo.join();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                
                // Salidas y cierre del programa
                outputData.writeUTF("Los números primos son: " + listaNumeros);
                System.out.println("Los números primos son: " + listaNumeros);
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
     * Guardar el número primo en la lista para devolverlo al cliente
     * @param numero 
     */
    public synchronized static void guardarNumero(Long numero) {
        listaNumeros.add(numero);
    }
        
    /**
     * Método que levanta el servidor
     * @param args El número a analizar si se cumplimenta como argumento
     */
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
    }
    
}