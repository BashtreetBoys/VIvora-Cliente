package gui;

import gameObject.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PuntosJPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -991822345509167857L;
	
	private ArrayList<JugadorLabel> listaJugadores;
    private Font fuenteLabel;
    private Dimension tamanoMaxLabel;

    public PuntosJPanel() {
        super();
        crearLayout();
        crearComponentes();
    }

    private void crearLayout() {
        setBackground(new Color(66, 66, 66));
        setBorder(new EmptyBorder(5, 5, 5,5));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 600));
        setMaximumSize(new Dimension(200, 660));
    }

    private void crearComponentes() {
        listaJugadores = new ArrayList<>();
        fuenteLabel = new Font("SansSerif", Font.BOLD, 20);
        tamanoMaxLabel =  new Dimension(50, 50);
    }

    public void agregarJugador(String nombreJugador, Color color) {
        JugadorLabel nuevoJugadorLabel = new JugadorLabel(nombreJugador, 0);
        nuevoJugadorLabel.setForeground(color);
        nuevoJugadorLabel.setFont(fuenteLabel);
        nuevoJugadorLabel.setPreferredSize(tamanoMaxLabel);
        nuevoJugadorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        listaJugadores.add(nuevoJugadorLabel);

        add(nuevoJugadorLabel);
    }

    public void actualizarPuntosJugador(String nombreJugador, int deltaPuntos) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            if(listaJugadores.get(i).getNombreJugador() == nombreJugador) {
                listaJugadores.get(i).actualizarPuntos(deltaPuntos);
                break;
            }
        }
    }
}
