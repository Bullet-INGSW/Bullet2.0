package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Etichetta;
import ingsw.bullet.server.model.Notifica;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAONotifica implements DAOInterface<Notifica> {

	private DataSource dataSource;

	public DAONotifica(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Notifica t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO notifica (email, descrizione) VALUES (?,?);";
			statement = connection.prepareStatement(query);

			statement.setString(1, t.getEmail());
			statement.setString(2, t.getDescrizione());

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

	public Notifica findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Notifica notifica = null;

		// parser chiavi primarie...
		int id_notifica = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM notifica WHERE notifica.id_notifica = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_notifica);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				notifica = new Notifica();
				notifica.setIdNotifica(result.getInt("id_notifica"));
				notifica.setEmail(result.getString("email"));
				notifica.setDescrizione(result.getString("descrizione"));

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
		return notifica;
	}

	public List<Notifica> findByUtente(String email) {
		Connection connection = null;
		Notifica notifica = null;
		List<Notifica> notifiche = new ArrayList<Notifica>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM notifica WHERE notifica.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				notifica = new Notifica();
				notifica.setIdNotifica(result.getInt("id_notifica"));
				notifica.setEmail(result.getString("email"));
				notifica.setDescrizione(result.getString("descrizione"));
				notifiche.add(notifica);

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
		return notifiche;
	}

	public List<Notifica> findAll() {
		Connection connection = null;
		Notifica notifica = null;
		List<Notifica> notifiche = new ArrayList<Notifica>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM notifica";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				notifica = new Notifica();
				notifica.setIdNotifica(result.getInt("id_notifica"));
				notifica.setEmail(result.getString("email"));
				notifica.setDescrizione(result.getString("descrizione"));
				notifiche.add(notifica);

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
		return notifiche;
	}

	public void update(Notifica t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE notifica SET " +
					"notifica.email = ?, " +
					"notifica.descrizione = ? " +
					"WHERE notifica.id_notifica = ?";
			statement = connection.prepareStatement(query);

			statement.setString(1, t.getEmail());
			statement.setString(2, t.getDescrizione());
			statement.setInt(3, t.getIdNotifica());

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

	public void delete(Notifica t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM notifica WHERE notifica.id_notifica = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdNotifica());
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
