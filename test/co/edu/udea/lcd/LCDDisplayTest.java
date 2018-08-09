package co.edu.udea.lcd;

//Importes necesarios para que la clase de pruebas funcione
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase para realizar pruebas sobre la clase LCDDisplay
 * @author Sebastian Montoya Jimenez
 * @version 1.0.0
 * @since 08/08/2018
 */
public class LCDDisplayTest
{

	@Test
	public void testEsNumeroEntero() // Prueba para revisar que el metodo esNumero si entrega verdadero cuando se le da un String con solo numeros
	{
		boolean resultado = LCDDisplay.esNumero("123456");
		assertEquals(true, resultado); // La prueba pasa si el retorno es true
	}
	
	@Test
	public void testNoEsNumeroEntero() // Prueba para revisar que el metodo esNumero entrega falso cuando evalua un string que contiene algun caracter no numerico
	{
		boolean resultado = LCDDisplay.esNumero("123456f");
		assertEquals(false, resultado); // La prueba pasa si el retorno es false
	}
}
