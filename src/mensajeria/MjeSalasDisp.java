package mensajeria;

/**
 * 
 * @author Julian
 * yo voy a recibir aca  un arrayList de esta mierda con todas las salas que tenga disponibles, es importante
 * guardar el puerto para establecer la conexion.
 * Tambien podriamos mandar la ip, aunque la sacamos por la ip del ServerPrincipal, que va a ser la misma que esta.
 */

public class MjeSalasDisp {
	public String nombreSala;
	public int nroPuertoSala;
	
	public MjeSalasDisp(String nombreSala, int nroPuertoSala) {
		super();
		this.nombreSala = nombreSala;
		this.nroPuertoSala = nroPuertoSala;
	}
	
	@Override
	public String toString() {
		return "Sala: " + this.nombreSala +" (puerto " +this.nroPuertoSala +")";
	}    
	

}
