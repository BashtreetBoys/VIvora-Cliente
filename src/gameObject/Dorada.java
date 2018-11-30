package gameObject;
import java.util.Random;

public class Dorada implements Estado {

	@Override
	public void cambiarEstado(Vibora vibora) {
		Random aleatorio = new Random(System.currentTimeMillis());
		int rand = aleatorio.nextInt(4) + 1; //Valor entre 2 y 3 para cambiar ratioCrecimiento.
		vibora.setRatioCrecimiento(rand);
	}

}
