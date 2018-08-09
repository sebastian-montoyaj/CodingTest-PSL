package co.edu.udea.lcd;

//Importes necesarios para que la clase funcione
import co.edu.udea.exception.LCDException;

/**
 * Clase encargada de manejar toda la logica y metodos de la pantalla LCD
 * @author Sebastian Montoya Jimenez
 * @version 1.0.0
 * @since 07/08/2018
 */
public class LCDDisplay
{
	// --------- CONSTANTES ---------
	
	private static final String CARACTER_SEPARADOR = ","; // Constante que define el caracter que separa el tamaño de los digitos del numero a imprimir
	private static final String CARACTER_VERTICAL = "|"; // Constante que define el caracter vertical de los digitos a imprimir
	private static final String CARACTER_HORIZONTAL = "-"; // Constante que define el caracter horizontal de los digitos a imprimir
	private static final int COORDENADA_Y = 0; // Constante que nos ayudara a asociar el indice 0 de los puntos de referencia con las filas o eje Y
	private static final int COORDENADA_X = 1; // Constante que nos ayudara a asociar el indice 1 de los puntos de referencia con las columnas o eje X
    
    // --------- VARIABLES ----------
    
    private int tamanoDigitos; // Variable que almacenara el tamaño que deberan tener los digitos
    private int espacioEntreDigitos; // Variable que guardara el numero de espacios a reservar entre digitos
    private int filasPorDigito; // Variable que guardara el numero de filas que se utilizaran en la impresion de cada digito
    private int columnasPorDigito; // Variable que guardara el numero de columnas que se utilizaran en la impresion de cada digito
    private int totalFilas; // Variable que tendra el total de filas que necesita la impresion del numero
    private int totalColumnas; // Variable que tendra el total de columnas que necesita la impresion del numero
    private String[][] matrizDeImpresion; // Variable en donde se ira construyendo la representación del numero a estilo de pantalla LCD
    
    // Las siguientes variables se usan como puntos coordenada que nos ayudaran en el dibujado de los caracteres que representaran cada uno de los digitos del numero a imprimir
    private int[] posicionEsquinaSuperiorIzquierda = new int[2];
    private int[] posicionCentroMedioIzquierdo = new int[2];
    private int[] posicionEsquinaInferiorIzquierda = new int[2];
    private int[] posicionCentroMedioDerecha = new int[2];
    private int[] posicionEsquinaSuperiorDerecha = new int[2];
    
    // --------- METODOS ------------
    
    /**
     * Metodo encargado de añadir (dibujar) una linea a la matriz de Impresion
     * @param puntoReferencia Par de coordenadas que sirven de referencia para saber desde donde imprimir la linea
     * @param caracterADibujar Caracter de dibujo que se quiere utilizar para el segmento a dibujar. Tambien nos servira para saber en que sentido dibujar dichas lineas
     */
    private void adicionarLinea(int[] puntoReferencia, String caracterADibujar)
    {
    	// Ahora, dependiendo del tamaño del digito (o visto de otra forma, del tamaño de segmento) se procede a...
    	for (int i = 1; i <= tamanoDigitos; i++)
    	{
    		// Si el caracter a dibujar es el caracter horizontal entonces
    		if (CARACTER_HORIZONTAL.equals(caracterADibujar))
    		{
    			int posicionAuxiliarX = puntoReferencia[COORDENADA_X] + i; // Calculamos el desfase a derecha en donde se debe situar el caracter horizontal i
    			matrizDeImpresion[puntoReferencia[COORDENADA_Y]][posicionAuxiliarX] = caracterADibujar; // Y quemamos/dibujamos tal caracter en la posicion Y del punto de referencia (la cual es constante) y en la posicion X que acabamos de calcular
    		}
    		else // Sino es porque se quiere emplear el caracter vertical por lo que
    		{
    			int posicionAuxiliarY = puntoReferencia[COORDENADA_Y] + i; // Esta vez se calcula el desfase hacia abajo de donde se debe situar el caracter vertical i
    			matrizDeImpresion[posicionAuxiliarY][puntoReferencia[COORDENADA_X]] = caracterADibujar; // Y quemamos/dibujamos tal caracter en la posicion Y calculada previamente y en la posicion X del punto de referencia (la cual esta vez es la constante)
    		}
    	}
    }
    
