package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Membro;
import ingsw.bullet.server.persistence.DataSource;
import ingsw.bullet.server.persistence.dao.DAOInterface;

import java.util.List;

public class DAOMembro implements DAOInterface<Membro> {
	
	private DataSource dataSource;

	public DAOMembro(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Membro t) {
		// TODO Auto-generated method stub
		
	}

	public Membro findByPrimaryKey(Object... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Membro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Membro t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Membro t) {
		// TODO Auto-generated method stub
		
	}
    
}
