package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mensajeria.MjeServerPrincipal;

public class CrearSalaVentana extends JFrame {

	private JPanel contenido;
	private JPanel contenedorLabelField;
	private JPanel contenedorButtons;
	private JLabel crearSalaLabel;
	private JTextField nombreSalaField;
	private JButton crearSalaButton;
	private JButton cancelarButton;
	
	public CrearSalaVentana() {
		super("Creador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 350);
		setLocationRelativeTo(null);
		
		crearComponentes();
		crearLayout();
	}
	
	private void crearComponentes() {
		contenido = new JPanel();
		contenedorLabelField = new JPanel();
		contenedorButtons = new JPanel();
		
		crearSalaLabel = new JLabel("Ingresa el nombre de tu sala: ");
		crearSalaLabel.setMaximumSize(new Dimension(100, 30));
		
		nombreSalaField = new JTextField("Mi sala", 20);
		nombreSalaField.setMaximumSize(new Dimension(150, 40));
		
		Dimension buttonMaxSize = new Dimension(150, 40);
		crearSalaButton = new JButton("Crear sala");
		crearSalaButton.setMaximumSize(buttonMaxSize);
		crearSalaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CrearSalaVentana.this.dispose();
			}
		});
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setMaximumSize(buttonMaxSize);
		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CrearSalaVentana.this.dispose();
			}
		});
	}

	private void crearLayout() {
		
		contenido.setBorder(new EmptyBorder(0, 0, 0, 0));
		contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
		
		contenedorLabelField.setBorder(new EmptyBorder(0, 0, 0, 0));
		contenedorLabelField.setLayout(new BoxLayout(contenedorLabelField, BoxLayout.X_AXIS));
		contenedorLabelField.add(crearSalaLabel);
		contenedorLabelField.add(nombreSalaField);
		
		contenido.add(contenedorLabelField);
		
		contenedorButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
		contenedorButtons.setLayout(new BoxLayout(contenedorLabelField, BoxLayout.X_AXIS));
		contenedorButtons.add(crearSalaLabel);
		contenedorButtons.add(nombreSalaField);
	}
}
