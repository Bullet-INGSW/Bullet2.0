package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Gruppo;
import ingsw.bullet.server.persistence.DataSource;

import java.util.List;

public class DAOGruppo implements DAOInterface<Gruppo> {
	private DataSource dataSource;

	public DAOGruppo(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Gruppo t) {
		// TODO Auto-generated method stub

	}

	public Gruppo findByPrimaryKey(Object... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Gruppo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Gruppo t) {
		// TODO Auto-generated method stub

	}

	public void delete(Gruppo t) {
		// TODO Auto-generated method stub

	}

}
