package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Notifica;
import ingsw.bullet.server.persistence.DataSource;

import java.util.List;

public class DAONotifica implements DAOInterface<Notifica> {
	private DataSource dataSource;

	public DAONotifica(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Notifica t) {
		// TODO Auto-generated method stub
		
	}

	public Notifica findByPrimaryKey(Object... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Notifica> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Notifica t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Notifica t) {
		// TODO Auto-generated method stub
		
	}
    
}
