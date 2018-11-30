package gameObject;

public class Fruta {

	private String powerUp;
	private int posX;
	private int posY;
	private int numeroFruta;
	
	public Fruta(String powerUp, int posX, int posY,int numeroFruta) {
		this.powerUp = powerUp;
		this.posX = posX;
		this.posY = posY;
		this.numeroFruta = numeroFruta;
	}
	
	public Fruta() {}	//No borrar este metodo, se utiliza
						//para crear un fruta vacia en Arena

	public String getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(String powerUp) {
		this.powerUp = powerUp;
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

	public int getNumeroFruta() {
		return numeroFruta;
	}

	public void setNumeroFruta(int numeroFruta) {
		this.numeroFruta = numeroFruta;
	}
	
	
}
