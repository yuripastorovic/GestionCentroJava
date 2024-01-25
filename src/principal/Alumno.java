package principal;

import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable {
	// ID de la version del objeto serializable
	private static final long serialVersionUID = 1L;
	private int numExp;
	private String nombre;
	private String ape1;
	private String ape2;
	private String fecha;
	private String telf;
	private String dir;
	private ArrayList<Curso> cursos;

	/**
	 * Constructor de la clase alumno para un Nuevo Alumno
	 * 
	 * @param numExp
	 * @param nombre
	 * @param ape1
	 * @param ape2
	 * @param fecha
	 * @param telf
	 * @param dir
	 */
	public Alumno(int numExp, String nombre, String ape1, String ape2, String fecha, String telf, String dir) {
		super();
		this.numExp = numExp;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fecha = fecha;
		this.telf = telf;
		this.dir = dir;
		this.cursos = new ArrayList<>();
	}

	/**
	 * Constructor de la clase alumno desde el fichero
	 * 
	 * @param numExp
	 * @param nombre
	 * @param ape1
	 * @param ape2
	 * @param fecha
	 * @param telf
	 * @param dir
	 * @param cursos
	 */
	public Alumno(int numExp, String nombre, String ape1, String ape2, String fecha, String telf, String dir,
			ArrayList<Curso> cursos) {
		super();
		this.numExp = numExp;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fecha = fecha;
		this.telf = telf;
		this.dir = dir;
		this.cursos = cursos;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getRealName() {
		return this.nombre + this.ape1 + this.ape2;
	}

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void removeCurso(Curso curso) {
		this.cursos.remove(curso);
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public int getNumExp() {
		return numExp;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setNumExp(int numExp) {
		this.numExp = numExp;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getApe1() {
		return ape1;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getApe2() {
		return ape2;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getTelf() {
		return telf;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setTelf(String telf) {
		this.telf = telf;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * Getter de la clase Alumno
	 * 
	 * @return
	 */
	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	/**
	 * Setter de la clase Alumno
	 * 
	 * @return
	 */
	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	/**
	 * toString de la clase Alumno
	 */
	@Override
	public String toString() {
		if (this.cursos.isEmpty()) {
			return "Alumno [numExp=" + numExp + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", fecha="
					+ fecha + ", telf=" + telf + ", dir=" + dir + ", cursos= SIN REGISTRARSE ]";
		} else {
			String imprimir = "";
			for (int i = 0; i < this.cursos.size(); i++) {
				imprimir = imprimir + this.cursos.get(i).getNombre() + ", ";
			}
			return "Alumno [numExp=" + numExp + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", fecha="
					+ fecha + ", telf=" + telf + ", dir=" + dir + ", cursos=" + imprimir + "]";
		}

	}

}