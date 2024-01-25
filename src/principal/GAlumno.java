package principal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Se trata de la clase que gestiona todas las operaciones del CRUD de la clase
 * Alumno
 */
public class GAlumno {
	// Fichero donde se escribe todo lo relacionad con la clase Alumno
	public static final String FICHERO = ".\\data\\alumnos.ser";

	/**
	 * Devuelve un ArrayList de alumnos leidos desde el fichero
	 * 
	 * @return
	 */
	public static ArrayList<Alumno> leerAlumnos() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> lista = new ArrayList<>();
		Utiles.ficheroExiste(FICHERO);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(FICHERO);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			while (true) {
				Alumno al = (Alumno) ois.readObject(); // leemos el fichero
				lista.add(al); // agregamos lectura a la arraylist
			}
		} catch (EOFException ex) {

		} catch (ClassNotFoundException | IOException e) {
			// e.printStackTrace();
		} finally { // cerramos el fichero
			try {
				if (ois != null) {
					ois.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	/**
	 * Comprueba que no existen alumnos con el mismo nombre+ape1+ape2
	 * 
	 * @param alumno
	 * @return
	 */
	public static boolean duplicados(String alumno) {
		ArrayList<Alumno> alumnos = leerAlumnos();
		boolean existe = false;
		for (int i = 0; i < alumnos.size(); i++) {
			if (alumnos.get(i).getRealName().equals(alumno)) {
				System.out.println(Utiles.TAB + "El alumno ya se encuentra registrado");
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * Agreaga un Nuevo Alumno al Fichero
	 * 
	 * @param alumno
	 */
	public static void escribirAlumno(Alumno alumno) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> lista = leerAlumnos();
		FileOutputStream fis = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		File fileEx = new File(FICHERO);
		if (!fileEx.exists()) {
			try {
				fileEx.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fis = new FileOutputStream(FICHERO);
			bos = new BufferedOutputStream(fis);
			oos = new ObjectOutputStream(bos);

			for (int i = 0; i < lista.size(); i++) {
				oos.writeObject(lista.get(i));
			}
			oos.writeObject(alumno);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { // cerramos el fichero
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Escribe un ArrayList de alumnos en el fichero
	 * 
	 * @param alumnos
	 */
	public static void escribirAlumno(ArrayList<Alumno> alumnos) {
		Utiles.ficheroExiste(FICHERO);
		FileOutputStream file = null;
		BufferedOutputStream buffer = null;
		ObjectOutputStream output = null;

		try {
			file = new FileOutputStream(FICHERO, false); // Abre el archivo en modo de escritura (false para
															// sobrescribir)
			buffer = new BufferedOutputStream(file);
			output = new ObjectOutputStream(buffer);

			for (Alumno alumno : alumnos) {
				output.writeObject(alumno);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (buffer != null) {
					buffer.close();
				}
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Desde el fichero lee los numExp de los alumnos y devuelve el mayor de estos
	 * 
	 * @return
	 */
	public static int returnCode() {
		Utiles.ficheroExiste(FICHERO);
		int retorno = 0;
		ArrayList<Alumno> alumnos = leerAlumnos();
		ArrayList<Integer> codigo = new ArrayList<>();
		if (alumnos == null) {
			retorno = 100;
		} else if (alumnos.size() > 0) {
			for (Alumno alum : alumnos) {
				codigo.add(alum.getNumExp());
			}
			retorno = (Collections.max(codigo));
			retorno++;
		} else {
			retorno = 100;
		}
		return retorno;
	}

	public static void mostrarAlumnos() {
		ArrayList<Alumno> alumnos = leerAlumnos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
		} else {
			for (int i = 0; i < alumnos.size(); i++) {
				System.out.println(Utiles.TAB + alumnos.get(i));
			}
		}
	}

	/**
	 * Busca en el fichero a un alumno por nombre o por apellidos o por apellido
	 * 
	 * @param busqueda
	 * @param argumento
	 * @return
	 */
	public static Alumno resultadoBusqueda(String busqueda, int argumento) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> coincidencias = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(FICHERO); ObjectInputStream ois = new ObjectInputStream(fis)) {
			while (true) {
				try {
					Alumno alumno = (Alumno) ois.readObject();
					if (argumento == 0) {
						if (alumno.getNombre().equalsIgnoreCase(busqueda)) {
							coincidencias.add(alumno);
							// System.out.print(TAB+coincidencias.size());
						}
						/// aqui falta la mita del metodo
					} else if (argumento == 1) {
						if (busqueda.contains(" ")) {
							String[] nueva = busqueda.split(" ");
							String ape1 = nueva[0];
							String ape2 = nueva[1];
							if (alumno.getApe1().equalsIgnoreCase(ape1) && alumno.getApe2().equalsIgnoreCase(ape2)) {
								coincidencias.add(alumno);
							}
						} else {
							if (alumno.getApe1().equalsIgnoreCase(busqueda)
									|| alumno.getApe2().equalsIgnoreCase(busqueda)) {
								coincidencias.add(alumno);
							}
						}
					}
				} catch (EOFException e) {
					break; // Fin del archivo // este catch coge lo de abajo
				}
			}
			if (coincidencias.size() == 0) {
				System.out.println(Utiles.TAB + "No existen coincidencias");
				return null;
			} else if (coincidencias.size() == 1) {
				System.out.println(Utiles.TAB + coincidencias.get(0));
				return coincidencias.get(0);
			} else {
				System.out.println(Utiles.TAB + "Existen varias coincidencias, indique a cual se refiere");
				int i;
				for (i = 0; i < coincidencias.size(); i++) {
					System.out.println(Utiles.TAB + " Pulse--" + i + ": " + coincidencias.get(i).toString());
				}
				int respuesta = Utiles.returnNumber();
				if (respuesta == -99) {
					// Esto es un fallo
					System.out.println(Utiles.TAB + "No ha introducido un numero.");
				} else {
					if ((respuesta >= 0) && (respuesta < coincidencias.size())) {
						System.out.println(Utiles.TAB + coincidencias.get(respuesta));
						return coincidencias.get(respuesta);
					} else {
						System.out.println(Utiles.TAB + "Índice fuera de rango.");
						return null;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no se ha encontrado: " + e.getMessage());
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
		System.out.println(Utiles.TAB + "No existen coincidencias");
		return null; // Si no se encuentra el alumno, retornamos null
	}

	/**
	 * Metodo de apoyo a para la busqueda de un alumno
	 * 
	 * @return
	 */
	public static Alumno busqueda() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
			return null;
		} else {
			System.out.print(Utiles.TAB + "¿Desea buscar por Nombre o por Apellidos?\n" + Utiles.TAB
					+ "Pulse--0 para buscar por nombre\n" + Utiles.TAB + "Pulse--1 para buscar por apellidos\n"
					+ Utiles.TAB + "Opcion: ");
			int respuesta = Utiles.returnNumber();
			if (respuesta == 0) {
				System.out.print(Utiles.TAB + "Introduzca el nombre del alumno que busca: ");
				String nombre = Utiles.formateoNombres(Utiles.entradaTeclado()).toLowerCase();
				return resultadoBusqueda(nombre, 0);
			} else if (respuesta == 1) {
				System.out.print(Utiles.TAB + "Introduzca los apellidos del alumno que busca: ");
				String apellidos = Utiles.formateoNombres(Utiles.entradaTeclado());
				return resultadoBusqueda(apellidos, 1);
			} else {
				System.out.println(Utiles.TAB + "No ha introducido una opcion valida"); // esto es un fallo
			}

		}
		return null;
	}

	/**
	 * Escribe en el fichero una modificacion de los objetos Alumno
	 * 
	 * @param modificado
	 * @param modificacion
	 * @param argumento=0  elimnacion de un alumno
	 * @param argumento=1  modificacion nombre
	 * @param argumento=2  modificacion ape1
	 * @param argumento=3  modificacion ape2
	 * @param argumento=4  modificacion tlf
	 * @param argumento=5  modificacion direccion
	 * @param argumento=6  modificacion fecha
	 * @param argumento=7  modificacion agrega un curso del alumno
	 * @param argumento=8  modificacion elimina un curso del alumno
	 */
	public static void modificacionFichero(Alumno modificado, String modificacion, int argumento) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> cursos = GCurso.leerCursos();
		ArrayList<Alumno> alumnos = leerAlumnos();
		for (int i = 0; i < alumnos.size(); i++) {
			if (alumnos.get(i).getRealName().equalsIgnoreCase(modificado.getRealName())) {
				switch (argumento) {
				case 0: // eliminacion
					alumnos.remove(alumnos.get(i));
					break;
				case 1: // nombre
					alumnos.get(i).setNombre(modificacion);
					break;
				case 2: // ape1
					alumnos.get(i).setApe1(modificacion);
					break;
				case 3: // ape2
					alumnos.get(i).setApe2(modificacion);
					break;
				case 4: // telefono
					alumnos.get(i).setTelf(modificacion);
					break;
				case 5: // direccion
					alumnos.get(i).setDir(modificacion);
					break;
				case 6: // fecha
					alumnos.get(i).setFecha(modificacion);
					break;
				case 7: // agregar curso
					boolean existe = false;
					Curso curso = null;
					for (int j = 0; j < cursos.size(); j++) {
						if (cursos.get(j).getCodigo().equals(modificacion)) {
							curso = cursos.get(j);
							existe = true;
						}
					}
					if (existe) {
						boolean esta = false;
						for (int j = 0; j < alumnos.get(i).getCursos().size(); j++) {
							if (alumnos.get(i).getCursos().get(j).equals(curso)) {
								esta = true;
							}
						}
						if (!esta) {
							alumnos.get(i).addCurso(curso);
							System.out.println(/**/"\t\t[---Alumno matriculado con exito---]");
						} else {
							System.out.println(Utiles.TAB + "---El alumno " + alumnos.get(i).getNombre()
									+ ", ya esta matriculado en el curso " + modificacion);
						}
					} else {
						System.out.println("---El curso " + modificacion + ", no existe.");
					}
					break;

				case 8: // StringCursos
					boolean real = false;
					Curso eliminar = null;
					for (int j = 0; j < cursos.size(); j++) {
						if (cursos.get(j).getCodigo().equals(modificacion)) {
							eliminar = cursos.get(j);
							real = true;
						}
					}
					if (real) {
						boolean esta = false;
						for (int j = 0; j < alumnos.get(i).getCursos().size(); j++) {
							if (alumnos.get(i).getCursos().get(j).equals(eliminar)) {
								esta = true;
							}
						}
						if (!esta) {
							System.out.println(Utiles.TAB + "---El alumno " + alumnos.get(i).getNombre()
									+ ", no esta matriculado en el curso no se puede desmatricular" + modificacion);
						} else {
							alumnos.get(i).removeCurso(eliminar);
							System.out.println(/**/"\t\t[--Alumno desmatriculado con exito-]");
						}

					} else {
						System.out.println(Utiles.TAB + "---El alumno  curso " + modificacion + " no existe");
					}
					break;
				default:
					break;
				}
			}
		}
		escribirAlumno(alumnos);
	}

	/**
	 * Metodo de apoyo de modificacionFichero(), gestiona y pide la informacion
	 * necesaria por teclado para la modifcacion de un Alumno
	 */
	public static void modificacion() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
		} else {
			Alumno alumno = busqueda();
			if (alumno != null) {
				System.out.println("\n\t\t[--- MENU MODIFICAR ALUMNOS ----\n\t\t" + "[ 1. Nombre\n\t\t"
						+ "[ 2. Primer apellido\n\t\t" + "[ 3. Segundo apellido\n\t\t" + "[ 4. Telefono\n\t\t"
						+ "[ 5. Direccion\n\t\t" + "[ 6. Fecha de Nacimiento\n\t\t" + "[ 0. Salir");

				System.out.print("\t\t[--- > Elige que modificar: ");
				int opcion = Utiles.returnNumber();
				switch (opcion) {
				case 1:
					System.out.println("\n\n\t\t[----//// MODIFICAR NOMBRE ////----]");
					System.out.println(
							"\n\n\t\t[El nombre del alumno es: " + alumno.getNombre() + ", introduzca el nuevo nombre");
					String name = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (!duplicados(name + alumno.getApe1() + alumno.getApe2())) {
						if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
							modificacionFichero(alumno, name, 1);
						}
					}
					System.out.println(/**/"\t\t[----------------------------------]");
					break;
				case 2:
					System.out.println("\n\n\t\t[----//// MODIFICAR APELLIDO 1 ////----]");
					System.out.println("\n\n\t\t[El Primer Apellido del alumno es: " + alumno.getApe1()
							+ ", introduzca el nuevo Apellido");
					String ape1 = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (!duplicados(ape1 + alumno.getApe1() + alumno.getApe2())) {
						if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
							modificacionFichero(alumno, ape1, 1);
						}
					}
					System.out.println(/**/"\t\t[--------------------------------------]");
					break;
				case 3:
					System.out.println("\n\n\t\t[----//// MODIFICAR APELLIDO 2 ////----]");
					System.out.println("\n\n\t\t[El Segundo Apellido del alumno es: " + alumno.getApe2()
							+ ", introduzca el nuevo Apellido");
					String ape2 = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (!duplicados(ape2 + alumno.getApe1() + alumno.getApe2())) {
						if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
							modificacionFichero(alumno, ape2, 1);
						}
					}
					System.out.println(/**/"\t\t[--------------------------------------]");
					break;
				case 4:
					System.out.println("\n\n\t\t[----//// MODIFICAR TELEFONO ////----]");
					System.out.println("\\n\n\t\t[El Telefono del alumno es: " + alumno.getTelf()
							+ ", introduzca el nuevo Telefono");
					String telef = Utiles.entradaTeclado();
					if (Utiles.comprobarTelefono(telef)) {
						System.out.println(Utiles.TAB + Utiles.OK + "Telefono introducido con exito " + Utiles.RIGHT);
					}
					if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
						modificacionFichero(alumno, telef, 4);
					}
					System.out.println(/**/"\t\t[------------------------------------]");
					break;
				case 5:
					System.out.println("\n\n\t\t[----//// MODIFICAR DIRECCION ////----]");
					System.out.println("\"\n\n\t\t[La direccion del alumno es: " + alumno.getDir()
							+ ", introduzca la nueva direccion");
					String dire = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
						modificacionFichero(alumno, dire, 5);
					}
					System.out.println(/**/"\t\t[-------------------------------------]");
					break;
				case 6:
					System.out.println("\n\n\t\t[----//// MODIFICAR FECHA NAC. ////----]");
					System.out.println(
							"\n\n\t\t[La fecha del alumno es: " + alumno.getFecha() + ", introduzca la nueva fecha");
					String fecha = Utiles.entradaTeclado();
					if (Utiles.comprobarFecha(fecha)) {
						if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
							modificacionFichero(alumno, fecha, 6);
						}
					}
					System.out.println(/**/"\t\t[--------------------------------------]");
					break;
				case 0:
					System.out.println("\t\tSaliendo...");
					break;
				default:
					System.out.println("\n\tEntrada no valida.");
					break;
				}
			}
		}
	}

	/**
	 * Metodo de apoyo de modificacionFichero(), gestiona y pide la informacion
	 * necesaria por teclado para la eliminacion de un Alumno
	 */
	public static void eliminacion() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
		} else {
			Alumno alumno = busqueda();
			if (alumno != null) {
				if (Utiles.yesNo(Utiles.TAB + "Desea realizar la eliminacion del alumno " + alumno.getNombre() + "?\n"
						+ Utiles.TAB + "[S/N]", "eliminacion")) {
					modificacionFichero(alumno, "", 0);
					System.out.println(/**/"\t\t[----Alumno eliminado con exito----]");
				}
			} else {
				System.out.println(Utiles.TAB + "Alumno no encontrado.");
			}
		}
	}

	/**
	 * metodo que crea un Nuevo Alumno
	 */
	public static void alta() {
		Utiles.ficheroExiste(FICHERO);
		int contFallos = 0;
		boolean fin = false;
		String numExpediente = "" + returnCode();
		String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		String telefono = "";
		String direccion = "";
		String fecha = "";

		do {
			System.out.print(Utiles.TAB + "Nombre: ");
			nombre = Utiles.formateoNombres(Utiles.entradaTeclado());
			if (nombre.isEmpty()) {
				contFallos = Utiles.fallo(contFallos, "Alta");
			} else {
				System.out.println(Utiles.TAB + Utiles.OK + "Nombre introducido con exito " + Utiles.RIGHT);
				fin = true;
			}
			if (contFallos == 5) {
				fin = true;
			}
		} while (!fin);

		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Primer Apellido: ");
				apellido1 = Utiles.formateoNombres(Utiles.entradaTeclado());
				if (apellido1.isEmpty()) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					System.out
							.println(Utiles.TAB + Utiles.OK + "Primer Apellido introducido con exito " + Utiles.RIGHT);
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}

		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Segundo Apellido: ");
				apellido2 = Utiles.formateoNombres(Utiles.entradaTeclado());
				if (apellido2.isEmpty()) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					System.out
							.println(Utiles.TAB + Utiles.OK + "Segundo Apellido introducido con exito " + Utiles.RIGHT);
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}
		if (duplicados(nombre + apellido1 + apellido2)) {
			contFallos = 5;
		}
		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Telefono: ");
				telefono = Utiles.entradaTeclado();
				if (!Utiles.comprobarTelefono(telefono)) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					System.out.println(Utiles.TAB + Utiles.OK + "Telefono introducido con exito " + Utiles.RIGHT);
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}

		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Direccion: ");
				direccion = Utiles.formateoNombres(Utiles.entradaTeclado());
				if (direccion.isEmpty()) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					System.out.println(Utiles.TAB + Utiles.OK + "Direccion introducida con exito " + Utiles.RIGHT);
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}
		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Fecha de Nacimiento: ");
				fecha = Utiles.entradaTeclado();
				if (fecha.isEmpty() || !Utiles.comprobarFecha(fecha)) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}
		System.out.println(Utiles.TAB);
		if (contFallos < 5) {
			String move = "\n" + Utiles.TAB + (char) 16;
			if (Utiles.yesNo(Utiles.TAB + "Desea dar de alta un alumno con estos datos?\n" + Utiles.TAB + "--------"
					+ move + "Numero de Expediente: " + numExpediente + move + "Nombre: " + nombre + move
					+ "Apellidos: " + apellido1 + " " + apellido2 + move + "Telefono: " + telefono + move
					+ "Direccion: " + direccion + move + "Fecha de nacimiento: " + fecha + "\n" + Utiles.TAB
					+ "-------------->[S/N]: ", "Alta")) {
				// System.out.println("Alumno creaado-------------");
				Alumno a1 = new Alumno(Integer.parseInt(numExpediente), nombre, apellido1, apellido2, fecha,
						telefono, direccion);
				escribirAlumno(a1);
			}
		}
	}

	/**
	 * matricula un alumno en un curso
	 */
	public static void matricular() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		if (alumnos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen alumnos aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !alumnos.isEmpty()) {
			Alumno alumno = busqueda();
			if (alumno != null) {
				Curso curso = GCurso.busqueda();
				if (curso != null) {
					modificacionFichero(alumno, curso.getCodigo(), 7);
				} else {
					System.out.println(Utiles.TAB + "**Curso no encontrado**");
				}
			} else {
				System.out.println(Utiles.TAB + "**Alumno no encontrado**");
			}
		}
	}

	/**
	 * desmatricula a un alumno de un curso
	 */
	public static void desMatricular() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		if (alumnos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen alumnos aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !alumnos.isEmpty()) {
			Alumno alumno = busqueda();
			if (alumno != null) {
				if (alumno.getCursos().isEmpty()) {
					System.out.println(Utiles.TAB + "**El alumno no esta inscrito en ningun curso**");
				} else {
					Curso curso = GCurso.busqueda();
					if (curso != null) {
						modificacionFichero(alumno, curso.getCodigo(), 8);

					} else {
						System.out.println(Utiles.TAB + "**Curso no encontrado**");
					}
				}
			} else {
				System.out.println(Utiles.TAB + "**Alumno no encontrado**");
			}
		}
	}

	/**
	 * Muestra por pantalla todos los Alumnos matriculados en un curso concreto
	 */
	public static void mostrarAlumnoCurso() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Alumno> alumnos = leerAlumnos();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		int registrados = 0;
		if (alumnos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No existen alumnos aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No existen cursos aun**");
		}
		if (!cursos.isEmpty() && !alumnos.isEmpty()) {
			Curso curso = GCurso.busqueda();
			if (curso != null) {
				for (Alumno alumno : alumnos) {
					if (alumno.getCursos().contains(curso)) {
						System.out.println(Utiles.TAB + alumno.toString());
						registrados++;
					}
				}
			}
			if (registrados == 0) {
				System.out.println(Utiles.TAB + "**No existen alumnos matriculados en el curso aun**");
			}
		}

	}

}
