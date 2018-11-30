package gui;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import mensajeria.*;

public class BuscarSalasVentana extends JFrame {
	
	private JPanel contenido;
	private JList<MjeSalasDisp> listaSalas;
	private JList<String> listaSalasPrueba;
	private JScrollPane panelScroll;
	
	Socket socketServerPrincipal;
	ObjectInputStream entrada;
	ObjectOutputStream salida;
	
	Socket socketSala;
	
	private JLabel salasLabel;
	
	public BuscarSalasVentana(Socket s, ObjectInputStream in, ObjectOutputStream out) {
		super("Buscador");
		this.socketServerPrincipal =s;
		this.entrada = in;
		this.salida = out;
		crearComponentes();
		crearLayout();
		
		crearSalasPrueba();	// Aca solo creo unas salas (String) para ver como queda
		/**
		 * Dejo esto aca porque despues no se bien donde ponerlo.
		 * Al hacer doble click sobre algun elemento de la lista ya deberia poder abrir una ventana (o Jpanel o no se) nueva con la arena de multiplayer.
		 * Voy a hacerte una funcion para que conecte y eso y despues vemos bien como manejar el tema de que lanzar o como que eso lo sabes mas vos
		 */
	}

	private void crearLayout() {
		setContentPane(contenido);
		setSize(375, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contenido.add(salasLabel);
		contenido.add(panelScroll);
		
		panelScroll.setViewportView(listaSalasPrueba);
	}
	
	private void conectarseASala(int nroPuerto) {
		try {
			socketSala = new Socket("127.0.0.1",nroPuerto);
			ObjectInputStream buffarini = new ObjectInputStream(socketSala.getInputStream());
			ObjectOutputStream bufferOut = new ObjectOutputStream(socketSala.getOutputStream());
			//aca lanza vos la ArenaVentanaMultiplayer o algo asi, y fijate de poner en el constructor las cosas que declare arriba.
			//es mucho muy importante.
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}

	private void crearComponentes() {
		contenido = new JPanel();
		contenido.setBorder(new EmptyBorder(0, 0, 0, 0));
		contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
		
		panelScroll = new JScrollPane();
		panelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		listaSalasPrueba = new JList<>();
		
		salasLabel = new JLabel("Salas disponibles:");
		salasLabel.setMaximumSize(new Dimension(100, 30));	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void crearSalasPrueba() {
		DefaultListModel modelo = new DefaultListModel();
		modelo.addElement("Sala 1");
		modelo.addElement("Sala 2");
		modelo.addElement("Sala 3");
		modelo.addElement("Sala 4");
		modelo.addElement("Sala 5");
		modelo.addElement("Sala 6");
		modelo.addElement("Sala 7");
		modelo.addElement("Sala 8");
		modelo.addElement("Sala 9");
		modelo.addElement("Sala 10");
		modelo.addElement("Sala 11");
		modelo.addElement("Sala 12");
		modelo.addElement("Sala 13");
		modelo.addElement("Sala 14");
		modelo.addElement("Sala 15");
		modelo.addElement("Sala 16");
		modelo.addElement("Sala 17");
		modelo.addElement("Sala 18");
		modelo.addElement("Sala 19");
		modelo.addElement("Sala 20");
		listaSalasPrueba.setModel(modelo);
	}
}
