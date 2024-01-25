package principal;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utiles {
	// Son todas las modificaciones de salida de pantalla
	public static final String OK = "\t\t\t" + (char) 5 + " ";
	public static final String RIGHT = " " + (char) 16 + " " + (char) 16 + " " + (char) 16;
	public static final String TAB = "\t\t[ ";
	public static final String MAL = "\t\t\t" + (char) 15 + " ";
	public static final String move = "\n" + Utiles.TAB + (char) 16;
	public static final Scanner sc = new Scanner(System.in);

	/**
	 * Se trata del unico metodo que maneja la entrada de teclado
	 * 
	 * @return String
	 */
	public static String entradaTeclado() {
		String entrada = sc.nextLine().trim();
		if (entrada.isEmpty() || entrada.matches("\\s+")) {
			System.out.println(TAB + MAL + "No introduzca cadenas vacias");
		}
		return entrada;
	}

	/**
	 * Se trata de un metodo que se encarga de cerrar el flujo de entrada del
	 * programa
	 */
	public static void cerrarTeclado() {
		sc.close();
	}

	/**
	 * Se trata de un metodo que se asegura que se ha introducido un int
	 * 
	 * @return -99 si no es un int valido
	 * @return int valor
	 */
	public static int returnNumber() {
		String respuesta = entradaTeclado();
		if (respuesta != null && respuesta.matches("\\d+")) {
			return Integer.parseInt(respuesta);
		} else {
			return -99;
		}
	}

	/**
	 * Se trata de un metodo que da un formato correcto a los nombres, eliminando
	 * espacios inecesarios y poniendo letra mayuscula en la primera de cada palabra
	 * 
	 * @param texto
	 * @return String textoFormateado
	 */
	public static String formateoNombres(String texto) {
		String[] palabras = texto.trim().split("\\s+");
		for (int i = 0; i < palabras.length; i++) {
			if (!palabras[i].isEmpty()) {
				palabras[i] = Character.toUpperCase(palabras[i].charAt(0)) + palabras[i].substring(1).toLowerCase();
			}
		}
		return String.join(" ", palabras);
	}

	/**
	 * Metodo que maneja los fallos
	 * 
	 * @param fallos
	 * @param tipo
	 * @return numero de fallos
	 */
	public static int fallo(int fallos, String tipo) {
		fallos++;
		if (fallos == 5) {
			System.out.println(TAB + MAL + tipo + " aboratada\n" + TAB + "\tSaliendo.....");
		} else {
			System.out.println(TAB + MAL + fallos + "/5 errores antes de salir");
		}
		return fallos;
	}

	/**
	 * Metodo que se encarga de preguntas con dos unicas opciones
	 * 
	 * @param informacion
	 * @param modificacion
	 * @return true= afirmativo
	 * @return false = negativo
	 */
	public static boolean yesNo(String informacion, String modificacion) {
		System.out.print(informacion);
		boolean fin = false;
		int fallos = 0;
		do {
			String opcion = entradaTeclado();
			if (opcion.equalsIgnoreCase("S")) {
				System.out.println(TAB + "\n" + TAB + "\t" + modificacion + " realizada con exito" + RIGHT);
				return true;
			} else if (opcion.equalsIgnoreCase("N")) {
				System.out.println(TAB + "\n" + TAB + "\t" + modificacion + " cancelada" + RIGHT);
				return false;
			} else {
				fallos = fallo(fallos, modificacion);
				System.out.print(TAB + "[S/N]:");
				if (fallos == 5) {
					fin = true;
				}
			}
		} while (!fin);
		return false;
	}

	/**
	 * metodo que se encarga de comprobar si la fecha introducida es correcta en
	 * formato y ademas si es lógica
	 * 
	 * @param texto
	 * @return true esta bien
	 * @return false esta mal
	 */
	public static boolean comprobarFecha(String texto) {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			LocalDate fechaPropuesta = LocalDate.parse(texto, formatoFecha);
			LocalDate fechaActual = LocalDate.now();
			if (fechaPropuesta.isAfter(fechaActual)) {
				System.out.println(TAB + MAL + "La fecha propuesta es posterior a la fecha actual");
				return false;
			} else {
				System.out.println(TAB + OK + "Fecha introducida con exito " + RIGHT);
				return true;
			}
		} catch (DateTimeParseException e) {
			System.out.println(TAB + MAL + "El formato de fecha no es correcto, recuerde XX/XX/XXXX");
			return false;
		}
	}

	/**
	 * metodo que se encarga de comprobar si el telefono introducido es correcta en
	 * formato 000000000
	 * 
	 * @param texto
	 * @return true esta bien
	 * @return false esta mal
	 */
	public static Boolean comprobarTelefono(String texto) {
		if (texto.length() == 9 && texto.matches("\\d+")) {// verifica la longitud de la cadena y que todos sus digitos
			return true;
		} else {
			System.out.println(TAB + MAL + "El formato del teléfono no es correcto o no tiene 9 caracteres");
			return false;
		}
	}

	/**
	 * metodo que se encarga de comprobar si el dni introducido es correcta en
	 * formato 00000000A
	 * 
	 * @param texto
	 * @return true esta bien
	 * @return false esta mal
	 */
	public static Boolean comprobarDNI(String texto) {
		if (texto.length() == 9 && Character.isAlphabetic(texto.charAt(8))
				&& texto.substring(0, 8).chars().allMatch(Character::isDigit)) {
			return true;
		}

		System.out.println(TAB + MAL + "El formato del DNI no es correcto. Recuerde: 99999999A");
		return false;
	}

	/**
	 * Metodo de apoyo al menu principal
	 * 
	 * @param identidad
	 * @return
	 */
	public static int menuCRUD(String identidad) {
		String respuesta = entradaTeclado();
		int resultado = -1;
		if (respuesta.length() == 1) {
			if (Character.isDigit(respuesta.charAt(0))) {
				resultado = Integer.parseInt(respuesta);
				if (resultado < 0 && resultado > 5) {
					System.out.println(TAB + MAL + "No ha introducido un numero correcto");
					resultado = -1;
				} else {
					System.out.println(TAB + MAL + "No ha introducido un numero");
				}
			} else {
				System.out.println(TAB + MAL + "No ha introducido una opcion valida");
			}
		}
		return resultado;
	}

	/**
	 * Metodo de apoyo al formateo de textos
	 * 
	 * @param texto
	 * @return
	 */
	public static String revisionTexto(String texto) {
		String[] palabras = texto.split(" ");
		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < palabras.length; i++) {
			String palabra = palabras[i];
			if (palabra.isEmpty()) {
				resultado.append(" ");
				continue;
			}
			char primeraLetra = palabra.charAt(0);
			boolean esCompuestaPorMayusculas = palabra.chars().allMatch(Character::isUpperCase);
			if (i == 0 || esCompuestaPorMayusculas) {
				resultado.append(palabra);
			} else {
				String palabraFormateada = Character.toUpperCase(primeraLetra) + palabra.substring(1).toLowerCase();
				resultado.append(palabraFormateada);
			}
			if (i < palabras.length - 1) {
				resultado.append(" ");
			}
		}
		return resultado.toString();
	}

	/**
	 * Metodo que comprueba que la estructura de ficheros y directorios exista y de
	 * no ser asi la crea
	 * 
	 * @param fichero
	 * @return
	 */
	public static boolean ficheroExiste(String fichero) {
		File dir = new File(".\\data");
		if (!dir.exists() || dir.isFile()) {
			dir.mkdir();
		}
		File file = new File(fichero);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}
	}
}
