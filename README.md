# Comprobar-Primo-Servidor
Comprueba si un número es primo a través de un servidor secuencial

# Resumen ejercicio

## Parte 1:

Implementar un servidor TCP secuencial que reciba desde un cliente un número entero positivo y devuelva el mensaje “primo” si el número es primo o “no primo”.
Si no lo es, si lo que se recibe no se puede interpretar como un número entero entonces devuelve el texto “error”.

Sólo cuando el cliente actual se desconecte, el servidor podrá asistir a otro cliente, pues no es multihilo.

El puerto de servicio del "test de primalidad" será el 16061.

## Parte 2:

Implementar un cliente TCP que reciba como parámetro un número entero positivo (desde la línea de órdenes), pregunte al servidor anterior si ese número es
primo o no y muestre el resultado por pantalla.

# JDK
Java 11
