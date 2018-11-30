package gameObject;

public interface Estado {
	/**
	 * Según el estado de la vibora, el método aplicará el Power Up correspondiente.
	 * @param vibora Vibora a la cual se le aplicará el Power Up.
	 */
	public void cambiarEstado(Vibora vibora);
}
