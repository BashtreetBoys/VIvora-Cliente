package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mensajeria.MjeServerPrincipal;

public class MultiPlayerJPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8718835659937511720L;
	
	private JButton crearSalaButton;
	private JButton buscarSalasButton;
	Socket socketServerPrincipal;
	ObjectInputStream entrada;
	ObjectOutputStream salida;
	
	
	public MultiPlayerJPanel(Socket s, ObjectInputStream entrada, ObjectOutputStream salida) {
		crearComponentes();
		crearLayout();
		this.socketServerPrincipal = s;
		this.entrada = entrada;
		this.salida = salida;
	}

	private void crearComponentes() {
		Dimension maxSize = new Dimension(200, 50);
		
		crearSalaButton = new JButton("Crear sala");
		crearSalaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearSalaButton.setMaximumSize(maxSize);
		crearSalaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lanzarCreadorSala();
			}
		});
		
		buscarSalasButton = new JButton("Buscar salas");
		buscarSalasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buscarSalasButton.setMaximumSize(maxSize);
		buscarSalasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lanzarBuscadorSalas();
			}
		});
	}
	
	private void crearLayout() {
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(crearSalaButton);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(buscarSalasButton);
		add(Box.createRigidArea(new Dimension(0, 20)));
	}
	
	private void lanzarCreadorSala() {
		new CrearSalaVentana(this, this.socketServerPrincipal, this.entrada, this.salida).setVisible(true);
	}
	
	public void crearSala(String nombreSala) {	// no me gusta cambiarlo de private a public pero es temporal
		MjeServerPrincipal paraEnviar = new MjeServerPrincipal();
		paraEnviar.quieroConectarmeASala =false;
		paraEnviar.quieroDesconectarme = false;
		paraEnviar.quieroSalas = false;
		paraEnviar.quieroCrearSalas = true;
		paraEnviar.nombreSala = nombreSala;
		try {
			salida.writeObject(paraEnviar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void lanzarBuscadorSalas() {
		new BuscarSalasVentana(socketServerPrincipal, entrada, salida).setVisible(true);
	}
}
