package gameObject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
	// Esta variable define el tamaño de cada cuadrado que dibujamos en la arena
	// Puede cambiar a futuro, depende de nuestro gusto
	public static final int TAM_GRAFICOS = 20;

	private int tamaño;
	private ArrayList<Vibora> viboras;
	private ArrayList<Obstaculo> obstaculos;
	private Fruta frutaActual;
	private int lv;
	private int cantidadFrutas;
	int cont;
	boolean chocó = false;
	int dirección = 0;

	public Arena() {
		super();
		this.tamaño = 100;
		this.viboras = new ArrayList<Vibora>();
		this.obstaculos = new ArrayList<Obstaculo>();
		this.lv = 1;
		this.frutaActual = new Fruta(0, 0, 0, 1);
		cont = 0;
	}

	public void agregarFruta(Fruta frutaNueva) {

		/* Random */
		int fil = (int) Math.round(580 / Arena.TAM_GRAFICOS);
		int col = (int) Math.round(760 / Arena.TAM_GRAFICOS);

		int x = new Random().nextInt(col) * Arena.TAM_GRAFICOS; // Nos da una
																// fila random
																// entre las que
																// tenemos
		int y = new Random().nextInt(fil) * Arena.TAM_GRAFICOS; // Nos da una
																// columna
																// random entre
																// las que
																// tenemos

		System.out.println(x + ", " + y);
		/*
		 * En estos momentos como no puedo setear el tamaño de la arena como
		 * quiero a veces la fruta sale de la arena, preguntar como solucionar
		 * ese asunto del redimensionado del ArenaJPanel y asi poder trabajar
		 * con la cantidad correcta de filas y columnas
		 *
		 */

		while (verColision(x, y, null)) {
			x = new Random().nextInt(col) * Arena.TAM_GRAFICOS;
			y = new Random().nextInt(fil) * Arena.TAM_GRAFICOS;
		}
		frutaNueva.setPosX(x);
		frutaNueva.setPosY(y);
	}

	public synchronized boolean verColision(int x, int y, Vibora vibActual) {

		boolean band = false;
		/* Verifica si hay una fruta en la posición */
		if (x == frutaActual.getPosX() && y == frutaActual.getPosY()) {
			if (vibActual != null) {
				System.out.println("Entre a colision fruta");
				colisionarFruta(vibActual);
			}
			band = true;
		}

		/* Verifica si hay un obstáculo en la posición */
		for (int i = 0; i < obstaculos.size(); i++) {
			int posXini = obstaculos.get(i).getPosXini();
			int posYini = obstaculos.get(i).getPosYini();
			int posXfin = obstaculos.get(i).getPosXfin();
			int posYfin = obstaculos.get(i).getPosYfin();

			/*
			 * if (posXini == posYini) { if (posXini == x) { if (y >= posYini &&
			 * y <= posYfin) return obstaculos.get(i); } }
			 * 
			 * if (posXfin == posYfin) { if (posYini == y) { if (x >= posXini &&
			 * x <= posXfin) return obstaculos.get(i); } }
			 */
			// Veo si el punto pasado por parametro esta entre los valores X e Y
			// del obstaculo
			if ((x >= posXini && x <= posXfin) && (y >= posYini && y <= posYfin)) {
				if (vibActual != null) {
					colisionarConViboraOObstaculo(vibActual);
				}
				band = true;
			}
		}

		if(vibActual != null && vibActual.getEstado() == 4)
			return band;
		
		// Veo si la cabeza de alguna vibora choca con la cabeza de otra vibora
		int choco = 0, indice = 0;
		for (int i = 0; i < this.viboras.size(); i++) {
			// Veo si la cabeza de la vibora en la que estoy mirando choca con
			// la cabeza de alguna otra vibora
			if ((x == this.viboras.get(i).getCabeza().getPosX() && y == this.viboras.get(i).getCabeza().getPosY()))
				choco++;

			if (choco >= 1)
				indice = i;
		}

		if (choco == 2) {
			if (vibActual != null) {
				colisionarConViboraOObstaculo(vibActual);
				colisionarConViboraOObstaculo(this.viboras.get(indice - 1));
			}
			band = true;
			// return this.viboras.get(indice);
		}
		// Veo si la cabeza de alguna vibora choca con algun cuerpito de otra
		// vibora
		for (int i = 0; i < this.viboras.size(); i++) {
			for (int j = 0; j < this.viboras.get(i).getCuerpito().size(); j++) {
				if ((x == this.viboras.get(i).getCuerpito().get(j).getPosX()
						&& y == this.viboras.get(i).getCuerpito().get(j).getPosY())) {
					if (vibActual != null) {
						colisionarConViboraOObstaculo(vibActual);
					}
					band = true;
				}
			}
		}

		if (this.lv < 3 && vibActual != null) {
			if (this.cantidadFrutas > 21) {

				setLv(++lv);
				cambiarNivel();
			}
		}
		return band;
	}

	public void colisionarFruta(Vibora vibora) {
		Normal n = new Normal();
		
		if(vibora.getEstado() != 0 && this.frutaActual.getIdpowerup() != 0) {
			n.cambiarEstado(vibora);
			//System.out.println("volvi a la normalidad");
		}
		
		///si el estado de la vivora es invencible ignorar el colisionador de obtaculos y viboras
		///chupa almas 
		int b = 0;
		
		///////////////////////////////////////////////
		
		if(this.frutaActual.getIdpowerup() == 4) { ///POWER UP INVICIBLE
			
			Invisible i = new Invisible();
			i.cambiarEstado(vibora);
			vibora.sumarPuntos(10);	// Misma cantidad de puntos que fruta normal
		}
		else if(this.frutaActual.getIdpowerup()==0 && vibora.getEstado()==4) {
			
			n.cambiarEstado(vibora);
			vibora.sumarPuntos(10);	// Misma cantidad de puntos que fruta normal
		}
		
		///////////////////////////////////////////////
		
		if(this.frutaActual.getIdpowerup()==3) { ///POWER UP FLASH
			
			Flash f = new Flash();
			f.cambiarEstado(vibora);
			vibora.sumarPuntos(5);	// Misma cantidad de puntos que fruta normal
		}
		else if(this.frutaActual.getIdpowerup()==0 && vibora.getEstado()==3) {
			
			n.cambiarEstado(vibora);
			vibora.sumarPuntos(5);	// Misma cantidad de puntos que fruta normal
		}
		
		////////////////////////////////////////////////
		
		if(this.frutaActual.getIdpowerup()==1) { ///POWER UP DORADA
			
			Dorada d = new Dorada();
			d.cambiarEstado(vibora);
			vibora.sumarPuntos(20);	
		}
		else if(this.frutaActual.getIdpowerup()==0 && vibora.getEstado()==1) {
			
			vibora.crecer();
			n.cambiarEstado(vibora);
			b=1;
			vibora.sumarPuntos(20);	
		}
		
		//////////////////////////////////////////////////////////////
		
		if(this.frutaActual.getIdpowerup()==2) { ///POWER UP ENVENENADA
			
			Envenenada e = new Envenenada();
			e.cambiarEstado(vibora);
			b=1;
			//System.out.println("reste 1");
			vibora.sumarPuntos(-10);	
		}
		else if(this.frutaActual.getIdpowerup()==0 && vibora.getEstado()==2) {
			
			vibora.removerCuerpo();
			n.cambiarEstado(vibora);
			b=1;
			//System.out.println("reste 1");
			vibora.sumarPuntos(-10);
		}
		
		//////////////////////////////////////////////////////////////
		
		if(b==0) {
			vibora.crecer();
			vibora.sumarPuntos(10);
		}
		
		Random generador = new Random();
		System.out.println("cantidad de frutas " + this.cantidadFrutas);
		if((this.cantidadFrutas%5)==0 && this.cantidadFrutas!=0)
			this.frutaActual.setIdpowerup(1+generador.nextInt(4));
			//this.frutaActual.setIdpowerup(3);
		else
			this.frutaActual.setIdpowerup(0);
		System.out.println("id fruta "+this.frutaActual.getIdpowerup());
		//System.out.println("estado de la vibora "+vibora.getEstado());
		this.agregarFruta(frutaActual);
		this.cantidadFrutas++;
	}

	public void colisionarConViboraOObstaculo(Vibora vibora) {
		vibora.setViva(false);
		vibora.morir();
	}

	public void colisionarConViboraOObstaculo(Object vibora) {
		((Vibora) vibora).setViva(false);
		((Vibora) vibora).morir();
	}

	boolean colisionarVibora(Vibora viboraColisionaCon, Vibora vibora) {

		int cabezaEnX = viboraColisionaCon.getCabeza().getPosX();
		int cabezaEnY = viboraColisionaCon.getCabeza().getPosY();

		if (cabezaEnX == vibora.getCabeza().getPosX() && cabezaEnY == vibora.getCabeza().getPosY()) {

			viboraColisionaCon.setViva(false); // muere
			return true;
		}

		for (int i = 0; i < vibora.getCuerpito().size(); i++) {
			if (vibora.getCuerpito().get(i).getPosX() == cabezaEnX
					&& vibora.getCuerpito().get(i).getPosX() == cabezaEnY) {

				viboraColisionaCon.setViva(false); // muere
				return true;
			}
		}
		return false;
	}

	public void agregarVibora(Vibora v, int x, int y, int dir) {
		v.setVibora(x, y, dir);
	}

	public void agregarVibora(Vibora v) {
		viboras.add(v);
		int n;
		n = viboras.indexOf(v);
		viboras.get(n).setViva(true);
		switch (n) {
		case 0:
			viboras.get(n).setVibora(4, 4, 3);
			break;
		case 1:
			viboras.get(n).setVibora(20, 3, 2);
			break;
		case 2:
			viboras.get(n).setVibora(40, 2, 2);
			break;
		case 3:
			viboras.get(n).setVibora(15, 5, 2);
			break;
		case 4:
			viboras.get(n).setVibora(20, 10, 2);
			break;
		case 5:
			viboras.get(n).setVibora(15, 15, 2);
			break;
		case 6:
			viboras.get(n).setVibora(10, 15, 2);
			break;
		case 7:
			viboras.get(n).setVibora(9, 9, 2);
			break;
		}
	}

	public void cambiarNivel() {

		// METO LAS SERPIENTES QUE DEBEN EMPEZAR EN ESTE NIVEL
		ArrayList<Vibora> auxiliar = new ArrayList<Vibora>();

		Normal n = new Normal();
		
		for (int i = 0; i < viboras.size(); i++) {
			viboras.get(i).resetearCuerpo();
			n.cambiarEstado(viboras.get(i));
			if (viboras.get(i).isViva() == true)
				auxiliar.add(viboras.get(i));
		}
		viboras.clear();
		for (int i = 0; i < auxiliar.size(); i++) {
			this.agregarVibora(auxiliar.get(i));
		}

		// AGREGO LOS OBSTACULOS DEL NIVEL ACTUAL
		switch (lv) {
		case 1:
			obstaculos.clear();
			obstaculos.add(new Obstaculo(0, 0, 19, 640));
			obstaculos.add(new Obstaculo(19, 0, 880, 19));
			obstaculos.add(new Obstaculo(860, 19, 880, 640));
			obstaculos.add(new Obstaculo(0, 640, 880, 660));
			break;
		case 2:
			obstaculos.clear();
			obstaculos.add(new Obstaculo(0, 0, 19, 640));
			obstaculos.add(new Obstaculo(19, 0, 880, 19));
			obstaculos.add(new Obstaculo(860, 19, 880, 640));
			obstaculos.add(new Obstaculo(0, 640, 880, 660));
			obstaculos.add(new Obstaculo(220, 320, 659, 339));
			break;
		case 3:
			obstaculos.clear();
			obstaculos.add(new Obstaculo(0, 0, 19, 640));
			obstaculos.add(new Obstaculo(19, 0, 880, 19));
			obstaculos.add(new Obstaculo(860, 19, 880, 640));
			obstaculos.add(new Obstaculo(0, 640, 880, 660));
			obstaculos.add(new Obstaculo(560, 180, 880, 199));
			obstaculos.add(new Obstaculo(0, 380, 319, 399));
			obstaculos.add(new Obstaculo(320, 0, 339, 259));
			obstaculos.add(new Obstaculo(560, 400, 579, 660));
			break;
		case 4:
			obstaculos.clear();
			obstaculos.add(new Obstaculo(0, 0, 19, 640));
			obstaculos.add(new Obstaculo(19, 0, 880, 19));
			obstaculos.add(new Obstaculo(860, 19, 880, 640));
			obstaculos.add(new Obstaculo(0, 640, 880, 660));
			break;
		case 5:
			obstaculos.clear();
			obstaculos.add(new Obstaculo(0, 0, 19, 640));
			obstaculos.add(new Obstaculo(19, 0, 880, 19));
			obstaculos.add(new Obstaculo(860, 19, 880, 640));
			obstaculos.add(new Obstaculo(0, 640, 880, 660));
			break;
		}

		// Agregar frutas
		this.cantidadFrutas = 1;
		this.agregarFruta(frutaActual);
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public ArrayList<Vibora> getViboras() {
		return viboras;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public Fruta getFrutaActual() {
		return frutaActual;
	}

	public void setFrutaActual(Fruta frutaActual) {
		this.frutaActual = frutaActual;
	}

	public int getCantidadFrutas() {
		return cantidadFrutas;
	}

	public void setCantidadFrutas(int cantidadFrutas) {
		this.cantidadFrutas = cantidadFrutas;
	}

	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void inteligenciaArtificial(Vibora v) {

		if (v.getCabeza().getPosX() < frutaActual.getPosX()) {
			if (v.getCabeza().getPosY() > frutaActual.getPosY())
				v.setDireccion(1);
			else if (v.getCabeza().getPosY() < frutaActual.getPosY())
				v.setDireccion(3);
			else if (v.getDireccion() == 4) {
				v.setDireccion(1);
			} else {
				v.setDireccion(2);
			}
		} else if (v.getCabeza().getPosX() > frutaActual.getPosX()) {
			if (v.getCabeza().getPosY() > frutaActual.getPosY())
				v.setDireccion(1);
			else if (v.getCabeza().getPosY() < frutaActual.getPosY())
				v.setDireccion(3);
			else if (v.getDireccion() == 2) {
				v.setDireccion(3);
			} else {
				v.setDireccion(4);
			}
		}

		// if((verColision(v.getCabeza().getPosX()+TAM_GRAFICOS,
		// v.getCabeza().getPosY()+TAM_GRAFICOS)) != null && v.getDireccion() !=
		// 1)
		// v.setDireccion(1);

		v.moverVibora(v.getDireccion());
//		verColision(v.getCabeza().getPosX(), v.getCabeza().getPosY(),v);
	}

	public void inteligenciaArtificialCuadrado(Vibora v) {

		if (cont <= 2)
			v.setDireccion(2);
		else if (cont >= 3 && cont <= 5)
			v.setDireccion(3);
		else if (cont > 5 && cont <= 8)
			v.setDireccion(4);
		else if (cont > 8 && cont <= 12)
			v.setDireccion(1);
		else {
			cont = 0;
			v.setDireccion(2);
		}
		cont++;
		v.moverVibora(v.getDireccion());
	}
}
