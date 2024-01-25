package principal;

public class Menu {
	/**
	 * Genera si no existen los ficheros de cada clase
	 */
	public static void genFile(){
		Utiles.ficheroExiste(GAlumno.FICHERO);
		Utiles.ficheroExiste(GCurso.FICHERO);
		Utiles.ficheroExiste(GProfesor.FICHERO);
	}
	/**
	 * Menu alumno, gestiona todas las opciones del CRUD alumno
	 */
	public static void menuAlumno() {
		boolean fin = false;
		do {

			System.out
					.println("\n\t[--- MENU ALUMNOS ----\n\t[ 1. Alta\n\t[ 2. Baja\n\t[ 3. Buscar\n\t[ 4. Modificar\n\t"
							+ "[ 5. Mostrar Todos\n\t[ 6. Matricular Alumno\n\t[ 7. Desmatricular Alumno\n\t[ 8. Mostrar Alumnos por curso\n\t[ 0. Volver a Menu principal");
			System.out.print("\t[--- > Introduce la opcion: ");
			String opcion = Utiles.entradaTeclado();
			switch (opcion) {
			case "1":
				altaAlumno();
				break;
			case "2":
				bajaAlumno();
				break;
			case "3":
				buscarAlumno();
				break;
			case "4":
				modificacionAlumno();
				break;
			case "5":
				mostrarAlumno();
				break;
			case "6":
				matricularAlumno();
				break;
			case "7":
				desmatricularAlumno();
				break;
			case "8":
				mostrarAlumnoCurso();
				break;
			case "0":
				System.out.println("\tSaliendo...");
				fin = true;
				break;
			default:
				System.out.println("\n\tEntrada no valida.");
				break;
			}
		} while (fin == false);
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void altaAlumno() {
		System.out.println("\n\n\t\t[----///////// ALTA ALUMNO /////////----]");
		GAlumno.alta();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void bajaAlumno() {
		System.out.println("\n\n\t\t[----///////// BAJA ALUMNO /////////----]");
		GAlumno.eliminacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void buscarAlumno() {
		System.out.println("\n\n\t\t[----//////// BUSCAR ALUMNO ////////----]");
		Alumno alum = GAlumno.busqueda();
		if (alum != null) {
			alum.toString();
		}
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void modificacionAlumno() {
		System.out.println("\n\n\t\t[----///// MODIFICACION ALUMNO /////----]");
		GAlumno.modificacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void mostrarAlumno() {
		System.out.println("\n\n\t\t[----//////// MOSTRAR ALUMNOS //////----]");
		GAlumno.mostrarAlumnos();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void matricularAlumno() {
		System.out.println("\n\n\t\t[----////// MATRICULAR ALUMNO //////----]");
		GAlumno.matricular();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void desmatricularAlumno() {
		System.out.println("\n\n\t\t[----///// DESMATRICULAR ALUMNO ////----]");
		GAlumno.desMatricular();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu alumno
	 */
	public static void mostrarAlumnoCurso() {
		System.out.println("\n\n\t\t[----// MOSTRAR ALUMNOS POR CURSO //----]");
		GAlumno.mostrarAlumnoCurso();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Menu profeso, gestiona todas las opciones del CRUD profesor
	 */
	public static void menuProfesor() {
		boolean fin = false;
		do {

			System.out.println(
					"\n\t[--- MENU PROFESORES ----\n\t[ 1. Alta\n\t[ 2. Baja\n\t[ 3. Buscar\n\t[ 4. Modificar\n\t"
							+ "[ 5. Mostrar Todos\n\t[ 6. Asignar Profesor\n\t[ 7. Desasignar Profesor\n\t[ 8. Mostrar Profesores con cursos\n\t[ 0. Volver a Menu principal");
			System.out.print("\t[--- > Introduce la opcion: ");
			String opcion = Utiles.entradaTeclado();
			switch (opcion) {
			case "1":
				altaProfesor();
				break;
			case "2":
				bajaProfesor();
				break;
			case "3":
				buscarProfesor();
				break;
			case "4":
				modificacionProfesor();
				break;
			case "5":
				mostrarProfesor();
				break;
			case "6":
				asignarProfesor();
				break;
			case "7":
				desasignarProfesor();
				break;
			case "8":
				mostrarProfesorCurso();
				break;
			case "9":
				mostrarProfesorsinCurso();
				break;
			case "0":
				System.out.println("\tSaliendo...");
				fin = true;
				break;
			default:
				System.out.println("\n\tEntrada no valida.");
				break;
			}
		} while (fin == false);
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void altaProfesor() {
		System.out.println("\n\n\t\t[----/////// ALTA PROFESOR /////////----]");
		GProfesor.alta();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void bajaProfesor() {
		System.out.println("\n\n\t\t[----/////// BAJA PROFESOR /////////----]");
		GProfesor.eliminacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void buscarProfesor() {
		System.out.println("\n\n\t\t[----////// BUSCAR PROFESOR ////////----]");
		Profesor profe = GProfesor.parametrosBusqueda();
		if (profe != null) {
			profe.toString();
		}
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void modificacionProfesor() {
		System.out.println("\n\n\t\t[----//// MODIFICACION PROFESOR ////----]");
		GProfesor.modificacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void mostrarProfesor() {
		System.out.println("\n\n\t\t[----/////// MOSTRAR PROFESORES ////----]");
		GProfesor.mostrarProfesores();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void asignarProfesor() {
		System.out.println("\n\n\t\t[----/////// ASIGNAR PROFESOR //////----]");
		GProfesor.asignarProfesor();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void desasignarProfesor() {
		System.out.println("\n\n\t\t[----///// DESASIGNAR PROFESOR /////----]");
		GProfesor.desasignarProfesor();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void mostrarProfesorCurso() {
		System.out.println("\n\n\t\t[---- MOSTRAR PROFESORES CON CURSO -----]");
		GProfesor.mostrarCursoProfesor();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu profesor
	 */
	public static void mostrarProfesorsinCurso() {
		System.out.println("\n\n\t\t[---- MOSTRAR PROFESORES SIN CURSO -----]");
		GProfesor.mostrarCursosinProfesor();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Menu curso, gestiona todas las opciones del CRUD Curso
	 */
	public static void menuCurso() {
		boolean fin = false;
		do {

			System.out.println(
					"\n\t[--- MENU CURSOS ----\n\t[ 1. Nuevo curso\n\t[ 2. Baja\n\t[ 3. Buscar\n\t[ 4. Modificar\n\t"
							+ "[ 5. Mostrar Todos\n\t[ 0. Volver a Menu principal");
			System.out.print("\t[--- > Introduce la opcion: ");
			String opcion = Utiles.entradaTeclado();
			switch (opcion) {
			case "1":
				altaCurso();
				break;
			case "2":
				bajaCurso();
				break;
			case "3":
				buscarCurso();
				break;
			case "4":
				modificacionCurso();
				break;
			case "5":
				mostrarCurso();
				break;
			case "0":
				System.out.println("\tSaliendo...");
				fin = true;
				break;
			default:
				System.out.println("\n\tEntrada no valida.");
				break;
			}
		} while (fin == false);
	}

	/**
	 * Metodo de apoyo del metodo menu curso
	 */
	public static void altaCurso() {
		System.out.println("\n\n\t\t[----//////// ALTA CURSO ///////////----]");
		GCurso.alta();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu curso
	 */
	public static void bajaCurso() {
		System.out.println("\n\n\t\t[----///////// BAJA CURSO //////////----]");
		GCurso.eliminacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu curso
	 */
	public static void buscarCurso() {
		System.out.println("\n\n\t\t[----/////// BUSCAR CURSO //////////----]");
		Curso curso = GCurso.busqueda();
		if (curso != null) {
			curso.toString();
		}
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu curso
	 */
	public static void modificacionCurso() {
		System.out.println("\n\n\t\t[----///// MODIFICACION CURSO //////----]");
		GCurso.modificacion();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo de apoyo del metodo menu curso
	 */
	public static void mostrarCurso() {
		System.out.println("\n\n\t\t[----///////// MOSTRAR CURSOS //////----]");
		GCurso.mostrarCursos();
		System.out.println(/**/"\t\t[---------------------------------------]");
	}

	/**
	 * Metodo menu, gestiona y distribuye el menu principal conjunto
	 */
	public static void menu() {
		genFile();
		boolean fin = false;
		do {
			System.out.println("\n[--- MENU PRINCIPAL----\n[ 1. Gestionar alumnos\n"
					+ "[ 2. Gestionar profesores\n[ 3. Gestionar cursos\n[ 0. Salir");
			System.out.print("[--- > Introduce la opcion: ");
			String opcion = Utiles.entradaTeclado();
			switch (opcion) {
			case "1":
				menuAlumno();
				break;
			case "2":
				menuProfesor();
				break;
			case "3":
				menuCurso();
				break;
			case "0":
				System.out.println("[---Saliendo...");
				fin = true;
				break;
			default:
				System.out.println("\n[---Entrada no valida.");
				break;
			}
		} while (fin == false);
		Utiles.cerrarTeclado();
		System.out.println("\n-****- PROGRAMA FINALIZADO -****-\n");
	}
}
