package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameObject.Jugador;
import mensajeria.MjeServerPrincipal;
import mensajeria.MjeViboraNueva;
import mensajeria.MsjeXeYNuevo;

public class ArenaMultiPlayerVentana extends JFrame {
	
	private Jugador yo;
	private ArenaMultiPlayerJPanel panelArena;
	private JPanel panelContenedor;
	private PuntosJPanel panelPuntos;
	private GridBagLayout gbl;
	
	Socket socketServerPrincipal;
	ObjectInputStream entrada;
	ObjectOutputStream salida;
	
	public ArenaMultiPlayerVentana(Socket socketServerPrincipal, ObjectInputStream entrada,
			ObjectOutputStream salida, Jugador yo) throws HeadlessException {
		super("Arena multiplayer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200,30,1100,700);
		setResizable(false);
		
		this.yo = yo;
		yo.getVivorita().setColorCabeza(Color.MAGENTA);
		yo.getVivorita().setColorCabeza(Color.RED);
		this.socketServerPrincipal = socketServerPrincipal;
		this.entrada = entrada;
		this.salida = salida;
		
		try {
			salida.writeObject(new MjeViboraNueva(yo.getVivorita(), yo.getNombre()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crearComponentes();
		crearLayout();
		
		setContentPane(panelContenedor);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				panelArena.moverVibora(e.getKeyCode());
				//salida.writeObject(new MsjeXeYNuevo());
			}
		});
	}
	
	private void crearComponentes() {
		gbl = new GridBagLayout();
		panelContenedor = new JPanel();
		panelArena = new ArenaMultiPlayerJPanel(yo.getVivorita(), entrada, salida);
		panelPuntos = new PuntosJPanel();
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
		panelContenedor.add(panelContenedor, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		panelContenedor.add(panelPuntos, gbc);
	}
}
