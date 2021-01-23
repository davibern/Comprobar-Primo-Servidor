# Comprobar-Primo-Servidor
Comprueba si un número es primo a través de un servidor secuencial

# Resumen ejercicio

## Parte 1: implementar un servidor TCP secuencial que reciba desde un cliente un número entero positivo y devuelva el mensaje “primo” si el número es primo o “no primo”
si no lo es. Si lo que se recibe no se puede interpretar como un número entero entonces devuelve el texto “error”. Solo cuando el cliente actual se desconecte,
el servidor podrá asistir a otro cliente, pues no es multihilo. Puedes reutilizar el método de comprobación de si un número es primo que se
proporcionó en el ejercicio 1 (mantén el sleep para que se tarde más cuanto más “difícil” sea el cálculo, si no, es prácticamente inmediato y es más
difícil hacer pruebas para ver si tarda más en unos casos o en otros). El puerto de servicio del "test de primalidad" será el 16061.

## Parte 2: implementar un cliente TCP que reciba como parámetro un número entero positivo (desde la línea de órdenes), pregunte al servidor anterior si ese número es
primo o no y muestre el resultado por pantalla.

# JDK
Java 11
