package comprobarprimoservidor;

/**
 * Clase realizada por el profesor que comprueba si un número es primo.
 * 
 * @author profesor
 * @version 1.0
 */
public class ComprobadorPrimo extends Thread {
    
    // Atributos
    private final long numero;
    private long divisor = 0;

    /**
     * Constructor que pide un número de tipo Long
     * @param numero Número de tipo Long que se usará para comprobar si es primo
     */
    public ComprobadorPrimo(long numero) {
        this.numero = numero;
    }
    
    /**
     * Método sobreescrito run de Thread que implementa el método esPrimo
     * ACTUALIZACIÓN: ya no se debe mostrar los resultados por pantalla, sino guardarlos en una estructura de datos
     */
    @Override
    public void run() {
        if (esPrimo(this.numero)) {
            //System.out.printf ("%10d es primo.\n", this.numero);
            Servidor.guardarNumero(this.numero);
            //System.out.println(Servidor.listaNumeros);
        }      
    }
    
    /**
     * Método que comprueba si un número es tipo primo
     * @param numero El número a comprobar
     * @return Verdadero o Falso si el número es o no primo
     */
    boolean esPrimo(long numero) {
        boolean primo = true;
        long candidatoDivisor = 2;
        while (candidatoDivisor < numero && primo) {
            try {
                Thread.sleep (0, 2);
            } catch (InterruptedException ex) {
                System.out.printf ("Error en sleep: %s.\n", ex.getMessage());
            }
            if (numero % candidatoDivisor == 0) {
                primo = false;
                divisor = candidatoDivisor;
            } else
                candidatoDivisor++;                       
        }        
        return primo;
    }   
    
}