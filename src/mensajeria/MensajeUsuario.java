package mensajeria;
import daos.Usuario;

public class MensajeUsuario {

	private Usuario usuario;			//Info del usuario
	private boolean quieroRegistrarme;	//Boolean que indica si el usuario quiere registrarse.
	
	public MensajeUsuario(Usuario u, boolean registro) {
		this.usuario = u;
		this.quieroRegistrarme = registro;
	}
}
