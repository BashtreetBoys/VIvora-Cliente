package mensajeria;
import java.io.Serializable;

import daos.Usuario;

public class MensajeUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1009920608755357108L;
	private Usuario usuario;			//Info del usuario
	private boolean quieroRegistrarme;	//Boolean que indica si el usuario quiere registrarse.
	
	public MensajeUsuario(Usuario u, boolean registro) {
		this.usuario = u;
		this.quieroRegistrarme = registro;
	}
}
