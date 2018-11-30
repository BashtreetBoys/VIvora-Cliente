package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import connection.ConexionHibernate;

public class Login extends JFrame {
/*
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Login(new CardLayout(), new JPanel()).setVisible(true);
	}
	*/
	Socket socketServerPrincipal;
	ObjectOutputStream salida;
	ObjectInputStream entrada;
	private CardLayout clContenedora;
	private JPanel panelContenedor;
	private JPanel panelLogin;
/*
 * En el constructor ahora le estoy paasando el socket y los stream, asi como esta aca que les quedo barbaro hagan las otras ventanas de multiplayer
 * Las de singleplayer no hace falta.
 * 
 */
	public Login(CardLayout clContenedora, JPanel panelContenedor, Socket s, ObjectOutputStream out, ObjectInputStream in) throws UnknownHostException, IOException {
		super("Login");
		setSize(350, 275);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		this.socketServerPrincipal = s;
		this.entrada = in;
		this.salida = out;
		
		
		this.clContenedora = clContenedora;
		this.panelContenedor = panelContenedor;

		placeComponents();
		setContentPane(panelLogin);
	}

	private void placeComponents() throws UnknownHostException, IOException {

		panelLogin = new JPanel();
		panelLogin.setLayout(null);
//		panelLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
//		panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));

//		Dimension maxSizeLabel = new Dimension(100, 20);
//		Dimension maxSizeText = new Dimension(200, 30);
//		Dimension maxSizeButton = new Dimension(150, 40);

		JLabel userLabel = new JLabel("User");
//		userLabel.setMaximumSize(maxSizeLabel);
//		userLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		userLabel.setBounds(10, 0, 80, 25);

		panelLogin.add(userLabel);

		JTextField userText = new JTextField(20);
//		userText.setMaximumSize(maxSizeText);
//		userText.setAlignmentX(Component.CENTER_ALIGNMENT);
		userText.setBounds(10, 25, 200, 30);

		panelLogin.add(userText);

		JLabel passwordLabel = new JLabel("Password");
//		passwordLabel.setMaximumSize(maxSizeLabel);
//		passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		passwordLabel.setBounds(10, 65, 120, 25);

		panelLogin.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
//		passwordText.setMaximumSize(maxSizeText);
//		passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordText.setBounds(10, 90, 200, 30);

		panelLogin.add(passwordText);

		JButton loginButton = new JButton("Loguearse");
//		loginButton.setMaximumSize(maxSizeButton);
//		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setBounds(35, 135, 150, 40);

		panelLogin.add(loginButton);

		JButton registerButton = new JButton("Registrarse");
//		registerButton.setMaximumSize(maxSizeButton);
//		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerButton.setBounds(35, 185, 150, 40);

		panelLogin.add(registerButton);

		RegistrarUsuario regUser = new RegistrarUsuario();
		/*Socket socketServerPrincipal = new Socket("127.0.0.1",10258);
		ObjectOutputStream salida = new ObjectOutputStream(socketServerPrincipal.getOutputStream());
		ObjectInputStream entrada  = new ObjectInputStream(socketServerPrincipal.getInputStream());*/
		ActionListener listenerLoguearse = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				String user = new String(userText.getText());
				String pass = new String(passwordText.getPassword());
				
				ConexionHibernate conexion = new ConexionHibernate(socketServerPrincipal,entrada, salida);
				if (conexion.verSiExiste(user, pass)) {
					JOptionPane.showMessageDialog(Login.this, "Bienvenido/a " + user, "Loggeo exitoso",
							JOptionPane.PLAIN_MESSAGE);
					Login.this.dispose();
					clContenedora.show(panelContenedor, "Menu");
				} else
					JOptionPane.showMessageDialog(source, "No existe ese usuario con esa contraseña");
			}
		};

		ActionListener listenerRegistrarse = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JButton source = (JButton) e.getSource();
				regUser.setVisible(true);
			}
		};
		loginButton.addActionListener(listenerLoguearse);
		registerButton.addActionListener(listenerRegistrarse);
	}
}