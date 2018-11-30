package mensajeria;

import gameObject.Jugador;
import gameObject.Vibora;

/**
 * 
 * @author Julian
 * Este todavia no esta implementado pero una vez que nosotros nos conectamos al socket de la sala lo primero que vamos a mandar es nuestra vibora y el nombre del jugador
 * Por que? Nosotros del lado del cliente nos hacemos cargo de mover a nuestra vibora (cambiarle la direccion y ponerle una posicion x e y).
 * Le vamos a mandar esa nueva posicion X e Y actualizada al server para ver si colisionamos con algo.
 * Cada vez que el server recibe una X e Y va a llamar a verColisiones y va a actualizar la arena.
 * Lo que vamos a recibir del server es la arena actualizada para pintarla en nuestro JPanel.
 * Posiblemente nos convenga mandar a un jugador directamente, eso lo veremos despues, no tengo tanto tiempo de pensar ahora.
 * @author JulianP - me parece que es mejor mandar al jugador solo
 * 
 */
public class MjeViboraNueva {

	public Vibora viboraJugador;
	public String nombreJugador;
	public Jugador player;
	
	public MjeViboraNueva(Vibora viboraJugador, String nombreJugador) {
		super();
		this.viboraJugador = viboraJugador;
		this.nombreJugador = nombreJugador;
	}

	
	
}
