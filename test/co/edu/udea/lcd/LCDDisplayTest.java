package co.edu.udea.lcd;

//Importes necesarios para que la clase de pruebas funcione
import static org.junit.Assert.*;
import org.junit.Test;
import co.edu.udea.exception.LCDException;

/**
 * Clase para realizar pruebas sobre la clase LCDDisplay
 * @author Sebastian Montoya Jimenez
 * @version 1.0.0
 * @since 08/08/2018
 */
public class LCDDisplayTest
{
	@Test
	public void testComandoSinCaracterSeparador() throws LCDException // Prueba para detectar comandos sin la coma
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("2 123456", 1);
		}
		catch (LCDException e)
		{
			assertEquals("El comando 2 123456 no contiene el caracter separador ','", e.getMessage()); // Pasa si detecta que el comando no tiene coma
		}
	}
	
	@Test
	public void testComandoSinFormatoAdecuado() throws LCDException // Prueba para detectar comandos con formato inadecuado
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("4,", 4);
		}
		catch (LCDException e)
		{
			assertEquals("El comando 4, no tiene el formato Size,NumberToPrint", e.getMessage()); // Pasa si detecta que el comando no esta bien formateado
		}
	}
	
	@Test
	public void testTamanoNoEsNumero() throws LCDException // Prueba para detectar si el parametro Size no es un numero (entero)
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("9f,654", 4);
		}
		catch (LCDException e)
		{
			assertEquals("El parametro Size [9f] no es un numero", e.getMessage()); // Pasa si detecta que Size contiene almenos un caracter no numerico
		}
	}
	
	@Test
	public void testTamanoFueraDelRango() throws LCDException // Prueba para detectar si el parametro Size sale del rango esperado
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("11,654", 4);
		}
		catch (LCDException e)
		{
			assertEquals("El parametro Size [11] debe estar entre 1 y 10", e.getMessage()); // Pasa si detecta que Size sale del rango [1,10]
		}
	}
	
	@Test
	public void testNumeroEsInvalido() throws LCDException // Prueba para detectar si el parametro Number no es un numero (entero)
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("2,654d", 4);
		}
		catch (LCDException e)
		{
			assertEquals("El parametro Number [654d] no es un numero", e.getMessage()); // Pasa si detecta que Number contiene almenos un caracter no numerico
		}
	}
	
	@Test
	public void testNumeroContieneSigno() throws LCDException // Prueba para detectar si el parametro Number tiene signo
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("2,-654", 4);
		}
		catch (LCDException e)
		{
			assertEquals("El numero -654 contiene el caracter '-', el cual no es un numero!", e.getMessage()); // Pasa si detecta que Number contiene + o -
		}
	}
	
	@Test
	public void testComandoEjecutadoCorrectamente() throws LCDException // Prueba para validar que un comando bien escrito si es procesado correctamente
	{
		LCDDisplay pantallaLCD = new LCDDisplay();
		try
		{
			pantallaLCD.procesarComando("2,1234567980", 1);
			// Pasa la prueba si no se genera ninguna excepcion
		}
		catch (LCDException e)
		{
			fail(e.getMessage()); // Falla si se genera alguna excepcion
		}
	}
	
	@Test
	public void testEsNumeroEntero() // Prueba para revisar que el metodo esNumero si entrega verdadero cuando se le da un String con solo numeros
	{
		boolean resultado = LCDDisplay.esNumero("123456");
		assertEquals(true, resultado); // Pasa si el retorno es true
	}
	
	@Test
	public void testNoEsNumeroEntero() // Prueba para revisar que el metodo esNumero entrega falso cuando evalua un string que contiene algun caracter no numerico
	{
		boolean resultado = LCDDisplay.esNumero("123456f");
		assertEquals(false, resultado); // Pasa si el retorno es false
	}
}
