package gameObject;

public class Cabeza {
	
	private int posX;
	private int posY;
	private double velocidad;
	
	public Cabeza(double velocidad, int xIni, int yIni) {
		this.velocidad = velocidad;
		this.posX = xIni * Arena.TAM_GRAFICOS;
		this.posY = yIni * Arena.TAM_GRAFICOS;
	}

	public void movCabeza(int dir) {
		
		if(dir == 1)
			this.posY -= Arena.TAM_GRAFICOS*velocidad;
		else if(dir == 2)
			this.posX += Arena.TAM_GRAFICOS*velocidad;
		else if(dir == 3)
			this.posY += Arena.TAM_GRAFICOS*velocidad;
		else if(dir == 4)
			this.posX -= Arena.TAM_GRAFICOS*velocidad;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}	
	
	
}
