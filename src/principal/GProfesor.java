package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Se trata de la clase que gestiona todas las operaciones del CRUD de la clase
 * Profesor
 */
public class GProfesor {
	// Fichero donde se guardaran todos los datos de la clase Profesor
	public static final String FICHERO = ".\\data\\profesores.dat";

	/**
	 * Metodo que comprueba que un DNI de un profesor no este escrito en el fichero
	 * ya
	 * 
	 * @param comparado
	 * @return
	 */
	public static boolean duplicadosDni(String comparado) {
		Utiles.ficheroExiste(FICHERO);
		try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO))) {
			while (true) {
				String dni = dis.readUTF();
				if (dni.equalsIgnoreCase(comparado)) {
					return true;
				}
				dis.readUTF();
				dis.readUTF();
				dis.readUTF();
				int numCursos = dis.readInt();
				for (int i = 0; i < numCursos; i++) {
					dis.readUTF();
				}
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Devuelve un ArrayList con los profesores escritos en el fichero
	 * 
	 * @return
	 */
	public static ArrayList<Profesor> listarProfesor() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = new ArrayList<>();
		try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO))) {
			while (true) {
				String dni = dis.readUTF();
				String nombre = dis.readUTF();
				String direccion = dis.readUTF();
				String telefono = dis.readUTF();
				Profesor profesor = new Profesor(dni, nombre, direccion, telefono);
				int numCursos = dis.readInt();
				ArrayList<Curso> curs = GCurso.leerCursos();
				for (int i = 0; i < numCursos; i++) {
					String codigoCurso = dis.readUTF();
					for (int j = 0; j < curs.size(); j++) {
						if (codigoCurso.equals(curs.get(j).getCodigo())) {
							profesor.agregarCurso(curs.get(j));
						}
					}
				}
				profesores.add(profesor);
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return profesores;
	}

	/**
	 * Recibe un alumno y lo agrega al fichero
	 * 
	 * @param profesor
	 */
	public static void escribirProfesor(Profesor profesor) {
		Utiles.ficheroExiste(FICHERO);
		if (!duplicadosDni(profesor.getDni())) {
			try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHERO, true))) {
				dos.writeUTF(profesor.getDni());
				dos.writeUTF(profesor.getNombre());
				dos.writeUTF(profesor.getDireccion());
				dos.writeUTF(profesor.getTelefono());

				// Escribir la información de los cursos si es necesario
				if (profesor.getCursos() != null) {
					dos.writeInt(profesor.getCursos().size());
					for (Curso curso : profesor.getCursos()) {
						dos.writeUTF(curso.getCodigo());
					}
				} else {
					dos.writeInt(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Recibe un ArrayList y sobrescribe al del fichero
	 * 
	 * @param profesores
	 */
	public static void escribirProfesor(ArrayList<Profesor> profesores) {
		Utiles.ficheroExiste(FICHERO);
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHERO, false))) {
			for (Profesor profesor : profesores) {
				dos.writeUTF(profesor.getDni());
				dos.writeUTF(profesor.getNombre());
				dos.writeUTF(profesor.getDireccion());
				dos.writeUTF(profesor.getTelefono());
				if (!profesor.getCursos().isEmpty()) {
					dos.writeInt(profesor.getCursos().size());
					for (Curso curso : profesor.getCursos()) {
						dos.writeUTF(curso.getCodigo());
					}
				} else {
					dos.writeInt(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestiona y pide la informacion necesaria por teclado para la creaccion de un
	 * Nuevo Profesor
	 */
	public static void alta() {
		Utiles.ficheroExiste(FICHERO);
		int contFallos = 0;
		boolean fin = false;
		String dni = "";
		String nombre = "";
		String direccion = "";
		String telefono = "";
		do {
			System.out.print(Utiles.TAB + "DNI: ");
			dni = Utiles.entradaTeclado();
			if (!Utiles.comprobarDNI(dni)) {
				contFallos = Utiles.fallo(contFallos, "Alta");
			} else {
				if (duplicadosDni(dni)) {
					System.out.print(Utiles.TAB + "El dni " + dni + " ya se encuntra registrado");
					fin = true;
					contFallos = 6;
				} else {
					System.out.println(Utiles.TAB + Utiles.OK + "DNI introducido con exito " + Utiles.RIGHT);
					fin = true;
				}
			}
			if (contFallos == 5) {
				fin = true;
			}
		} while (!fin);

		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Nombre: ");
				nombre = Utiles.formateoNombres(Utiles.entradaTeclado().toUpperCase());
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
		}
		fin = false;
		if (contFallos < 5) {
			contFallos = 0;
			do {
				System.out.print(Utiles.TAB + "Direccion: ");
				direccion = Utiles.entradaTeclado();
				if (nombre.isEmpty()) {
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
		System.out.println(Utiles.TAB);
		if (contFallos < 5) {
			String move = "\n" + Utiles.TAB + (char) 16;
			if (Utiles.yesNo(Utiles.TAB + "Desea dar de alta un profesor con estos datos?\n" + Utiles.TAB + "--------"
					+ move + "DNI: " + dni + move + "Nombre: " + nombre + move + "Direccion: " + direccion + move
					+ "Telefono: " + telefono + "\n" + Utiles.TAB + "-------------->[S/N]: ", "Alta")) {
				// System.out.println("Alumno creaado-------------");
				Profesor p1 = new Profesor(dni, nombre, direccion, telefono);
				escribirProfesor(p1);
			}
		}

	}

	/**
	 * Busca en el fichero a un profesor por nombre o DNI
	 * 
	 * @param busqueda
	 * @param tipoBusqueda
	 * @return = profesor devuelve el profesor buscado
	 * @return = null no ha encontrado el profesor buscado
	 */
	public static Profesor busqueda(String busqueda, String tipoBusqueda) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> curs = GCurso.leerCursos();
		ArrayList<Profesor> coincidencias = new ArrayList<>();
		try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO))) {
			while (true) {
				String dni = dis.readUTF();
				String nombre = dis.readUTF();
				String direccion = dis.readUTF();
				String telefono = dis.readUTF();
				Profesor profesor = new Profesor(dni, nombre, direccion, telefono);
				int numCursos = dis.readInt();
				for (int i = 0; i < numCursos; i++) {
					String codigoCurso = dis.readUTF();
					for (int j = 0; j < curs.size(); j++) {
						if (codigoCurso.equals(curs.get(j).getCodigo())) {
							profesor.agregarCurso(curs.get(j));
						}
					}
				}
				if (tipoBusqueda.equalsIgnoreCase("dni") && profesor.getDni().equalsIgnoreCase(busqueda)) {
					coincidencias.add(profesor);
				} else if (tipoBusqueda.equalsIgnoreCase("name") && profesor.getNombre().equalsIgnoreCase(busqueda)) {
					coincidencias.add(profesor);
				}
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			e.printStackTrace();
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
		return null;
	}

	/**
	 * Metodo de apoyo de busqueda(), gestiona y pide la informacion necesaria por
	 * teclado para la busqueda de un Profesor
	 * 
	 * @return = profesor devuelve el profesor buscado
	 * @return = null no ha encontrado el profesor buscado
	 */
	public static Profesor parametrosBusqueda() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		if (profesores == null || profesores.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
			return null;
		} else {
			boolean fin = false;
			int fallos = 0;
			do {
				System.out.print(Utiles.TAB + "¿Desea buscar por Nombre o por DNI?\n" + Utiles.TAB
						+ "Pulse--0 para buscar por nombre\n" + Utiles.TAB + "Pulse--1 para buscar por DNI\n"
						+ Utiles.TAB + "Opcion: ");
				int respuesta = Utiles.returnNumber();
				if (respuesta == 0) {
					System.out.print(Utiles.TAB + "Introduzca el nombre del profesor que busca: ");
					String nombre = Utiles.formateoNombres(Utiles.entradaTeclado()).toLowerCase();
					fin = true;
					return busqueda(nombre, "name");
				} else if (respuesta == 1) {
					System.out.print(Utiles.TAB + "Introduzca el DNI del profesor que busca: ");
					String apellidos = Utiles.formateoNombres(Utiles.entradaTeclado());
					fin = true;
					return busqueda(apellidos, "dni");
				} else {
					System.out.println(Utiles.TAB + "No ha introducido una opcion valida"); // esto es un fallo
					fallos = Utiles.fallo(fallos, "Busqueda");
				}
				if (fallos == 5) {
					fin = true;
				}
			} while (!fin);
		}
		return null;
	}

	/**
	 * Escribe en el fichero cualquier modificacion sobre los objetos de este u
	 * objetos relacionado (Curso)
	 * 
	 * @param objetivo
	 * @param modificacion=0 elimiacion de un objeto
	 * @param modificacion=1 modificacion nombre
	 * @param modificacion=2 modificacion dni
	 * @param modificacion=3 modificacion direccion
	 * @param modificacion=4 modificacion telefono
	 * @param modificacion=5 modificacion agregar un curso
	 * @param modificacion=6 modificacion eliminar un curso
	 * @param argumento
	 */
	public static void modificacionFichero(Profesor objetivo, String modificacion, int argumento) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		ArrayList<Curso> curs = GCurso.leerCursos();
		Curso addiccion = null;
		for (int i = 0; i < profesores.size(); i++) {
			if (profesores.get(i).equals(objetivo)) {
				switch (argumento) {
				case 0: // eliminacion
					if (!profesores.get(i).getCursos().isEmpty()) {
						for (Curso c : curs) {
							if (c.getProfesor().equalsIgnoreCase(profesores.get(i).getDni())) {
								c.jubilar();
							}
						}
					}
					profesores.remove(profesores.get(i));
					GCurso.escribirCursos(curs);
					break;
				case 1: // nombre
					profesores.get(i).setNombre(modificacion);
					break;
				case 2: // dni
					profesores.get(i).setDni(modificacion);
					break;
				case 3: // direccion
					profesores.get(i).setDireccion(modificacion);
					break;
				case 4: // telefono
					profesores.get(i).setTelefono(modificacion);
					break;
				case 5: // addcurso
					
					
					boolean existe = false;
					for (Curso c : curs) {
						if (c.getCodigo().equals(modificacion)) {
							existe = true;
							addiccion = c;
						}
					}
					if(!addiccion.getProfesor().equals("null")) {
						for(Profesor p: profesores) {
							if(addiccion.getProfesor().equalsIgnoreCase(p.getDni())) {
								p.getCursos().remove(addiccion);
							}
						}
					}	
					if (existe) {
						profesores.get(i).agregarCurso(addiccion);
						GCurso.addProfesor(addiccion, profesores.get(i));
					}
					break;
					
					
					
				case 6: // remove curso
					boolean esReal = false;
					for (Curso c : curs) {
						if (c.getCodigo().equals(modificacion)) {
							esReal = true;
							addiccion = c; // Usar el curso encontrado
						}
					}
					if (esReal) {
						profesores.get(i).eliminarCurso(addiccion);
						GCurso.removeProfesor(addiccion, profesores.get(i));
					}
					break;
				default:
					break;
				}
			}
		}
		escribirProfesor(profesores);
	}

	/**
	 * Metodo de apoyo de moficacionFichero(), gestiona y pide la informacion
	 * necesaria por teclado para la modificacion de un Profesor
	 */
	public static void modificacion() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		if (profesores == null || profesores.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
		} else {
			Profesor profesor = parametrosBusqueda();
			if (profesor != null) {
				System.out.println("\n\t\t[--- MENU MODIFICAR PROFESORES ----\n\t\t" + "[ 1. Nombre\n\t\t"
						+ "[ 2. DNI\n\t\t" + "[ 3. Direccion\n\t\t" + "[ 4. Telefono\n\t\t[ 0. Salir");

				System.out.print("\t\t[--- > Elige que modificar: ");
				int opcion = Utiles.returnNumber();
				switch (opcion) {
				case 1:
					System.out.println("\n\n\t\t[----//// MODIFICAR NOMBRE ////----]");
					System.out.println("\n\n\t\t[El nombre del profesor es: " + profesor.getNombre()
							+ ", introduzca el nuevo nombre");
					String name = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
						modificacionFichero(profesor, name, 1);
					}
					System.out.println(/**/"\t\t[----------------------------------]");
					break;
				case 2:
					System.out.println("\n\n\t\t[----//// MODIFICAR DNI ////----]");
					System.out.println(
							"\n\n\t\t[El dni del profesor es: " + profesor.getDni() + ", introduzca el nuevo Apellido");
					String dni = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (!duplicadosDni(dni)) {
						if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
							modificacionFichero(profesor, dni, 2);
						}
					} else {
						System.out.println("\n\t\t[----EL DNI YA ESTA REGISTRADO----]");
					}
					System.out.println(/**/"\t\t[--------------------------------------]");
					break;
				case 3:
					System.out.println("\n\n\t\t[----//// MODIFICAR DIRECCION ////----]");
					System.out.println("\"\n\n\t\t[La direccion del profesor es: " + profesor.getDireccion()
							+ ", introduzca el nuevo Apellido");
					String dir = Utiles.formateoNombres(Utiles.entradaTeclado());
					if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
						modificacionFichero(profesor, dir, 3);
					}
					System.out.println(/**/"\t\t[--------------------------------------]");
					break;
				case 4:
					System.out.println("\n\n\t\t[----//// MODIFICAR TELEFONO ////----]");
					System.out.println("\n\n\t\t[El Telefono del Profesor es: " + profesor.getTelefono()
							+ ", introduzca el nuevo Telefono");
					String telef = Utiles.entradaTeclado();
					if (Utiles.comprobarTelefono(telef)) {
						System.out.println(Utiles.TAB + Utiles.OK + "Telefono introducido con exito " + Utiles.RIGHT);
					}
					if (Utiles.yesNo("Desea realizar la modificacion?[S/N]", "modificacion")) {
						modificacionFichero(profesor, telef, 4);
					}
					System.out.println(/**/"\t\t[------------------------------------]");
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
	 * necesaria por teclado para la eliminacion de un Profesor
	 */
	public static void eliminacion() {
		Utiles.ficheroExiste(FICHERO);
		int fallos = 0;
		ArrayList<Profesor> profesores = listarProfesor();
		if (profesores == null || profesores.size() == 0) {
			System.out.println(Utiles.TAB + "**VACIO**");
		} else {
			boolean fin = false;
			Profesor profesor = parametrosBusqueda();
			if (profesor != null) {
				do {
					System.out.print(
							"\n\t\t[--- MENU BAJA PROFESORES ----\n\t\t" + "[ 1. Dar de baja Profesor\n\t\t[ 0. Salir");
					int opcion = Utiles.returnNumber();
					switch (opcion) {
					case 1:
						modificacionFichero(profesor, "", 0);
						System.out.println(/**/"\t\t[---Profesor eliminado con exito---]");
						System.out.println(/**/"\t\t[----------------------------------]");
						fin = true;
						break;
					case 0:
						System.out.println(Utiles.TAB + "Saliendo...");
						fin = true;
						break;
					default:
						fallos++;
						if (fallos == 5) {
							System.out.println(Utiles.TAB + "Saliendo...");
							fin = true;
						}
						System.out.println(Utiles.TAB + "Entrada no valida.");
						break;
					}

				} while (fin == false);
			}
		}
	}

	/**
	 * Muestra por pantalla todos los profesores
	 */
	public static void mostrarProfesores() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		if (!profesores.isEmpty()) {
			for (Profesor c : profesores) {
				System.out.println(Utiles.TAB + c.toString());
			}
		} else {
			System.out.println(Utiles.TAB + "**VACIO**");
		}

	}

	/**
	 * Metodo de apoyo de modificacionFichero(), gestiona y pide la informacion
	 * necesaria por teclado para la agregacion de un curso en un Profesor
	 */
	public static void asignarProfesor() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		if (profesores.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen profesores aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !profesores.isEmpty()) {
			Profesor profesor = parametrosBusqueda();
			if (profesor != null) {
				Curso curso = GCurso.busqueda();
				if (curso != null) {
					if(profesor.getCursos().contains(curso)) {
						System.out.println(Utiles.TAB + "**El profesor ya tiene asignado el curso**");
					}else {
						modificacionFichero(profesor, curso.getCodigo(), 5);
						System.out.println(Utiles.TAB + "**Curso asignado**");
					}
				}
			}
		}
	}

	/**
	 * Metodo de apoyo de modificacionFichero(), gestiona y pide la informacion
	 * necesaria por teclado para la eliminacion de un curso en un Profesor
	 */
	public static void desasignarProfesor() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		if (profesores.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen profesores aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !profesores.isEmpty()) {
			Profesor profesor = parametrosBusqueda();
			if (profesor != null) {
				if (!profesor.getCursos().isEmpty()) {
					Curso curso = GCurso.busqueda();
					if (curso != null) {
						if (Utiles.yesNo("Desea realizar la desasignacion?[S/N]", "desasignacion")) {
							modificacionFichero(profesor, curso.getCodigo(), 6);
							System.out.println(Utiles.TAB + "**Curso desasignado**");
						}
					}
				} else {
					System.out.println(Utiles.TAB + "**El profesor no esta registrado en ningun curso aun**");
				}
			}
		}
	}

	/**
	 * muestra por pantalla todos los cursos que tienen un profesor asignado
	 */
	public static void mostrarCursoProfesor() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		int registrados = 0;
		if (profesores.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen profesores aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !profesores.isEmpty()) {
			for (Profesor profe : profesores) {
				if (!profe.getCursos().isEmpty()) {
					System.out.println(profe.toString());
					registrados++;
				}
			}
			if (registrados == 0) {
				System.out.println(Utiles.TAB + "**Nigun profesor imparte curso aun**");
			}
		}
	}

	/**
	 * muestra los profesores que no imparten ningun curso
	 */
	public static void mostrarCursosinProfesor() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Profesor> profesores = listarProfesor();
		ArrayList<Curso> cursos = GCurso.leerCursos();
		int registrados = 0;
		if (profesores.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen profesores aun**");
		}
		if (cursos.isEmpty()) {
			System.out.println(Utiles.TAB + "**No Existen cursos aun**");
		}
		if (!cursos.isEmpty() && !profesores.isEmpty()) {
			for (Profesor profe : profesores) {
				if (profe.getCursos().isEmpty()) {
					System.out.println(profe.toString());
					registrados++;
				}
			}
			if (registrados == 0) {
				System.out.println(Utiles.TAB + "**Todos los profesores imparten cursos**");
			}
		}
	}
}
