package gameObject;

public class Envenenada implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
			vibora.removerCuerpo();
	}

}
