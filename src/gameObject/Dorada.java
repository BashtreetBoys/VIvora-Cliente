package gameObject;
import java.util.Random;

public class Dorada implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		vibora.setEstado(1);
		vibora.setRatioCrecimiento(2);
	}

}