    /**
     * Metodo encargado de ir ubicando los segmentos de un numero dado en la matriz de impresion
     * @param listaSegmentos Arreglo que contiene los segmentos especificos que se deben adicionar a la matriz de impresion para formar el digito en esta
     */
    private void adicionarSegmentos(int[] listaSegmentos)
    {
    	// Entonces, por cada segmento de la lista se procede a ...
    	for (int seg : listaSegmentos)
    	{
    		// Adicionar el caracter requerido segun el numero asociado al segmento, por lo tanto:
    		switch (seg)
    		{
    		case 1: // Si el segmento a agregar es el 1 entonces se adiciona una (o varias) lineas verticales debajo de la EsquinaSuperiorIzquierda
    			adicionarLinea(posicionEsquinaSuperiorIzquierda, CARACTER_VERTICAL);
    			break;
    		case 2: // Si el segmento a agregar es el 2 entonces se adiciona una (o varias) lineas verticales debajo del CentroMedioIzquierdo
    			adicionarLinea(posicionCentroMedioIzquierdo, CARACTER_VERTICAL);
    			break;
    		case 3: // Si el segmento a agregar es el 3 entonces se adiciona una (o varias) lineas verticales debajo de la EsquinaSuperiorDerecha
    			adicionarLinea(posicionEsquinaSuperiorDerecha, CARACTER_VERTICAL);
    			break;
    		case 4: // Si el segmento a agregar es el 4 entonces se adiciona una (o varias) lineas verticales debajo del CentroMedioDerecha
    			adicionarLinea(posicionCentroMedioDerecha, CARACTER_VERTICAL);
    			break;
    		case 5: // Si el segmento a agregar es el 5 entonces se adiciona una (o varias) lineas horizontales a la derecha de la EsquinaSuperiorIzquierda
    			adicionarLinea(posicionEsquinaSuperiorIzquierda, CARACTER_HORIZONTAL);
    			break;
    		case 6: // Si el segmento a agregar es el 6 entonces se adiciona una (o varias) lineas horizontales a la derecha del CentroMedioIzquierdo
    			adicionarLinea(posicionCentroMedioIzquierdo, CARACTER_HORIZONTAL);
    			break;
    		case 7: // Si el segmento a agregar es el 7 entonces se adiciona una (o varias) lineas horizontales a la derecha de la EsquinaInferiorIzquierda
    			adicionarLinea(posicionEsquinaInferiorIzquierda, CARACTER_HORIZONTAL);
    			break;
    		default:
    			break;
			}
    	}
    }
    
