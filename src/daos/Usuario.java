package daos;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable{

	private String username;
	private String password;
	


	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + "]";
	}
	
	
	
}