package gui;

import javax.swing.*;

public class JugadorLabel extends JLabel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 403222886438937852L;
	
	private String nombreJugador;
    private int puntosJugador;

    public JugadorLabel(String nombreJugador, int puntosJugador) {
        super(nombreJugador + ": " + puntosJugador);
        this.nombreJugador = nombreJugador;
        this.puntosJugador = puntosJugador;
    }

    public String getNombreJugador() { return nombreJugador; }

    public void actualizarPuntos(int deltaPuntos) {
        puntosJugador = deltaPuntos;
        setText(nombreJugador + ": " + puntosJugador);
    }
}
