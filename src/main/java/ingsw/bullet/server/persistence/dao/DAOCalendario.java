package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Calendario;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCalendario implements DAOInterface<Calendario> {

	private DataSource dataSource;

	public DAOCalendario(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Calendario t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO calendario (id_gruppo, email, descrizione) VALUES (?,?,?);";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getId_gruppo());
			statement.setString(2, t.getEmail());
			statement.setString(3, t.getDescrizione());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}

	public Calendario findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Calendario calendario = null;

		// parser chiavi primarie...
		String id_calendario = (String)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM calendario WHERE calendario.id_calendario = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, id_calendario);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				calendario = new Calendario();
				calendario.setId_calendario(result.getInt("id_calendario"));
				calendario.setId_gruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setDescrizione(result.getString("descrizione"));

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
		return calendario;
	}

	public List<Calendario> findAll() {
		Connection connection = null;
		List<Calendario> calendari = new ArrayList<Calendario>();

		try {
			connection = this.dataSource.getConnection();
			Calendario calendario;
			PreparedStatement statement;
			String query = "SELECT * FROM calendario";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calendario = new Calendario();
				calendario.setId_calendario(result.getInt("id_calendario"));
				calendario.setId_gruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setDescrizione(result.getString("descrizione"));
				calendari.add(calendario);
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
		return calendari;
	}

	public void update(Calendario t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE calendario SET" +
					"calendario.id_calendario = ?, " +
					"calendario.id_gruppo = ?, " +
					"calendario.email = ?, " +
					"calendario.descrizione = ?";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getId_calendario());
			statement.setInt(2, t.getId_gruppo());
			statement.setString(3, t.getEmail());
			statement.setString(4, t.getDescrizione());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}

	public void delete(Calendario t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM calendario WHERE calendario.id_calendario = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getId_calendario());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
    
}
