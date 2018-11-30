package gameObject;

public class Obstaculo {
	private int posXini;
	private int posXfin;
	private int posYini;
	private int posYfin;
	
	public Obstaculo(int posXini, int posYini, int posXfin, int posYfin) {
		this.posXini = posXini;
		this.posXfin = posXfin;
		this.posYini = posYini;
		this.posYfin = posYfin;
	}

	public int getPosXini() {
		return posXini;
	}

	public int getPosXfin() {
		return posXfin;
	}

	public int getPosYini() {
		return posYini;
	}

	public int getPosYfin() {
		return posYfin;
	}
}
