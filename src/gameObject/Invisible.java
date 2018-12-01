package gameObject;

public class Invisible implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setEstado(4);
	}

}
