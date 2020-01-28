package ingsw.bullet.server.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ingsw.bullet.server.model.Utente;
import ingsw.bullet.server.model.Utente.Sesso;
import ingsw.bullet.server.persistence.DataSource;

public class DAOUtente implements DAOInterface<Utente> {
	
	private DataSource dataSource;

	public DAOUtente(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Utente t) {
		// TODO Auto-generated method stub
		
	}

	public Utente findByPrimaryKey(Object ...keys) {
		Connection connection = null;
		Utente utente = null;
		
		// parser chiavi primarie...
		String email = (String)keys[0];
		
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM utente WHERE utente.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setSesso(Sesso.valueOf(result.getString("sesso")));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return utente;
	}

	public List<Utente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Utente t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Utente t) {
		// TODO Auto-generated method stub
		
	}

	

}
