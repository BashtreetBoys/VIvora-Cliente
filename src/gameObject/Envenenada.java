package gameObject;

public class Envenenada implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setEstado(2);
		vibora.removerCuerpo();
	}

}
