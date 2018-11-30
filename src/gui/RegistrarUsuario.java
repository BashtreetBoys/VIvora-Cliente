package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import connection.ConexionHibernate;
import mensajeria.MensajeUsuario;

public class RegistrarUsuario extends JFrame {
	
	Socket serverSocket;
	ObjectInputStream bufferIn;
	ObjectOutputStream bufferOut;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609160768508516389L;
	
	public RegistrarUsuario(Socket s, ObjectOutputStream out, ObjectInputStream in) {
		super("Registrar");
		setSize(225, 340);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		add(panel);
		placeComponents(panel);		
		this.serverSocket = s;
		this.bufferIn = in;
		this.bufferOut = out;
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);
		//Dimensiones de los componentes
		
		Dimension maxSizeSep = new Dimension(0,5);
		
		//Usuario label+text
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 0, 80, 25);
		panel.add(userLabel);

		panel.add(Box.createRigidArea(maxSizeSep));
		
		JTextField userText = new JTextField(20);
		userText.setBounds(10, 25, 200, 30);
		panel.add(userText);
		
		//Fin usuario
		
		panel.add(Box.createRigidArea(maxSizeSep));
		
		//Contraseña label+text
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 65, 80, 25);
		panel.add(passwordLabel);
		
		panel.add(Box.createRigidArea(maxSizeSep));
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(10, 90, 200, 30);
		panel.add(passwordText);
		
		//Fin contraseña
		panel.add(Box.createRigidArea(maxSizeSep));
		
		//ConfirmarContraseña label+text
		
		JLabel confirmPasswdLabel = new JLabel("Confirmar Password");
		confirmPasswdLabel.setBounds(10, 130, 120, 25);
		panel.add(confirmPasswdLabel);
		
		panel.add(Box.createRigidArea(maxSizeSep));
		
		JPasswordField confirmPasswdText = new JPasswordField(20);
		confirmPasswdText.setBounds(10, 155, 200, 30);
		panel.add(confirmPasswdText);
		
		//Fin confirmar contraseña
		
		//Boton registrarse
		JButton registerButton = new JButton("Registrarse");
		registerButton.setBounds(35, 200, 150, 40);
		panel.add(registerButton);
		
		//Fin boton registrarse
		panel.add(Box.createRigidArea(maxSizeSep));
		
		//Boton loguearse
		JButton loginButton = new JButton("Volver al login");
		loginButton.setBounds(35, 250, 150, 40);
		panel.add(loginButton);
		
		//Fin boton loguearse
	
		ConexionHibernate conexion = new ConexionHibernate(serverSocket, bufferIn, bufferOut);
		
		ActionListener listenerLoguearse = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				setVisible(false);
			
			}
		};
		
		ActionListener listenerRegistrarse = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				String user = new String(userText.getText());
				String pass = new String(passwordText.getPassword());
				String confPass = new String(confirmPasswdText.getPassword());
				if(!(pass.equals(confPass))){
					JOptionPane.showMessageDialog(source, "Las contraseñas no coinciden");
				}
				else{
					MensajeUsuario mu = new MensajeUsuario(user, pass, true);
					boolean band = conexion.registrarUsuario(user, pass);
					if(band){
						JOptionPane.showMessageDialog(source, "Se ha registrado su usuario!");
					}
					else{
						JOptionPane.showMessageDialog(source, "Algo salió mal en el registro de su usuario");
					}
				}
				
			}
		};
		loginButton.addActionListener(listenerLoguearse);
		registerButton.addActionListener(listenerRegistrarse);
		}			
	}

