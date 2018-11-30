package gameObject;

public class Flash implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setVelocidad(vibora.getVelocidad()*2);
	}

}
