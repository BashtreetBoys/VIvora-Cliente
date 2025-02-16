package mensajeria;

import java.io.Serializable;

import gameObject.Arena;

/*
 * Que estamos haciendo aca? Es la respuesta del servidor, nos va a mandar la arena nueva y nos va a avisar si nuestra querida viborita se murio.
 * Lo que tenemos que hacer es levantar esto del lado del cliente y si nos morimos (teMoriste esta en true) manejar nuestra muerte.
 * Si no pintamos nuestra arena y esperamos nuevas teclas.
 * 
 */
public class MjeArenaNueva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4984306197818203273L;
	
	public Arena arenaNueva;
	public boolean teMoriste;
}
