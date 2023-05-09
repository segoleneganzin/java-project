package dao;

import java.util.HashMap;

/**
 * Patron de conception DAO
 *
 * @param <T> avec type generique T (comme pour ArrayList<T>)
 */
public abstract class DAO<T> {
	
	protected final HashMap<Integer, T> donnees = new HashMap<Integer, T>();
		
	/**
	 * Methode de creation d'un objet de type "T",
	 * peut etre amene a injecter l'id cree dans le programme
	 * @param obj
	 * @return boolean 
	 */
	public abstract boolean create(T obj);

	/**
	 * Methode pour effacer selon l'id de l'objet
	 * @param obj
	 * @return boolean 
	 */
	public abstract boolean delete(T obj);

	/**
	 * Methode de mise a jour selon l'id de l'objet
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Methode de recherche des informations qui retourne un objet T
	 * @param id
	 * @return T
	 */
	public abstract T read(int id);
		
}
