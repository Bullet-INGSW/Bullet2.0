package ingsw.bullet.server.persistence.dao;

import java.util.List;

public interface DAOInterface<T> {
	public void save(T t); // Create

	// la funzione accetta parametri variabili...
	public T findByPrimaryKey(Object ...keys); // Retrieve

	public List<T> findAll();

	public void update(T t); // Update

	public void delete(T t); // Delete

}