package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pruebona extends JFrame {

	private JPanel contentPane;
	private JList list;

	public Pruebona() {
		super("Probando un JList");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 295);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 331, 267);
		contentPane.add(scrollPane);

		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (arg0.getClickCount() == 2) {
					mostrarSeleccionDeLaLista((String) list.getSelectedValue());
				}
			}
		});
		DefaultListModel modelo = new DefaultListModel();
		modelo.addElement("Rojo Claro");
		modelo.addElement("Rojo Oscuro");
		modelo.addElement("Verde Claro");
		modelo.addElement("Verde Oscuro");
		modelo.addElement("Marrón Claro");
		modelo.addElement("Marrón Claro");
		modelo.addElement("Azul");
		modelo.addElement("Celeste");
		modelo.addElement("Negro");
		modelo.addElement("Violeta");
		modelo.addElement("Rosa Claro");
		modelo.addElement("Rosa Oscuro");
		modelo.addElement("Naranja");
		modelo.addElement("Blanco");
		modelo.addElement("Amarillo");
		modelo.addElement("Gris Claro");
		modelo.addElement("Gris Oscuro");
		list.setModel(modelo);
		scrollPane.setViewportView(list);

		setLocationRelativeTo(null);
	}

	public void mostrarSeleccionDeLaLista(String seleccionado) {
		JOptionPane.showMessageDialog(this, seleccionado);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Pruebona().setVisible(true);
	}
}