    /**
     * Metodo encargado de definir los segmentos que componen un digito y a partir de los segmentos adicionar la representacion del digito a la matriz
     * @param numero Digito a agregar a la matriz de impresion
     */
    // Tomando el 8 como ejemplo, los segmentos basicos para construir todos los digitos estan asignados de la siguiente forma:
    //       ── <5
    //  1> |    | <3
    //       ── <6
    //  2> |    | <4
    //       ── <7
    private void adicionarDigito(char numero)
    {
    	// Para empezar, creamos un arreglo en el cual depositaremos cuales son los segmentos necesarios para construir el digito
        int[] listaSegmentos;
        
        // Ahora y dependiendo del numero que entro se inicializa la lista segmentos, por lo tanto ...
        switch (numero)
        {
            case '1': // Si el numero a procesar es el 1 entonces se agregan los segmentos 3 y 4
            	listaSegmentos = new int[] {3,4};
                break;
            case '2': // Si el numero a procesar es el 2 entonces se agregan los segmentos 5, 3, 6, 2 y 7
            	listaSegmentos = new int[] {5,3,6,2,7};
                break;
            case '3': // Si el numero a procesar es el 3 entonces se agregan los segmentos 5, 3, 6, 4 y 7
            	listaSegmentos = new int[] {5,3,6,4,7};
                break;
            case '4': // Si el numero a procesar es el 4 entonces se agregan los segmentos 1, 6, 3 y 4
            	listaSegmentos = new int[] {1,6,3,4};
                break;
            case '5': // Si el numero a procesar es el 5 entonces se agregan los segmentos 5, 1, 6, 4 y 7
            	listaSegmentos = new int[] {5,1,6,4,7};
                break;
            case '6': // Si el numero a procesar es el 6 entonces se agregan los segmentos 5, 1, 6, 2, 7 y 4
            	listaSegmentos = new int[] {5,1,6,2,7,4};
                break;
            case '7': // Si el numero a procesar es el 7 entonces se agregan los segmentos 5, 3 y 4
            	listaSegmentos = new int[] {5,3,4};
                break;
            case '8': // Si el numero a procesar es el 8 entonces se agregan los segmentos 1, 2, 3, 4, 5, 6 y 7
            	listaSegmentos = new int[] {1,2,3,4,5,6,7};
                break;
            case '9': // Si el numero a procesar es el 9 entonces se agregan los segmentos 1, 3, 4, 5, 6 y 7
            	listaSegmentos = new int[] {1,3,4,5,6,7};
                break;
            case '0': // Si el numero a procesar es el 10 entonces se agregan los segmentos 1, 2, 3, 4, 5 y 7
            	listaSegmentos = new int[] {1,2,3,4,5,7};
                break;
            default: // Para cualquier otro caso que no sea un numero entonces se agregan los segmentos 5, 1, 6, 2 y 7. Estos segmentos forman una E que denotaria ERROR!
            	listaSegmentos = new int[] {5,1,6,2,7};
                break;
        }
        
        // Para terminar, se invoca el metodo adicionarSegmentos para agregar/dibujar dichos segmentos a la matriz de impresion
        adicionarSegmentos(listaSegmentos);
    }
    
