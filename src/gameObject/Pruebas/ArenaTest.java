package gameObject.Pruebas;
import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;
import gameObject.Arena;
import gameObject.Cabeza;
import gameObject.Cuerpo;
import gameObject.Fruta;
import gameObject.Vibora;
import gameObject.Obstaculo;
public class ArenaTest {
	
	
	
	@Test
	public void AgregoVibora() {
	Vibora nueva = new Vibora (3,3);
	Vibora nueva2 = new Vibora(4,9);
	Vibora nueva3 = new Vibora(10,11);
	
	Arena bg = new Arena ();
	bg.setLv(1);

	bg.agregarVibora(nueva);
	Assert.assertEquals(1, bg.getViboras().size());
	bg.agregarVibora(nueva2);
	bg.agregarVibora(nueva3);
	Assert.assertEquals(3, bg.getViboras().size());
	
	}
	
	@Test
	public void cambioDeNivel() {
		Arena arenita = new Arena();
		ArrayList<Obstaculo> obs = new ArrayList<Obstaculo>();
		
		obs.clear();
		obs.add(new Obstaculo(0, 0, 20, 700));
		obs.add(new Obstaculo(20, 0, 1200, 20));
		obs.add(new Obstaculo(860, 20, 880, 700));
		obs.add(new Obstaculo(20, 680, 1200, 700));
		
		arenita.setLv(1);
		arenita.cambiarNivel();
		
		
		Assert.assertEquals(1, arenita.getLv());
		
		int i = 0;
		for(Obstaculo ob : obs) {
	//		Assert.assertEquals(ob.getPosXfin(),arenita.getObstaculos().get(i).getPosXfin(),0.0);
			Assert.assertEquals(ob.getPosYfin(),arenita.getObstaculos().get(i).getPosYfin(),0.0);
			Assert.assertEquals(ob.getPosXini(),arenita.getObstaculos().get(i).getPosXini(),0.0);
			Assert.assertEquals(ob.getPosYini(),arenita.getObstaculos().get(i).getPosYini(),0.0);
			i++;
		
		}

		arenita.setLv(2);
		arenita.cambiarNivel();
		obs.clear();
		obs.add(new Obstaculo(1, 1, 100, 1));
		obs.add(new Obstaculo(100, 2, 100, 100));
		obs.add(new Obstaculo(99, 100, 1, 100));
		obs.add(new Obstaculo(1, 99, 1, 2));
		obs.add(new Obstaculo(20, 50, 80, 50));
		Assert.assertEquals(2, arenita.getLv());
		i = 0;
		for(Obstaculo ob : obs) {
			Assert.assertEquals(ob.getPosXfin(),arenita.getObstaculos().get(i).getPosXfin(),0.0);
			Assert.assertEquals(ob.getPosYfin(),arenita.getObstaculos().get(i).getPosYfin(),0.0);
			Assert.assertEquals(ob.getPosXini(),arenita.getObstaculos().get(i).getPosXini(),0.0);
			Assert.assertEquals(ob.getPosYini(),arenita.getObstaculos().get(i).getPosYini(),0.0);
			i++;
		
		}
		
	}
	
	@Test
	
	public void testVerColisionFruta() {
		Arena arenita =  new Arena();
		ArrayList<Obstaculo> obs = new ArrayList<Obstaculo>();
		ArrayList<Vibora> vibs = new ArrayList<Vibora>();
		Vibora vib = new Vibora(3,2);

		Cabeza cabecita = new Cabeza(20, 10, 10);
		cabecita.setPosX(3);
		cabecita.setPosY(2);
		
		ArrayList<Cuerpo> cuerpito = new ArrayList<Cuerpo>();
		cuerpito.add(new Cuerpo(cabecita.getPosX(),cabecita.getPosY()-1));
		cuerpito.add(new Cuerpo(cabecita.getPosX(),cabecita.getPosY()-2));
		
		Fruta frutita = new Fruta("normal",0,0,2);
	   arenita.agregarFruta(frutita);
		
		//Testeo la colision de una vibora con una fruta
		//System.out.println(arenita.verColision(0,0));
		Assert.assertEquals("Fruta",arenita.verColision(0, 0,null));
	}
	
	@Test
	public void testVerColisionObstaculo(){
		Arena arenita =  new Arena();
		Vibora vib = new Vibora(2,2);
		arenita.agregarVibora(vib);
		arenita.setLv(1);
		arenita.cambiarNivel();
		
		//v.setVibora(80, 80, 2); vib esta en 80 x 80 y 2 direccion
		
		Assert.assertEquals("Obstaculo",arenita.verColision(1140, 20,null));
		
	}
	
	@Test
	public void testVerColisionCabezaVibora(){
		Arena arenita = new Arena();
		Vibora vib = new Vibora(2,2);
		Vibora vib2 = new Vibora(2,2);
		arenita.agregarVibora(vib);
		arenita.agregarVibora(vib2);
		arenita.getViboras().get(0).setVibora(2, 2, 1);
		arenita.getViboras().get(1).setVibora(2, 2, 1);
		//Posicion cabeza vibora 1
		Assert.assertEquals(2, vib.getCabeza().getPosX());
		//Posicion cabeza vibora 2
		Assert.assertEquals(2, vib2.getCabeza().getPosX());
		
		//Veo si chocaron
		Assert.assertEquals("Vibora",arenita.verColision(2,2,null));
	}
	/* Hay que corregir el encuentro con los cuerpitos
	@Test
	public void testVerColisionCuerpitoVibora(){
		Arena arenita = new Arena();
		Vibora vib = new Vibora(4,4);
		Vibora vib2 = new Vibora(4,2);
		arenita.agregarVibora(vib);
		arenita.agregarVibora(vib2);
		arenita.getViboras().get(0).setVibora(4,4,2);
		arenita.getViboras().get(1).setVibora(4, 3, 1);
		//Posicion cabeza vibora 1
		Assert.assertEquals(4, vib.getCabeza().getPosX());
		//Posicion cabeza vibora 2
		Assert.assertEquals(4, vib2.getCabeza().getPosX());
		
		//Veo si chocaron
		Assert.assertEquals("Vibora",arenita.verColision(2,2));
	}*/
	
}
