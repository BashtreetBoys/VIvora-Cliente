package gameObject;

public class Fruta {

	private String powerUp;
	private int posX;
	private int posY;
	private int numeroFruta;
	private int idpowerup;
	
	public Fruta(int idpowerup, int posX, int posY,int numeroFruta) {
		if(idpowerup == 0)
			this.powerUp = "normal";
		if(idpowerup == 1)
			this.powerUp = "dorada";
		if(idpowerup == 2)
			this.powerUp = "veneno";
		if(idpowerup == 3)
			this.powerUp = "flash";
		if(idpowerup == 4)
			this.powerUp = "tortuga";
		this.idpowerup = idpowerup;
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

	public int getIdpowerup() {
		return idpowerup;
	}

	public void setIdpowerup(int idpowerup) {
		this.idpowerup = idpowerup;
	}
	
	
}
