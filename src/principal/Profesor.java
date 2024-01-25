package principal;

import java.util.ArrayList;
import java.util.Objects;

public class Profesor {
	private String dni;
	private String nombre;
	private String direccion;
	private String telefono;
	private ArrayList<Curso> cursos;

	/**
	 * Constructor de un Nuevo Profesor
	 * 
	 * @param dni
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 */
	public Profesor(String dni, String nombre, String direccion, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cursos = new ArrayList<>();
	}

	/**
	 * Constructor de un Profesor a partir del fichero
	 * 
	 * @param dni
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @param cursos
	 */
	public Profesor(String dni, String nombre, String direccion, String telefono, ArrayList<Curso> cursos) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cursos = cursos;
	}

	/**
	 * metodo que gestiona la adiccion de un curso de un Profesor
	 * 
	 * @param curso
	 */
	public void agregarCurso(Curso curso) {
		if (!this.cursos.contains(curso)) {
			this.cursos.add(curso);
		}
	}

	/**
	 * metodo que gestiona la curso de un curso de un Profesor
	 * 
	 * @param curso
	 */
	public void eliminarCurso(Curso curso) {
		if (this.cursos.contains(curso)) {
			this.cursos.remove(curso);
			System.out.println("Curso eliminado con éxito del profesor " + this.nombre);
		}
	}

	/**
	 * hashCode de la clase Profesor, centrado en el dni ya que es unico
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	/**
	 * Equals de la clase Profesor, centrado en el dni ya que es unico
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}

	/**
	 * Getter de la clase Profesor
	 * 
	 * @return
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Setter de la clase Profesor
	 * 
	 * @return
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Getter de la clase Profesor
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de la clase Profesor
	 * 
	 * @return
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la clase Profesor
	 * 
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Setter de la clase Profesor
	 * 
	 * @return
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Getter de la clase Profesor
	 * 
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Setter de la clase Profesor
	 * 
	 * @return
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Getter de la clase Profesor
	 * 
	 * @return
	 */
	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	/**
	 * Setter de la clase Profesor
	 * 
	 * @return
	 */
	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	/**
	 * ToString de la clase Profesor, con ajustes para que se vea mejor
	 */
	@Override
	public String toString() {
		if (this.cursos.isEmpty()) {
			return "Profesor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
					+ ", cursos= SIN CURSOS REGISTRADOS]";
		} else {
			ArrayList<Curso> cursos = GCurso.leerCursos();
			if (cursos.isEmpty()) {
				return "Profesor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
						+ telefono + ", cursos= SIN CURSOS REGISTRADOS222]";
			} else {
				ArrayList<Curso> real = new ArrayList<>();
				String curs = "";
				for (int i = 0; i < this.cursos.size(); i++) {
					for (int j = 0; j < cursos.size(); j++) {
						if (this.cursos.get(i).equals(cursos.get(j))) {
							real.add(cursos.get(j));
							curs = curs + cursos.get(j).toString();
						}
					}
				}
				this.setCursos(real);
				return "Profesor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
						+ telefono + ", cursos=" + curs + "]";
			}
		}
	}
}