    /**
     * Metodo encargado de imprimir el numero especificado
     * @param numeroAImprimir Numero en cuestion a imprimir
     * @throws LCDException Esto permite lanzar las excepciones personalizadas para este aplicativo
     */
    private void imprimirNumero(String numeroAImprimir) throws LCDException
    {
    	// Antes de empezar a imprimir el numero vamos a realizar los siguientes preparativos:
    	
    	// Primero, se calcula el numero de filas que tendra cada digito. El cual al mismo tiempo va a ser el valor total de filas que van a ser necesarias para la impresion de todo el numero
    	filasPorDigito = totalFilas = (2 * tamanoDigitos) + 3;
    	
    	// Segundo, se calcula el numero de columnas que tendra cada digito
    	columnasPorDigito = tamanoDigitos + 2;
    	
    	// Tercero, a diferencia del numero total de filas, el numero total de columnas es igual a: El numero de digitos a imprimir X (El numero de columnas que consume cada numero + El espacio que se debe dejar con respecto al siguiente digito)
    	totalColumnas = numeroAImprimir.length() * (columnasPorDigito + espacioEntreDigitos);
    	
    	// Y cuarto, se instancia e inicializa la matriz de impresion del numero. En esta matriz es donde se "dibujaran" los caracteres que daran forma al numero
    	matrizDeImpresion = new String[totalFilas][totalColumnas];
    	for (int i = 0; i < totalFilas; i++)
    	{
            for (int j = 0; j < totalColumnas; j++)
            {
            	matrizDeImpresion[i][j] = " "; // Se inicializa cada celda de esta matriz con " " (espacio) porque si se inicializan las celdas con "" (String vacio) no se lograran los rellenos necesarios para dar forma a los digitos
            }
        }
    	
    	// A continuación, procedemos a separar los digitos del numero en un arreglo de caracteres
    	char[] digitos = numeroAImprimir.toCharArray();
    	
    	// Y se crea una variable que llamaremos pivoteEnX. Su mision es almacenar la columna desde la cual debera imprimirse cada digito, en otras palabras, es el desfase que se debe generar por cada impresion
    	int pivoteEnX = 0; // Comienza en cero para que el primer digito inicie desde tal columna
    	
    	// Ahora, para cada digito del numero se procede a ...
    	for (char digito : digitos)
    	{
    		// Revisar Si el caracter en cuestion es un digito, sino lo es entonces
    		if(!Character.isDigit(digito))
    		{
    			// Lanzamos la excepcion correspondiente
        		throw new LCDException(String.format("El numero %s contiene el caracter '%c', el cual no es un numero!", numeroAImprimir, digito));
        		// Nota: Si pasa aqui debe ser porque el numero tiene un caracter de signo (+ o -) al principio y que no es permitido
    		}
    		
    		// Luego, calculamos los puntos de referencia con los cuales nos apoyaremos para la ubicación espacial de los caracteres que representaran el digito dentro de la matriz de impresion
    		/* Para entender como se calculan estas coordenas, debemos plantearnos unas posiciones relativas que son las siguientes:
    		 * Esquina Superior Izquierda  ---> La denotaremos: ESI
    		 * Centro Medio Izquierdo      ---> La denotaremos: CMI
    		 * Esquina Inferior Izquierda  ---> La denotaremos: EII
    		 * Esquina Superior Derecha    ---> La denotaremos: ESD
    		 * Centro Medio Derecho        ---> La denotaremos: CMD
    		 * 
    		 * Lo que graficamente se podria ver así:
    		 * 
    		 * NOTA: Las Y son las filas y las X son las columnas. Ademas, se asume que el espacio entre digitos es de 1
    		 * 
    		 *            1er numero    Espacio   2do numero       ...        Y asi sucesivamente
    		 *  Columna    0   1   2      3        4   5   6  
    		 * Fila      +───+───+───+           +───+───+───+                
    		 *   0       |ESI|   |ESD|    |      |ESI|   |ESD|
    		 *           +───+───+───+           +───+───+───+
    		 *   1       |   |   |   |    |      |   |   |   |
    		 *           +───+───+───+           +───+───+───+
    		 *   2       |CMI|   |CMD|    |      |CMI|   |CMD|
    		 *           +───+───+───+           +───+───+───+
    		 *   3       |   |   |   |    |      |   |   |   |
    		 *           +───+───+───+           +───+───+───+
    		 *   4       |EII|   |   |    |      |EII|   |   |
    		 *           +───+───+───+           +───+───+───+
    		 *  
    		 *           pivoteEnX = 0           pivoteEnX = pivoteEnX
    		 *                                               + columnasPorDigito
    		 *                                               + espacioEntreDigitos
    		 *                                             = 4
    		 *  Notese entonces que las posiciones mencionadas se repiten a ciertos intervalos y esto nos permitira saber desde donde se pueden comenzar a dibujar cada una de las 7 secciones que hay predefinidas para los digitos
    		 */
    		
    		// Cada una de esta posiciones se calcularia asi:
            posicionEsquinaSuperiorIzquierda[COORDENADA_Y] = 0;
            posicionEsquinaSuperiorIzquierda[COORDENADA_X] = 0 + pivoteEnX;

            posicionCentroMedioIzquierdo[COORDENADA_Y] = (filasPorDigito / 2);
            posicionCentroMedioIzquierdo[COORDENADA_X] = 0 + pivoteEnX;

            posicionEsquinaInferiorIzquierda[COORDENADA_Y] = (filasPorDigito - 1);
            posicionEsquinaInferiorIzquierda[COORDENADA_X] = 0 + pivoteEnX;
            
            posicionCentroMedioDerecha[COORDENADA_Y] = (filasPorDigito / 2);
            posicionCentroMedioDerecha[COORDENADA_X] = (columnasPorDigito - 1) + pivoteEnX;

            posicionEsquinaSuperiorDerecha[COORDENADA_Y] = 0;
            posicionEsquinaSuperiorDerecha[COORDENADA_X] = (columnasPorDigito - 1) + pivoteEnX;
            
            // Una vez se terminan de calcular las posiciones del digito actual, se actualiza la posicion del pivote en X para el siguiente digito a dibujar
            pivoteEnX = pivoteEnX + columnasPorDigito + espacioEntreDigitos;
    		
    		// Ahora, invocamos el metodo adicionarDigito para que este ya se encargue de agregar el digito a la matriz de impresion
    		adicionarDigito(digito);
    	}
    	
    	// Una vez se terminan de dibujar todos los digitos del numero en memoria (o sea, en la variable matrizDeImpresion) se prosigue con imprimir el numero completo por consola
    	for (int i = 0; i < totalFilas; i++)
    	{
            for (int j = 0; j < totalColumnas; j++)
            {
                System.out.print(matrizDeImpresion[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * Metodo encargado de procesar la entrada (comando) que contiene el size del segmento de los digitos y los digitos a imprimir
     * @param comandoADibujar Entrada que contiene el size del segmento de los digitos y el numero a imprimir
     * @param espacioEntreDigitos Espacio que se va a dejar entre los digitos
     * @throws LCDException Esto permite lanzar las excepciones personalizadas para este aplicativo
     */
    public void procesarComando(String comandoADibujar, int espacioEntreDigitos) throws LCDException
    {
    	// Para empezar, primero revisamos que el comando si tenga el caracter separador. En caso que no, entonces
    	if (!comandoADibujar.contains(CARACTER_SEPARADOR))
    	{
    		// Lanzamos la excepcion correspondiente
    		throw new LCDException(String.format("El comando %s no contiene el caracter separador '%s'", comandoADibujar, CARACTER_SEPARADOR));
    	}
    	
    	// Si pasa la validacion anterior, se separa/tokeniza el comando para facilitar su analisis
    	String[] parametrosComando = comandoADibujar.split(CARACTER_SEPARADOR);
    	
    	// Ahora, si el numero de parametros del comando es diferente de dos entonces este no tiene el formato esperado por lo que
    	if (parametrosComando.length != 2)
    	{
    		// Lanzamos una excepcion al respecto
    		throw new LCDException(String.format("El comando %s no tiene el formato tamaño,numeroAImprimir", comandoADibujar));
    	}
    	
    	// Si pasa aqui es porque el comando si tiene el formato indicado y se procede a ...
    	
    	// Revisar que el primer parametro (Size) del comando si sea un numero, sino lo es entonces
    	if (!esNumero(parametrosComando[0]))
    	{
    		// Lanzamos una excepcion al respecto
    		throw new LCDException(String.format("El parametro Size [%s] no es un numero", parametrosComando[0]));
    	}
    	
    	// En caso que si, entonces se castea dicho parametro y se inicializa la variable global tamanoDigitos para que esta clase sepa de ahora en adelante con que tamaño debe imprimir los digitos
    	tamanoDigitos = Integer.parseInt(parametrosComando[0]);
    	
    	// Luego, se valida que el tamaño este dentro del rango esperado, Sino lo esta entonces
    	if(tamanoDigitos <1 || tamanoDigitos >10)
    	{
    		// Lanzamos una excepcion al respecto
    		throw new LCDException(String.format("El parametro Size [%s] debe estar entre 1 y 10", parametrosComando[0]));
    	}
    	
    	// Por otra parte, tambien revisamos que el segundo parametro (Number) igualmente sea un numero, en caso de no serlo entonces
    	if (!esNumero(parametrosComando[1]))
    	{
    		// Lanzamos una excepcion al respecto
    		throw new LCDException(String.format("El parametro Number [%s] no es un numero", parametrosComando[1]));
    	}
    	
    	// Finalmente y si se pasaron las validaciones anteriores se procede a ...
    	this.espacioEntreDigitos = espacioEntreDigitos; // Inicializar la variable global espacioEntreDigitos para que esta clase sepa cuanto espacio respetar entre la impresion de cada digito
    	imprimirNumero(parametrosComando[1]); // Y se manda a imprimir el numero del comando
    }
    
   /**
    * Metodo para validar que una cadena de caracteres si corresponde a un numero
    * @param cadenaAEstudiar Cadena de caracteres que se va a analizar para verificar si es un numero o no
    * @return Se retorna VERDADERO en caso que la cadena si sea un numero y FALSO en caso contario
    */
   public static boolean esNumero(String cadenaAEstudiar)
   {
       try // Se intenta ...
       {
           Integer.parseInt(cadenaAEstudiar); // Castear la cadena de caracteres a un numero entero
           return true; // Si pasa el casteo, entonces retorno VERDADERO
       }
       catch (NumberFormatException ex) // En caso de error ...
       {
           return false; // Retorno FALSO
       }
   }
}
