package co.edu.udea.exception;

/**
 * Clase para el manejo personalizado de excepciones, esta nos permitira volcar
 * todo los eventos inesperados, advertencias o errores que sucedan, a un log o
 * realizar otro tipo de instrucciones que nosotros consideremos apropiadas.
 * @author Sebastian Montoya Jimenez
 * @version 1.0.0
 * @since 07/08/2018
 */
public class LCDException extends Exception
{
	// Constante que es necesario implementar cuando la clase hereda de la clase Exception. Para el aplicativo no es relevante
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metodo con el cual se tratan las incidencias o eventos inesperados del aplicativo
	 * @param message Mensaje de error que se presento
	 */
	public LCDException(String message)
	{
		super(message);
	}
	
}
