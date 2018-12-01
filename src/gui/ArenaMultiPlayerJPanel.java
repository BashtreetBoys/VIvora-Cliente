package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;
import javax.swing.Timer;

import gameObject.Arena;
import gameObject.Cuerpo;
import gameObject.Obstaculo;
import gameObject.Vibora;
import mensajeria.MjeArenaNueva;
import mensajeria.MsjeXeYNuevo;

public class ArenaMultiPlayerJPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3742581923345352721L;
	
	private Arena arena;
	private Vibora miVibora;
	private int keyCodeRegistrado;
	private Timer t = new Timer(60, this);
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	public ArenaMultiPlayerJPanel(Vibora miVibora, ObjectInputStream entrada, ObjectOutputStream salida) {
		this.miVibora = miVibora;
		this.entrada = entrada;
		this.salida = salida;
		
		t.start();
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
	
	private void pintarVibora(Graphics g, Vibora v) {
		if(v.getEstado()==0) {
			v.setColorCabeza(v.getColorOrigCab());
			v.setColorCuerpo(v.getColorOrigCuerpo());
		}
		else if(v.getEstado()==1) {
			v.setColorCabeza(new Color(225,232,0));
			v.setColorCuerpo(new Color(247,255,0));
		}
		else if(v.getEstado()==2) {
			v.setColorCabeza(new Color(183,2,192));
			v.setColorCuerpo(new Color(243,0,255));
		}
		else if(v.getEstado()==3) {
			v.setColorCabeza(new Color(203,5,5));
			v.setColorCuerpo(new Color(255,0,0));
		}
		else if(v.getEstado()==4) {
			v.setColorCabeza(new Color(226,226,226));
			v.setColorCuerpo(new Color(255,255,255));
		}
	
		g.setColor(v.getColorCabeza());
		g.fillRect(v.getCabeza().getPosX(), v.getCabeza().getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);

		g.setColor(v.getColorCuerpo());
		for (Cuerpo pedacitoCuerpo : v.getCuerpito()) {
			g.fillRect(pedacitoCuerpo.getPosX(), pedacitoCuerpo.getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);
		}
		
	}

	private void pintarFruta(Graphics g) {
		g.setColor(new Color(255, 235, 46));
		g.fillRect(arena.getFrutaActual().getPosX(), arena.getFrutaActual().getPosY(), Arena.TAM_GRAFICOS, Arena.TAM_GRAFICOS);
	}

	private void pintarObstaculos(Graphics g) {
		g.setColor(Color.RED);
		for (Obstaculo obstaculo : arena.getObstaculos()) {

			g.fillRect(obstaculo.getPosXini(), obstaculo.getPosYini(), obstaculo.getPosXfin() - obstaculo.getPosXini(),
					obstaculo.getPosYfin() - obstaculo.getPosYini());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		miVibora.moverVibora(keyCodeRegistrado);
		try {
			salida.writeObject(new MsjeXeYNuevo(miVibora));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			MjeArenaNueva nuevaArena = (MjeArenaNueva) entrada.readObject();
			arena = nuevaArena.arenaNueva;
			miVibora.setViva(nuevaArena.teMoriste);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		repaint();
	}

	public void moverVibora(int keyCode) {
		keyCodeRegistrado = keyCode;
	}
	
	public Vibora getVibora() {
		return miVibora;
	}
}
