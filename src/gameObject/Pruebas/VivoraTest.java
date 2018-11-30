package gameObject.Pruebas;

import gameObject.Vibora;
import gameObject.Cuerpo;
import gameObject.Cabeza;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.junit.Assert;

public class VivoraTest {
	public static final int TAM_GRAFICOS = 20;

	// @Test
	public void crearViboras() {
		Vibora vib = new Vibora(2, 2);

		Cabeza cabecita = new Cabeza(20, 1, 1);
		cabecita.setPosX(2);
		cabecita.setPosY(2);

		ArrayList<Cuerpo> cuerpito = new ArrayList<Cuerpo>();
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() + TAM_GRAFICOS));
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() + (2 * TAM_GRAFICOS)));

		vib.setVibora(2, 2, 1);
		// Ver si puso bien la velocidad inicial de la vibora
		Assert.assertEquals(1, vib.getVelocidad());

		// Ver si creo a la vibora viva
		Assert.assertEquals(true, vib.isViva());

		// Ver si instancio bien la cabeza de la vibora
		Assert.assertEquals(cabecita.getPosX(), vib.getCabeza().getPosX(), 0.0);
		Assert.assertEquals(cabecita.getPosY(), vib.getCabeza().getPosY(), 0.0);

		// Ver si puso bien los cuerpitos iniciales segun la direccion de la
		// vibora
		Assert.assertEquals(cuerpito.get(0).getPosX(), vib.getCuerpito().get(0).getPosX());
		Assert.assertEquals(cuerpito.get(1).getPosX(), vib.getCuerpito().get(1).getPosX());
		Assert.assertEquals(cuerpito.get(0).getPosY(), vib.getCuerpito().get(0).getPosY());
		Assert.assertEquals(cuerpito.get(1).getPosY(), vib.getCuerpito().get(1).getPosY());
	}

	// @Test
	public void achicarVibora() {
		Vibora vib = new Vibora(1, 1);

		Cabeza cabecita = new Cabeza(20, 1, 1);
		cabecita.setPosX(1);
		cabecita.setPosY(1);

		ArrayList<Cuerpo> cuerpito = new ArrayList<Cuerpo>();
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 1));
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 2));

		vib.setVibora(1, 1, 1);

		// Ver tamaño inicial de la vibora
		Assert.assertEquals(2, vib.getCuerpito().size());
		// Agrando a la vibora en un cuerpito
		vib.crecer();
		Assert.assertEquals(3, vib.getCuerpito().size());
	}

	// @Test
	public void resetarVibora() {
		Vibora vib = new Vibora(1, 1);

		Cabeza cabecita = new Cabeza(20, 1, 1);
		cabecita.setPosX(1);
		cabecita.setPosY(1);

		ArrayList<Cuerpo> cuerpito = new ArrayList<Cuerpo>();
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 1));
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 2));

		vib.setVibora(1, 1, 1);
		vib.crecer();
		vib.crecer();
		// Ver tamaño inicial de la vibora
		Assert.assertEquals(4, vib.getCuerpito().size());

		// Ver tamaño luego de resetear vibora
		vib.resetearCuerpo();
		Assert.assertEquals(2, vib.getCuerpito().size());
	}

	// @Test
	public void matarVibora() {
		Vibora vib = new Vibora(1, 1);

		Cabeza cabecita = new Cabeza(20, 1, 1);
		cabecita.setPosX(1);
		cabecita.setPosY(1);

		ArrayList<Cuerpo> cuerpito = new ArrayList<Cuerpo>();
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 1));
		cuerpito.add(new Cuerpo(cabecita.getPosX(), cabecita.getPosY() - 2));

		vib.setVibora(1, 1, 1);
		vib.crecer();
		vib.crecer();

		// Ver si esta la vibora viva
		Assert.assertEquals(true, vib.isViva());
		vib.morir();
		// Ver si murio la vibora
		Assert.assertEquals(false, vib.isViva());
	}

	@Test
	public void verMovimientoiVibora() {
		Vibora vib = new Vibora(1, 1);
		vib.setVibora(4, 4, 2);
		vib.crecer();
		vib.crecer();

		System.out.println("X cabeza: " + vib.getCabeza().getPosX() + "\tY cabeza: " + vib.getCabeza().getPosY());

		ArrayList<Cuerpo> cuerpo = vib.getCuerpito();
		for (int i = 0; i < vib.getCuerpito().size(); i++) {
			System.out.println("X cuerpo " + (i + 1) + ": " + cuerpo.get(i).getPosX() + "\tY cuerpo " + (i + 1) + ": "
					+ cuerpo.get(i).getPosY());
		}

		vib.moverVibora(KeyEvent.VK_DOWN);

		System.out.println("\nMOVEMOS LA VIBORA PARA ABAJO\n");

		System.out.println("X cabeza: " + vib.getCabeza().getPosX() + "\tY cabeza: " + vib.getCabeza().getPosY());

		cuerpo = vib.getCuerpito();
		for (int i = 0; i < vib.getCuerpito().size(); i++) {
			System.out.println("X cuerpo " + (i + 1) + ": " + cuerpo.get(i).getPosX() + "\tY cuerpo " + (i + 1) + ": "
					+ cuerpo.get(i).getPosY());
		}

		vib.moverVibora(KeyEvent.VK_RIGHT);

		System.out.println("\nMOVEMOS LA VIBORA PARA LA DERECHA\n");

		System.out.println("X cabeza: " + vib.getCabeza().getPosX() + "\tY cabeza: " + vib.getCabeza().getPosY());

		cuerpo = vib.getCuerpito();
		for (int i = 0; i < vib.getCuerpito().size(); i++) {
			System.out.println("X cuerpo " + (i + 1) + ": " + cuerpo.get(i).getPosX() + "\tY cuerpo " + (i + 1) + ": "
					+ cuerpo.get(i).getPosY());
		}
	}

}
