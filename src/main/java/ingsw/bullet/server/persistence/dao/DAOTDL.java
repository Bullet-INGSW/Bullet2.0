package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.TDL;
import ingsw.bullet.server.persistence.DataSource;
import ingsw.bullet.server.persistence.dao.DAOInterface;

import java.util.List;

public class DAOTDL implements DAOInterface<TDL> {
	
	private DataSource dataSource;

	public DAOTDL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(TDL t) {
		// TODO Auto-generated method stub
		
	}

	public TDL findByPrimaryKey(Object... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TDL> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(TDL t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(TDL t) {
		// TODO Auto-generated method stub
		
	}
    
}
