package principal;

import java.io.Serializable;
import java.util.Objects;

public class Curso implements Serializable {
	// ID de la version del objeto serializable
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String profesor; // dni del profesor

	/**
	 * Constructor sin profesor
	 * 
	 * @param nombre
	 * @param descripcion
	 */
	public Curso(int codigo, String nombre, String descripcion) {
		this.codigo = "" + codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.profesor = "null";
	}

	/**
	 * Constructor con profesor
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param profesor
	 */
	public Curso(String codigo, String nombre, String descripcion, String profesor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.profesor = profesor;
	}

	/**
	 * hash de la clase Curso centrado en el codigo de curso
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	/**
	 * equals de la Curso centrado en el codigo de curso
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigo, other.codigo);
	}

	/**
	 * Getter de la clase Curso
	 * 
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Getter de la clase Curso
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de la clase Curso
	 * 
	 * @return
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la clase Curso
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter de la clase Curso
	 * 
	 * @return
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Getter de la clase Curso
	 * 
	 * @return
	 */
	public String getProfesor() {
		return profesor;
	}

	/**
	 * Setter de la clase Curso
	 * 
	 * @return
	 */
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	/**
	 * elimina un profesor de un curso
	 */
	public void jubilar() {
		this.profesor = "null";
	}

	/**
	 * ayuda escribir objetos de la clase Curso en fichero
	 * 
	 * @return
	 */
	public String toCVS() {
		return codigo + "#" + nombre + "#" + descripcion + "#" + profesor + "\n";
	}

	/**
	 * toString de la clase Curso
	 */
	@Override
	public String toString() {
		if (this.profesor.equals("null")) {
			return "Cod[" + codigo + "] " + nombre + " Desc[" + descripcion + "] Profesor: Sin profesor asignado";
		} else {
			return "Cod[" + codigo + "] " + nombre + " Desc[" + descripcion + "] Profesor: " + profesor;
		}

	}
}