package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SinglePlayerJPanel extends JPanel {

	JButton botonComenzar;
	
	public SinglePlayerJPanel() {
		crearComponentes();
		crearLayout();
		
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(botonComenzar);
	    add(Box.createRigidArea(new Dimension(0, 20)));
	}

	private void crearLayout() {
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	private void crearComponentes() {
		botonComenzar = new JButton("Comenzar partida");
		botonComenzar.setMaximumSize(new Dimension(200, 50));
		botonComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonComenzar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comenzarJuego();
			}
		});
	}
	
	private void comenzarJuego() {
		new ArenaVentana().setVisible(true);
	}
}
