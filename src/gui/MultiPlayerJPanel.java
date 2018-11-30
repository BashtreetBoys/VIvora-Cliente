package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MultiPlayerJPanel extends JPanel {
	
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
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(crearSalaButton);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(buscarSalasButton);
		add(Box.createRigidArea(new Dimension(0, 20)));
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
	}
	
	private void lanzarCreadorSala() {
		System.out.println("Falta implementar");
		
	}
	
	private void lanzarBuscadorSalas() {
		new BuscarSalasVentana().setVisible(true);
	}
}
