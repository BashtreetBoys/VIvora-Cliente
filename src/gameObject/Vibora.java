package gameObject;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Vibora {

	private int velocidad;
	private Cabeza cabeza;
	private ArrayList<Cuerpo> cuerpito;
	private int direccion;
	private int ratioCrecimiento;
	private boolean viva;
	private Color colorCabeza,colorCuerpo;
	private int puntosPartidaActual;
	

	public Vibora(int xIni, int yIni) {
		this.velocidad = 1;
		this.cabeza = new Cabeza(this.velocidad, xIni, yIni);
		this.cuerpito = new ArrayList<Cuerpo>();
		this.ratioCrecimiento = 1;
		this.viva = true;
		this.puntosPartidaActual = 0;
	}

	public void setVibora(int xIni, int yIni, int direccion) {
		this.direccion = direccion;
		
		cabeza.setPosX(xIni * Arena.TAM_GRAFICOS);
		cabeza.setPosY(yIni * Arena.TAM_GRAFICOS);

		if (this.direccion == 1) {
			System.out.println("Entre a la direccion 1");
			cuerpito.add(new Cuerpo(cabeza.getPosX(), cabeza.getPosY() + Arena.TAM_GRAFICOS));
			cuerpito.add(new Cuerpo(cabeza.getPosX(), cabeza.getPosY() + 2 * Arena.TAM_GRAFICOS));
			
		} else if (this.direccion == 2) {
			System.out.println("Entre a la direccion 2");

			cuerpito.add(new Cuerpo(cabeza.getPosX() - Arena.TAM_GRAFICOS, cabeza.getPosY()));
			cuerpito.add(new Cuerpo(cabeza.getPosX() - 2 * Arena.TAM_GRAFICOS, cabeza.getPosY()));
			

		} else if (this.direccion == 3) {

			cuerpito.add(new Cuerpo(cabeza.getPosX(), cabeza.getPosY() - Arena.TAM_GRAFICOS));

			cuerpito.add(new Cuerpo(cabeza.getPosX(), cabeza.getPosY() - 2 * Arena.TAM_GRAFICOS));

		} else if (this.direccion == 4) {

			cuerpito.add(new Cuerpo(cabeza.getPosX() + Arena.TAM_GRAFICOS, cabeza.getPosY()));

			cuerpito.add(new Cuerpo(cabeza.getPosX() + 2 * Arena.TAM_GRAFICOS, cabeza.getPosY()));

		}
	}

	public void moverVibora(int keyCode) {
		if (viva) {
			if (keyCode == KeyEvent.VK_UP && direccion != 3)
				this.direccion = 1;
			if (keyCode == KeyEvent.VK_RIGHT && direccion != 4)
				this.direccion = 2;
			if (keyCode == KeyEvent.VK_DOWN && direccion != 1)
				this.direccion = 3;
			if (keyCode == KeyEvent.VK_LEFT && direccion != 2)
				this.direccion = 4;

			
			int xCabeza = cabeza.getPosX();
			int yCabeza = cabeza.getPosY();
			this.cabeza.movCabeza(this.direccion);
			
			int xCuerpo = cuerpito.get(0).getPosX();
			int yCuerpo = cuerpito.get(0).getPosY();
			cuerpito.get(0).movCuerpo(xCabeza, yCabeza);
			
			int i = 1;
			while (i < cuerpito.size()) {

				Cuerpo c1 = new Cuerpo(xCuerpo, yCuerpo);
				xCuerpo = cuerpito.get(i).getPosX();
				yCuerpo = cuerpito.get(i).getPosY();
				cuerpito.get(i).movCuerpo(c1.getPosX(), c1.getPosY());

				i++;
			}
		}

	}

	public void crecer() {
		for (int i = 0; i < this.ratioCrecimiento; i++) {

			Cuerpo anteultimo = this.cuerpito.get(this.cuerpito.size() - 2); // Acomodar respecto a la velocidad
			Cuerpo ultimo = this.cuerpito.get(this.cuerpito.size() - 1);

			if (((this.cuerpito.get(this.cuerpito.size() - 2).getPosY() == this.cuerpito.get(this.cuerpito.size() - 1)
					.getPosY()))
					&& this.cuerpito.get(this.cuerpito.size() - 2).getPosX() > this.cuerpito
							.get(this.cuerpito.size() - 1).getPosX()) {
				this.cuerpito.add(new Cuerpo(ultimo.getPosX() - Arena.TAM_GRAFICOS, ultimo.getPosY()));
			}

			else if ((anteultimo.getPosY() == ultimo.getPosY()) && anteultimo.getPosX() < ultimo.getPosX()) {
				this.cuerpito.add(new Cuerpo(ultimo.getPosX() + Arena.TAM_GRAFICOS, ultimo.getPosY()));
			}

			else if ((anteultimo.getPosX() == ultimo.getPosX()) && anteultimo.getPosY() < ultimo.getPosY()) {
				this.cuerpito.add(new Cuerpo(ultimo.getPosX(), ultimo.getPosY() + Arena.TAM_GRAFICOS));
			}

			else if ((anteultimo.getPosX() == ultimo.getPosX()) && anteultimo.getPosY() > ultimo.getPosY()) {
				this.cuerpito.add(new Cuerpo(ultimo.getPosX(), ultimo.getPosY() - Arena.TAM_GRAFICOS));
			}
		}
	}

	public int getDireccion() {
		return direccion;
	}

	public void removerCuerpo() {
		this.cuerpito.remove(this.cuerpito.size() - 1);
	}

	void setRatioCrecimiento(int rand) {
		this.ratioCrecimiento = rand;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isViva() {
		return this.viva;
	}

	public Cabeza getCabeza() {
		return cabeza;
	}

	public void setCabeza(Cabeza cabeza) {
		this.cabeza = cabeza;
	}

	public ArrayList<Cuerpo> getCuerpito() {
		return cuerpito;
	}

	public void setCuerpito(ArrayList<Cuerpo> cuerpito) {
		this.cuerpito = cuerpito;
	}

//	public ArrayList getPosiciones() {
//		return posiciones;
//	}
//
//	public void setPosiciones(ArrayList posiciones) {
//		this.posiciones = posiciones;
//	}

	public void setViva(boolean viva) {
		this.viva = viva;
	}

	public void morir() {
		cuerpito.clear();
		cabeza.setPosX(-100);
		cabeza.setPosY(-100);
		this.setViva(false);
	}

	public void resetearCuerpo() {
		int n = this.cuerpito.size();
		// Se fija que borre todos los cuerpitos excepto los ultimos 2
		for (int i = 0; i < n; i++) {
			this.removerCuerpo();
		}

	}

	public Color getColorCuerpo() {
		return colorCuerpo;
	}

	public void setColorCuerpo(Color color) {
		this.colorCuerpo = color;
	}

	public Color getColorCabeza() {
		return colorCabeza;
	}

	public void setColorCabeza(Color color) {
		this.colorCabeza = color;
	}

	public void sumarPuntos(int deltaPuntos) {
		this.puntosPartidaActual += deltaPuntos;
	}
	
	public int getPuntosPartidaActual() {
		return puntosPartidaActual;
	}
}
