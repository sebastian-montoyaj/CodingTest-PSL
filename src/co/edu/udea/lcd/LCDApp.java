package co.edu.udea.lcd;

// Importes necesarios para que la clase funcione
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import co.edu.udea.lcd.LCDDisplay;
import co.edu.udea.exception.LCDException;

/**
 * Clase principal del aplicativo, la cual se encarga de tomar los comandos que
 * el usuario ingresa, los revisa y los pasa de la forma adecuada a un objeto
 * del tipo LCDDisplay para que los muestre por consola.
 * @author Sebastian Montoya Jimenez
 * @version 1.0.0
 * @since 07/08/2018
 */
public class LCDApp
{
	// Constante que indica cual es el comando para terminar/cerrar el aplicativo
	static final String CADENA_FINAL = "0,0";
	
	/**
	 * Metodo de ejecucion principal el cual sirve como punto de entrada a la aplicacion
	 * @param args Permite la recepcion de argumentos por consola. En si no se utiliza, pero Java lo exige para la correcta ejecucion del aplicativo.
	 * @throws LCDException Esto permite lanzar las excepciones personalizadas para este aplicativo
	 */
	public static void main(String[] args) throws LCDException
	{
		// En primer lugar se proceden a crear las variables y objetos que necesita el aplicativo
        List<String> listaComando = new ArrayList<>(); // Variable en donde se almacenaran cada uno de los comandos que indique el usuario
        String comando; // Variable auxiliar en donde se recibira de manera temporal lo que ingrese el usuario para analizarlo
        int espacioEntreDigitos; // Variable para almacenar el numero de espacios a guardar entre digitos. Rango: [0,5]
        LCDDisplay pantallaLCD = new LCDDisplay(); // Objeto LCDDisplay con el cual procesaremos y mostraremos los digitos que ingreso el usuario
        Scanner lector = new Scanner(System.in); // Y se crea un objeto scanner el cual estara pendiente de lo que sucede en la consola
        
        try // Ahora, se intenta ...
        {
	        // Solicitar al usuario cuantos espacios desea dejar entre cada digito
	        System.out.print("Espacio entre Digitos (0 a 5): ");
	        
	        // Y esperemos que ingrese dicho valor
	        comando = lector.next();
	        
	        // Si lo que ingreso el usuario NO es un numero entonces ...
	        if (!LCDDisplay.esNumero(comando))
	        {
	        	// Lanzamos la excepcion correspondiente
	        	throw new LCDException(String.format("La cadena %s no es un numero entero", comando));
	        }
	        
	        // Luego si pasa aquí, entonces inicializamos la variable espacioEntreDigitos con el numero que dio el usuario
	        espacioEntreDigitos = Integer.parseInt(comando);
	        
	        // A continuacion, Si el numero ingresado no esta dentro del rango esperado (menor a 0 o mayor a 5) entonces ...
	        if (espacioEntreDigitos < 0 || espacioEntreDigitos > 5)
	        {
	        	// Lanzamos la excepcion correspondiente
	        	throw new LCDException("El espacio entre digitos debe estar entre 0 y 5");
	        }
	        
	        // Si el numero ingresado si estaba en el rango correcto entonces se procede a...
	        do
	        {
	            System.out.print("Entrada: "); // Solicitar el comando que se desea imprimir
	            comando = lector.next(); // y se espera a que el usuario termine de ingresarlo
	            
	            // Una vez se confirma el comando, Si este es diferente del comando de finalizacion entonces
	            if(!CADENA_FINAL.equals(comando))
	            {
	                listaComando.add(comando); // Se añade este a la lista de comandos a imprimir
	            }
	        } while (!CADENA_FINAL.equals(comando)); // Ahora, mientras no se haya ingresado el comando de finalizacion se procedera a solicitar el siguiente comando
	        
	        
	        // Una vez que el usuario ha terminado de registrar todos sus comandos se procede a ...
	        
	        // Crear una variable Iterator con la cual recorreremos todos los comandos registrados
	        Iterator<String> iteratorComandos = listaComando.iterator();
	        
	        // Ahora, mientras todavia hayan comandos por mostrar ...
	        while (iteratorComandos.hasNext()) 
	        {
	        	// Se presentara el numero dado con las dimensiones y espacios especificados
	        	pantallaLCD.procesarComando(iteratorComandos.next(), espacioEntreDigitos);
	        }
        }
        catch (LCDException e) // En caso de error ...
        {
        	// Se imprime por consola el error que fue causa de la excepcion y en el cual incidio el usuario
        	System.out.println(e.getMessage());
		}
        finally // Finalmente, cerramos la comunicacion del objeto scanner e imprimimos un mensaje para advertir de la terminacion del programa
        {
        	lector.close();
        	System.out.println("---- FIN de LCDApp ----");
        }
	}
}
