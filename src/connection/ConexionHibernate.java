package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import daos.Usuario;

public class ConexionHibernate {
	ObjectInputStream entrada;
	ObjectOutputStream salida;
	SessionFactory mFctory;
	Session session;
	Socket conexionAlServer;
	public ConexionHibernate(Socket s,ObjectInputStream entrada,ObjectOutputStream salida) {
		this.entrada = entrada;
		this.salida = salida;
		try {
			this.conexionAlServer = s;
			mFctory = new Configuration().configure().buildSessionFactory();
			session = mFctory.openSession();
		} catch (Throwable ex) {
			System.err.println("No se pudo inicializar la session factory. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public ConexionHibernate() {
		try {
			mFctory = new Configuration().configure().buildSessionFactory();
			session = mFctory.openSession();
		} catch (Throwable ex) {
			System.err.println("No se pudo inicializar la session factory. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public void insertar() {
		Usuario nuevo = new Usuario();
		nuevo.setUsername("ddddd");
		nuevo.setPassword("taloko");
		Transaction tx = session.beginTransaction();
		try {

			session.saveOrUpdate(nuevo);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}

	@SuppressWarnings("unchecked")
	public void seleccionar() {
		Query q = session.createQuery("Select u from Usuario u");
		List<Usuario> nuevo = q.getResultList();
		for (Usuario u : nuevo) {
			System.out.println(u);
		}
	}

	/**
	 * 
	 * @param usuario:
	 *            le pasan el username que inserto en el login
	 * @param pass
	 *            le pasan la pass que inserto en el login
	 * @return true si existe el usuario con esa pass y se puede loggear, false
	 *         si no
	 * @throws ClassNotFoundException 
	 */
	public boolean verSiExiste(String usuario, String pass)  {
		boolean resultado = false;
		Usuario nue = new Usuario();
		nue.setUsername(usuario);
		nue.setPassword(pass);
		try {

			salida.writeObject(nue);
			resultado = (boolean) entrada.readObject();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultado;
		/*try {
			Query q = session.createQuery(
					"Select u from Usuario u Where u.username = '" + usuario + "' and u.password = '" + pass + "'");
			nue = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No encontrado");
			return false;
		}
		if (nue.getUsername().equals(usuario) && nue.getPassword().equals(pass)) {
			return true;
		}*/
	}

	/**
	 * 
	 * @param user
	 *            le pasan el nombre de usuario a registrar
	 * @param pass
	 *            le pasan la pass a registrar
	 * @return true si ingreso con exito, false si no
	 */
	public boolean registrarUsuario(String user, String pass) {
		Usuario nuevo = new Usuario();
		nuevo.setUsername(user);
		nuevo.setPassword(pass);
		Transaction tx=session.beginTransaction();
		try {
			session.save(nuevo);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return false;
		}
		return true;
	}

}
