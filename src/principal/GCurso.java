package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Se trata de la clase que gestiona todas las operaciones del CRUD de la clase
 * Curso
 */
public class GCurso {

	// Fichero donde se guardaran todos los datos de la clase Curso
	public static final String FICHERO = ".\\data\\cursos.txt";

	/**
	 * Devuelve un ArrayList con todos los cursos del fichero
	 * 
	 * @return
	 */
	public static ArrayList<Curso> leerCursos() {
		ArrayList<Curso> cursos = new ArrayList<>();
		if (Utiles.ficheroExiste(FICHERO)) {
			File file = new File(FICHERO);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String linea;
				while ((linea = reader.readLine()) != null) {
					String[] campos = linea.split("#");
					if (campos.length >= 4) {
						Curso c1 = new Curso(campos[0], campos[1], campos[2], campos[3]);
						cursos.add(c1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return cursos;
		} else {
			return null;
		}
	}

	/**
	 * Muestra por pantalla todos los cursos existentes del fichero
	 */
	public static void mostrarCursos() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> cursos = leerCursos();
		if (cursos == null || cursos.size() == 0) {
			System.out.println(Utiles.TAB + "No existen cursos aun");
		} else {
			File file = new File(FICHERO);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String linea;
				while ((linea = reader.readLine()) != null) {
					String[] campos = linea.split("#");
					if (campos.length >= 4) {
						System.out.println(Utiles.TAB + new Curso(campos[0], campos[1], campos[2], campos[3]));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * devuelve el id mas alto de los cursos en el fichero y le suma 1, si no hay
	 * ningun curso registrado devuelve 10
	 * 
	 * @return
	 */
	public static int idLastCurso() {
		if (Utiles.ficheroExiste(FICHERO)) {
			File file = new File(FICHERO);
			BufferedReader reader = null;
			ArrayList<Integer> nums = new ArrayList<>();
			try {
				reader = new BufferedReader(new FileReader(file));
				String linea;

				while ((linea = reader.readLine()) != null) {
					String[] campos = linea.split("#");
					if (campos.length > 0 && !campos[0].trim().isEmpty()) {
						nums.add(Integer.parseInt(campos[0].trim()));
					}
				}
				if (!nums.isEmpty()) {
					Collections.sort(nums, Collections.reverseOrder()); // Ordenar en orden descendente
					return (nums.get(0) + 1);
				} else {
					return 10;
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} else {
			return 10;
		}
		return 10;
	}

	/**
	 * Escribe en el fichero un Nuevo curso
	 * 
	 * @param curso
	 */
	public static void escribirCurso(Curso curso) {
		Utiles.ficheroExiste(FICHERO);
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try {
			writer = new FileWriter(FICHERO, true);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(curso.toCVS());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Escribe en el fichero un ArrayList de cursos
	 * 
	 * @param cursos
	 */
	public static void escribirCursos(ArrayList<Curso> cursos) {
		Utiles.ficheroExiste(FICHERO);
		try (FileWriter writer = new FileWriter(FICHERO, false);
				BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

			for (Curso curso : cursos) {
				bufferedWriter.write(curso.toCVS());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca en el fichero un Curso
	 * 
	 * @return
	 */
	public static Curso busqueda() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> alumnos = leerCursos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "No existen cursos aun");
			return null;
		} else {
			System.out.print(
					Utiles.TAB + "\n" + Utiles.TAB + "\t[--- BUSQUEDA CURSO ---]\n" + Utiles.TAB + "\t[Nombre: ");
			String busqueda = Utiles.entradaTeclado().toLowerCase();
			ArrayList<Curso> coincidencias = new ArrayList<>();
			File file = new File(FICHERO);

			if (!file.exists()) {
				System.out.println(Utiles.TAB + "No hay Cursos disponibles");
				return null;
			}
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String linea;
				while ((linea = reader.readLine()) != null) {
					String[] campos = linea.split("#");
					if (campos.length >= 4) {
						if (campos[1].equalsIgnoreCase(busqueda)) {
							Curso c1 = new Curso(campos[0], campos[1], campos[2], campos[3]);
							coincidencias.add(c1);
						}
					}
				}
				if (coincidencias.size() == 1) {
					return coincidencias.get(0);
				} else if (coincidencias.size() == 0) {
					System.out.println(Utiles.TAB + "No existen coincidencias");
					return null;
				} else {
					System.out.println(
							Utiles.TAB + Utiles.TAB + "Existen varias coincidencias, indique a cual se refiere");
					int i;
					for (i = 0; i < coincidencias.size(); i++) {
						System.out.println(
								Utiles.TAB + Utiles.TAB + " Pulse--" + i + ": " + coincidencias.get(i).toString());
					}
					System.out.print(Utiles.TAB + Utiles.TAB + "Opcion: ");
					int respuesta = Utiles.returnNumber();
					if (respuesta == -99) {
						System.out.println(Utiles.TAB + Utiles.TAB + "No ha introducido un numero.");
					} else {
						if (respuesta >= 0 && respuesta < coincidencias.size()) {
							return coincidencias.get(respuesta);
						} else {
							System.out.println(Utiles.TAB + Utiles.TAB + "Índice fuera de rango.");
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * Escribe en el fichero cualquier modificacion en cualquier objeto Curso, asi
	 * de sus clases relacionadas
	 * 
	 * @param curso
	 */
	public static void escribirModificacion(Curso curso) {
		File file = new File(FICHERO);
		File aux = new File("data\\Auxcursos.txt");
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		BufferedReader reader = null;

		try {
			if (!file.exists()) {
				System.out.println(Utiles.TAB + "No existen cursos que modificar");
				file.createNewFile();
			} else {
				reader = new BufferedReader(new FileReader(file));
				writer = new FileWriter(aux);
				bufferedWriter = new BufferedWriter(writer);

				String linea;
				while ((linea = reader.readLine()) != null) {
					String[] campos = linea.split("#");
					if (campos.length >= 4) {
						if (campos[0].equalsIgnoreCase(curso.getCodigo())) {
							campos[1] = curso.getNombre();
							campos[2] = curso.getDescripcion();
							campos[3] = curso.getProfesor();
						}
						String escritura = String.join("#", campos);
						bufferedWriter.write(escritura + "\n");
					} else {
						bufferedWriter.write(linea + "\n");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if (file.exists()) {
			file.delete();
		}

		if (!aux.renameTo(new File(FICHERO))) {
			System.out.println("No se pudo renombrar el archivo auxiliar.");
		}
	}

	/**
	 * Metodo de apoyo de escribirModificacion(), gestiona y pide la informacion
	 * necesaria por teclado para la modifcacion de un Curso
	 */
	public static void modificacion() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> alumnos = leerCursos();
		if (alumnos == null || alumnos.size() == 0) {
			System.out.println(Utiles.TAB + "No existen cursos aun");
		} else {
			Curso curso = busqueda();
			if (curso != null) {
				System.out.println(Utiles.TAB + "Introduzca que campo desea modificar:\n" + Utiles.TAB
						+ "Pulse--1: Nombre\n" + Utiles.TAB + "Pulse--2: Descripcion");
				int respuesta = Utiles.returnNumber();
				switch (respuesta) {
				case -99:
					System.out.println(Utiles.TAB + "No ha introducido un numero.");
					break;
				case 1:
					String nombre = "";
					int fallos = 0;
					boolean fin = false;
					do {
						System.out.println(Utiles.TAB + "El nombre del curso es " + curso.getNombre()
								+ ", introduzca el nuevo nombre:");
						nombre = Utiles.formateoNombres(Utiles.entradaTeclado());
						if (!nombre.isEmpty()) {
							fin = true;
						} else {
							fallos = Utiles.fallo(fallos, "Modificacion");
						}
						if (fallos == 5) {
							fin = true;
						}

					} while (!fin);
					if (fallos != 5) {
						if (Utiles.yesNo(Utiles.TAB + "Desea realizar la modificacion?[S/N]", "modificacion")) {
							curso.setNombre(nombre);
							escribirModificacion(curso);
						}
					}
					break;
				case 2:
					System.out.println(Utiles.TAB + "La descripcion del curso " + curso.getNombre() + " es "
							+ curso.getDescripcion() + ", introduzca la nueva descripcion:");
					String descripcion = Utiles.entradaTeclado();
					if (Utiles.yesNo(Utiles.TAB + "Desea realizar la modificacion?[S/N]", "modificacion")) {
						curso.setDescripcion(descripcion);
						escribirModificacion(curso);
					}
				default:
					System.out.println(Utiles.TAB + "No ha introducido un numero valido.");
					break;
				}
			}
		}
	}

	/**
	 * Metodo que elimina un Curso del fichero
	 * 
	 * @param curso
	 */
	public static void escribirEliminar(Curso curso) {
		File file = new File(FICHERO);
		File aux = new File("data\\Auxcursos.txt");
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		BufferedReader reader = null;

		try {
			// Lector y escritor
			reader = new BufferedReader(new FileReader(file));
			writer = new FileWriter(aux);
			bufferedWriter = new BufferedWriter(writer);

			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] campos = linea.split("#");
				if (campos.length >= 4) {
					if (!campos[0].equalsIgnoreCase(curso.getCodigo())) {
						String escritura = campos[0] + "#" + campos[1] + "#" + campos[2] + "#" + campos[3];
						bufferedWriter.write(escritura + "\n");
					}
				} else {
					bufferedWriter.write(linea + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if (file.exists()) {
			file.delete();
		}

		if (!aux.renameTo(new File(FICHERO))) {
			System.out.println("No se pudo renombrar el archivo auxiliar.");
		}
	}

	/**
	 * Metodo de apoyo de escribirEliminar(), gestiona y pide la informacion
	 * necesaria por teclado para la eliminacion de un Curso
	 */
	public static void eliminacion() {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> cursos = leerCursos();
		ArrayList<Alumno> alumnos = GAlumno.leerAlumnos();
		if (cursos == null || cursos.size() == 0) {
			System.out.println(Utiles.TAB + "No existen cursos aun");
		} else {
			Curso curso = busqueda();
			if (curso != null) {
				if (Utiles.yesNo(Utiles.TAB + "Desea realizar la eliminacion?[S/N]", "eliminacion")) {
					for (Alumno a : alumnos) {
						if (a.getCursos().contains(curso)) {
							a.getCursos().remove(curso);
						}
					}
					GAlumno.escribirAlumno(alumnos);
					escribirEliminar(curso);
				}
			}
		}
	}

	/**
	 * Agrega un Profesor a un Curso
	 * 
	 * @param curso
	 * @param profesor
	 */
	public static void addProfesor(Curso curso, Profesor profesor) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> curs = leerCursos();
		for (int i = 0; i < curs.size(); i++) {
			if (curs.get(i).getCodigo().equals(curso.getCodigo())) {
				curs.get(i).setProfesor(profesor.getDni());
			}
		}
		escribirCursos(curs);
	}

	/**
	 * Elimina a un profesor de un curso
	 * 
	 * @param curso
	 * @param profesor
	 */
	public static void removeProfesor(Curso curso, Profesor profesor) {
		Utiles.ficheroExiste(FICHERO);
		ArrayList<Curso> curs = leerCursos();
		for (int i = 0; i < curs.size(); i++) {
			if (curs.get(i).getCodigo().equals(curso.getCodigo())) {
				curs.get(i).setProfesor("");
			}
		}
		escribirCursos(curs);
	}

	/**
	 * Gestiona y pide la informacion necesaria por teclado para la creaccion de un
	 * Nuevo Curso
	 */
	public static void alta() {
		Utiles.ficheroExiste(FICHERO);
		int contFallos = 0;
		boolean fin = false;
		int numCode = idLastCurso();
		String nombre = "";
		String descripcion = "";
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
				System.out.print(Utiles.TAB + "Descripcion de curso: ");
				descripcion = Utiles.formateoNombres(Utiles.entradaTeclado());
				if (descripcion.isEmpty()) {
					contFallos = Utiles.fallo(contFallos, "Alta");
				} else {
					System.out.println(Utiles.TAB + Utiles.OK + "Descripcion agregada con exito " + Utiles.RIGHT);
					fin = true;
				}
				if (contFallos == 5) {
					fin = true;
				}
			} while (!fin);
		}
		System.out.println(Utiles.TAB);
		if (contFallos < 5) {
			if (Utiles.yesNo(
					Utiles.TAB + "Desea dar de alta un curso con estos datos?\n" + Utiles.TAB + "--------" + Utiles.move
							+ "Numero de Curso: " + numCode + Utiles.move + "Nombre: " + nombre + Utiles.move
							+ "Descripcion de Curso: " + descripcion + "\n" + Utiles.TAB + "-------------->[S/N]: ",
					"Alta")) {
				Curso curso = new Curso(numCode, nombre, descripcion);
				escribirCurso(curso);
			}
		}
	}
}
