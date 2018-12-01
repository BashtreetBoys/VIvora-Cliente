package mensajeria;

import java.io.Serializable;

import gameObject.Vibora;

/**
 * Estamos un poco lejos de implementar algo de esto pero ya lo tenemos que tener al corriente. Aca lo que vamos a hacer es mandar los datos actualizados de la arena.
 * Despues lo que vamos a hacer es que el server vea las colisiones y mande la arena actualizada para pintar.
 * Esta clase puede cambiar bastante en la brevedad.
 * 
 * @author Julian
 *
 */
public class MsjeXeYNuevo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5290282739808056096L;
	public Vibora miVibora;
	public int posXNueva;
	public int posYNueva;
	
	public MsjeXeYNuevo(Vibora v) {
		this.miVibora = v;
		this.posXNueva = v.getCabeza().getPosX();
		this.posYNueva = v.getCabeza().getPosY();
	}
}
