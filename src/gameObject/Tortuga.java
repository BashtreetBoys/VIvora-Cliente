package gameObject;

public class Tortuga implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setVelocidad(vibora.getVelocidad()/2);
	}

}
