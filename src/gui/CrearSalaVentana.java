package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mensajeria.MjeServerPrincipal;

public class CrearSalaVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5138969921303296079L;
	
	private JPanel contenido;
	private JPanel contenedorLabelField;
	private JPanel contenedorButtons;
	private JLabel crearSalaLabel;
	private JTextField nombreSalaField;
	private JButton crearSalaButton;
	private JButton cancelarButton;
	
	private String nombreSala = null;
	private MultiPlayerJPanel padre;
	
	public CrearSalaVentana(MultiPlayerJPanel padre) {
		super("Creador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(330, 130);
		setLocationRelativeTo(null);
		
		this.padre = padre;
		
		crearComponentes();
		crearLayout();
		
		setContentPane(contenido);
	}
	
	private void crearComponentes() {
		contenido = new JPanel();
		contenedorLabelField = new JPanel();
		contenedorButtons = new JPanel();
		
		crearSalaLabel = new JLabel("Ingresa el nombre de tu sala: ");
		crearSalaLabel.setMaximumSize(new Dimension(100, 25));
		
		nombreSalaField = new JTextField(40);
		nombreSalaField.setMaximumSize(new Dimension(200, 30));
		
		Dimension buttonMaxSize = new Dimension(100, 30);
		crearSalaButton = new JButton("Crear sala");
		crearSalaButton.setMaximumSize(buttonMaxSize);
		crearSalaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if((nombreSala = nombreSalaField.getText()).equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(CrearSalaVentana.this, "Ingresa un nombre para la sala", 
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(padre != null)
						padre.crearSala(nombreSala);
					CrearSalaVentana.this.dispose();
				}
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
		contenedorLabelField.add(Box.createRigidArea(new Dimension(5, 0)));
		contenedorLabelField.add(nombreSalaField);
		
		contenido.add(Box.createRigidArea(new Dimension(0, 10)));
		contenido.add(contenedorLabelField);
		
		contenedorButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
		contenedorButtons.setLayout(new BoxLayout(contenedorButtons, BoxLayout.X_AXIS));
		contenedorButtons.add(crearSalaButton);
		contenedorButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		contenedorButtons.add(cancelarButton);
		
		contenido.add(Box.createRigidArea(new Dimension(0, 10)));
		contenido.add(contenedorButtons);
		contenido.add(Box.createRigidArea(new Dimension(0, 10)));
	}
	
	public String getNombreSala() {
		return nombreSala;
	}
	
	public static void main(String args[]) {
		new CrearSalaVentana(null).setVisible(true);
	}
}
