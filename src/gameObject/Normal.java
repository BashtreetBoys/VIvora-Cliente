package gameObject;

public class Normal implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setEstado(0);
		vibora.setRatioCrecimiento(1);
	}

}
