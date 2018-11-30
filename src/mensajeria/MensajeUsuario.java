package mensajeria;

import java.io.Serializable;

public class MensajeUsuario implements Serializable {

	public String user;
	public String pass;
	public boolean quieroRegistrarme;	//Boolean que indica si el usuario quiere registrarse.
	
	public MensajeUsuario(String user, String pass, boolean registro) {
		this.user = user;
		this.pass = pass;
		this.quieroRegistrarme = registro;
	}
}
