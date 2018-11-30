package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.hibernate.mapping.Collection;

import gameObject.*;

public class ArenaJPanel extends JPanel implements ActionListener {

	private ArenaVentana padre;	// seguro que esto es una re negrada

	private Arena arena;
	private Timer t = new Timer(80, this);
	private Vibora vibora;
	private Fruta fruta;
	private Cuerpo cuerpo;
	int keyCodeRegistrado;
	private ArrayList<Obstaculo> obs;
	ArrayList<Color> listaColores = new ArrayList<Color>();

	public ArenaJPanel(ArenaVentana padre) {
		super();
		crearLayout();
		setBackground(Color.BLACK);
		this.padre = padre;
		arena = new Arena();

		cuerpo = new Cuerpo(0,0); // supongo que estos sirven para ver las colisiones
		vibora = new Vibora(0, 0);	// solo me sirven para ver su clase
		arena.setLv(1);
		arena.cambiarNivel();
		fruta = arena.getFrutaActual();

		obs = arena.getObstaculos();
		
		this.keyCodeRegistrado = aKeyCode(vibora.getDireccion());
		System.out.println(vibora.getDireccion());
		t.start();
	}

	private void crearLayout() {
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(860, 660));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/* Aca pinto los obstaculos */
		pintarObstaculos(g);

		/* Aca pinto la fruta */
		pintarFruta(g);

		/* A partir de aca pinto las serpientes */
		for(Vibora v : arena.getViboras())
			pintarVibora(g, v);
//		pintarVibora(g,this.vibora2);
//		pintarVibora(g,this.vibora3);
	}

	private void pintarObstaculos(Graphics g) {
		g.setColor(Color.RED);
		for (Obstaculo obstaculo : obs) {

			g.fillRect(obstaculo.getPosXini(), obstaculo.getPosYini(), obstaculo.getPosXfin() - obstaculo.getPosXini(),
					obstaculo.getPosYfin() - obstaculo.getPosYini());
		}
	}

	private void pintarFruta(Graphics g) {
		g.setColor(new Color(255, 235, 46));
		g.fillRect(fruta.getPosX(), fruta.getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);
	}

	private void pintarVibora(Graphics g,Vibora v) {
		// g.setColor(new Color(255, 83, 76)); si no jode a los ojos dejar este color
		g.setColor(v.getColorCabeza());
		g.fillRect(v.getCabeza().getPosX(), v.getCabeza().getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);

		g.setColor(v.getColorCuerpo());
		for (Cuerpo pedacitoCuerpo : v.getCuerpito()) {
			g.fillRect(pedacitoCuerpo.getPosX(), pedacitoCuerpo.getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		for(Vibora v : arena.getViboras()) {
			if (!v.isViva()) {
				t.stop();
				System.out.println("Se te murio la vibora");
			}
		
			v.moverVibora(keyCodeRegistrado);
			// t.stop();  // lo dejo porque capaz con el synchronized no se soluciona el problema de los threads
			arena.verColision(v.getCabeza().getPosX(), v.getCabeza().getPosY(),v);
			padre.actualizarPuntosJugador(v, v.getPuntosPartidaActual());
			// t.start(); // lo dejo porque capaz con el synchronized no se soluciona el problema de los threads
		
			repaint();
		}
	}

	public void moverVibora(int keyCode) {
		if (keyCode != aKeyCode(vibora.getDireccion()) && keyCode != aKeyCodeOpuesto(vibora.getDireccion()))
			System.out.println(keyCode);
			this.keyCodeRegistrado = keyCode;
	}

	private int aKeyCode(int direccion) {

		if (direccion == 1)
			return KeyEvent.VK_UP;
		else if (direccion == 2)
			return KeyEvent.VK_RIGHT;
		else if (direccion == 3)
			return KeyEvent.VK_DOWN;
		else
			return KeyEvent.VK_LEFT;
	}

	private int aKeyCodeOpuesto(int direccion) {

		if (direccion == 3)
			return KeyEvent.VK_UP;
		else if (direccion == 4)
			return KeyEvent.VK_RIGHT;
		else if (direccion == 1)
			return KeyEvent.VK_DOWN;
		else
			return KeyEvent.VK_LEFT;
	}

	public void agregarVibora(Vibora vivorita, Color colorCabeza, Color colorCuerpo) {
		vivorita.setColorCabeza(colorCabeza);
		vivorita.setColorCuerpo(colorCuerpo);
		arena.agregarVibora(vivorita);
	}
}