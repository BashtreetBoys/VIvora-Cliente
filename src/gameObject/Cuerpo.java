package gameObject;
/**
 * 
 * @author Maximun
 *
 */
public class Cuerpo {

	private int posX;
	private int posY;
	
	public Cuerpo(int posX, int posY) {
	
		this.posX = posX;
		this.posY = posY;
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

	/**
	 * 
	 * @param x es la proxima posicion a mover en eje x  
	 * @param y es la proxima posicion a mover en eje y
	 */
	public void movCuerpo(int x,int y) {
		this.posX = x;
		this.posY = y;
	}
}
