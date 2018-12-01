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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mensajeria.MjeServerPrincipal;

public class MultiPlayerJPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8718835659937511720L;
	
	private JPanel panelNombre;
	private JPanel panelButtons;
	private JButton crearSalaButton;
	private JButton buscarSalasButton;
	private JLabel ingresarNombreLabel;
	private JLabel nombreViboraLabel;
	private JTextField ingresarNombreField;
	
	private String nombreVibora;
	
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
		panelNombre = new JPanel();
		panelNombre.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelNombre.setBackground(Color.BLACK);
		
		panelButtons = new JPanel();
		panelButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelButtons.setBackground(Color.BLACK);
		
		ingresarNombreLabel = new JLabel("Ingresa el nombre de tu vibora: ");
		ingresarNombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ingresarNombreLabel.setMaximumSize(new Dimension(100, 25));
		ingresarNombreLabel.setForeground(Color.WHITE);
		
		nombreViboraLabel = new JLabel();
		nombreViboraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreViboraLabel.setMaximumSize(new Dimension(100, 25));
		
		ingresarNombreField = new JTextField(40);
		ingresarNombreField.setMaximumSize(new Dimension(200, 30));
		ingresarNombreField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((nombreVibora = ingresarNombreField.getText()).equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(MultiPlayerJPanel.this, "Ingresa un nombre para la vibora", 
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
				else {
					nombreViboraLabel.setText(nombreVibora);
					panelNombre.remove(ingresarNombreField);
					panelNombre.add(nombreViboraLabel);
				}
			}
		});
		
		Dimension maxSize = new Dimension(200, 50);
		
		crearSalaButton = new JButton("Crear sala");
		crearSalaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearSalaButton.setMaximumSize(maxSize);
		crearSalaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nombreVibora == null || nombreVibora.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(MultiPlayerJPanel.this, "Ingresa un nombre para la vibora", 
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				else
					lanzarCreadorSala();
			}
		});
		
		buscarSalasButton = new JButton("Buscar salas");
		buscarSalasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buscarSalasButton.setMaximumSize(maxSize);
		buscarSalasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nombreVibora == null || nombreVibora.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(MultiPlayerJPanel.this, "Ingresa un nombre para la vibora", 
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				else
					lanzarBuscadorSalas();
			}
		});
	}
	
	private void crearLayout() {
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
		
		panelNombre.add(nombreViboraLabel);
		panelNombre.add(Box.createRigidArea(new Dimension(5, 0)));
		panelNombre.add(ingresarNombreField);
		
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(panelNombre);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 20)));
		
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		panelButtons.add(crearSalaButton);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 20)));
		panelButtons.add(buscarSalasButton);
		add(panelButtons);
		
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
		new BuscarSalasVentana(socketServerPrincipal, entrada, salida, nombreVibora).setVisible(true);
	}
}
