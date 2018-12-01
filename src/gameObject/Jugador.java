package gameObject;

/**
 * 
 * @author MDS7.
 *
 */
public class Jugador {
	private String nombre;
	private String estado; //Estado que indica "Jugando" "Listo" "No Jugando".
	private Vibora vivorita; 
	private int puntaje;
	
	/**
	 * 
	 * @param nombre Nombre del jugador.
	 * @param estado Estado del jugador, "Jugando"/"Listo"/"No Jugando".
	 * @param vivorita La vivora asignada al jugador.
	 */
	public Jugador(String nombre, String estado, Vibora vivorita) {
		this.nombre = nombre;
		this.estado = estado;
		this.vivorita = vivorita;
		this.puntaje = 0;
	}
	
	/**
	 * Sirve para salir de la Arena.
	 * @param a Indica la Arena
	 */
	public void salirDeArena(Arena a) {
		System.out.println("Sali de la arena jaj.");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void actualizarPuntaje(int puntajeNuevo) { puntaje = puntajeNuevo; }

	public Vibora getVivorita() { return vivorita; }
}
