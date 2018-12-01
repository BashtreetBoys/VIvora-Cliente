package mensajeria;

import java.io.Serializable;

/**
 * 
 * @author Julian
 * por medio de este mensaje vamos a hacer la comunicacion con el server principal, que se va a encargar de cuestiones administrativas y no del juego en si
 * Por ejemplo, cuando quiero que el server me mande las salas disponibles pongo el quieroSalas en true, el resto en false y mando.
 * Despues voy a recibir un ArrayList con las salas para mostrarlas.
 * Si yo quiero crear una sala pongo en true quieroCrearSalas y escribo el nombre de la sala (por el momento el numero de puerto no, es medio inseguro que lo decida un usuario, lo hace el server automaticamente).
 * Si yo quiero desconectarme de la sala pongo el quieroDesconectarme en true y eso va a cerrar todo, re lindo no? @author JulianP - ermozo jaj
 * El quieroConectarmeASala lo deje por las dudas pero no lo vamos a usar
 */

@SuppressWarnings("serial")
public class MjeServerPrincipal implements Serializable{
	public boolean quieroSalas;
	public boolean quieroCrearSalas;
	public boolean quieroConectarmeASala;//creo que este no hace falta porque para conectarse a una sala le tira el mensaje al puerto de la sala y listo
	public boolean quieroDesconectarme;
	public int nroPuerto;
	public String nombreSala;
	

}
