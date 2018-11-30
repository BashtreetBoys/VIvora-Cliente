package mensajeria;

public class MensajeUsuario {

	String user;
	String pass;
	private boolean quieroRegistrarme;	//Boolean que indica si el usuario quiere registrarse.
	
	public MensajeUsuario(String user, String pass, boolean registro) {
		this.user = user;
		this.pass = pass;
		this.quieroRegistrarme = registro;
	}
}
