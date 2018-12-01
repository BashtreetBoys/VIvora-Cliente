package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import gameObject.*;
//mucho de lo de aca tiene que ir a parar en el server cuando hagamos la arena. Para singleplayer esta bien.

public class ArenaVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3035969733902856405L;

	private static int colorAAsignar = 1;

	private ArenaJPanel panelArena;
	private JPanel panelContenedor;
	private PuntosJPanel panelPuntos;
	private GridBagLayout gbl;
	private ArrayList<Jugador> listaJugadores;	// Supongo que esta deberia ser una lista de sockets, ni idea la verdad. Esto lo podriamos trabajar como una lista de strings con los nombres nomas.
	private ArrayList<Color> listaColores;

	public ArenaVentana() {
		super("Arena");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200,30,1100,700);
		setResizable(false);
		
		crearComponentes();
		crearLayout();

		//En caso de no querer limitar el tamaño de la arena usar el siguiente
		//codigo comentado y comentar todos los add que siguen
		/*
		setContentPane(panelArena);
		 */
		setContentPane(panelContenedor);

		agregarJugador(new Jugador("Julian", "que se yah", new Vibora(0, 0)));
		agregarIA(new Jugador("IA 1", "Papurri", new Vibora(0, 0)));
		agregarIA(new Jugador("IA 2", "Mamurri", new Vibora(0, 0)));
		//agregarIA(new Jugador("IA 3", "Hermanurri", new Vibora(0, 0)));
		//agregarJugador(new Jugador("Mati", "uesaaaaa", new Vibora(1,1)));
		//agregarJugador(new Jugador("Pablo", "ke ase", new Vibora(2, 2)));
		//agregarJugador(new Jugador("Facu", "papu", new Vibora(3, 3)));
		
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				panelArena.moverVibora(e.getKeyCode());
			}
		});
	}

	private void crearComponentes() {
		gbl = new GridBagLayout();
		panelContenedor = new JPanel();
		panelArena = new ArenaJPanel(this);
		panelPuntos = new PuntosJPanel();
		listaJugadores = new ArrayList<>();
		listaColores = new ArrayList<>();
		crearListaColores();
	}

	private void crearLayout() {
		panelContenedor.setBackground(new Color(27, 27, 28));
		panelContenedor.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelContenedor.setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 880;
		gbc.ipady = 660;
		panelContenedor.add(panelArena, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		panelContenedor.add(panelPuntos, gbc);
	}

	public void agregarJugador(Jugador nuevoJugador) {
		resetearColoresAAsignar();
		
		listaJugadores.add(nuevoJugador);
		panelPuntos.agregarJugador(nuevoJugador.getNombre(), listaColores.get(colorAAsignar));
		panelArena.agregarVibora(nuevoJugador.getVivorita(), listaColores.get(colorAAsignar), listaColores.get(colorAAsignar - 1));
		ArenaVentana.colorAAsignar += 2;
	}
	
	public void agregarIA(Jugador nuevoIA){
		resetearColoresAAsignar();
		
		listaJugadores.add(nuevoIA);
		panelPuntos.agregarJugador(nuevoIA.getNombre(), listaColores.get(colorAAsignar));
		nuevoIA.getVivorita().setIA(true);
		nuevoIA.getVivorita().setTipoIA((new Random().nextInt(2)+1));
		panelArena.agregarVibora(nuevoIA.getVivorita(), listaColores.get(colorAAsignar), listaColores.get(colorAAsignar - 1));
		ArenaVentana.colorAAsignar += 2;	
	}

	private void resetearColoresAAsignar() {
		if(ArenaVentana.colorAAsignar >= listaColores.size())
			ArenaVentana.colorAAsignar = 1;
	}
	
	private void crearListaColores() {
		listaColores.add(new Color(255, 102, 102)); ///rosa oscuro
		listaColores.add(new Color(255, 77, 77)); ///rosa claro
		listaColores.add(new Color(51, 133, 255)); /// celeste
		listaColores.add(new Color(26, 117, 255)); /// celeste oscuro
		listaColores.add(new Color(255, 184, 77)); ///naranja apagado
		listaColores.add(new Color(255, 153, 0)); ///naranja oscuro
		listaColores.add(new Color(77, 255, 136)); /// verde agua
		listaColores.add(new Color(0, 179, 60)); /// verde agua oscuro
	}
	
	public void actualizarPuntosJugador(Vibora vibora, int puntajeNuevo) {
		for (Jugador j : listaJugadores) {
			if(j.getVivorita() == vibora) {
				j.actualizarPuntaje(puntajeNuevo);
				panelPuntos.actualizarPuntosJugador(j.getNombre(), puntajeNuevo);
			}
		}
	}
}
