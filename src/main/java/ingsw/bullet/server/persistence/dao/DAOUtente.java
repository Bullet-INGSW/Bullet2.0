package ingsw.bullet.server.persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ingsw.bullet.model.Utente;
import ingsw.bullet.model.Utente.Sesso;
import ingsw.bullet.server.persistence.DataSource;

public class DAOUtente implements DAOInterface<Utente> {

	private DataSource dataSource;

	public DAOUtente(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Utente t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO utente (email, password, nome, cognome, sesso) VALUES (?,?,?,?,?);";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, t.getEmail());
			statement.setString(2, t.getPassword());
			statement.setString(3, t.getNome());
			statement.setString(4, t.getCognome());
			statement.setString(5, t.getSesso().name());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			while (resultSet.next())
			{
				t.setEmail(resultSet.getString(1));
				t.setPassword(resultSet.getString(2));
				t.setNome(resultSet.getString(3));
				t.setCognome(resultSet.getString(4));
				t.setSesso(Sesso.valueOf(resultSet.getString(5)));
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
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setSesso(Sesso.valueOf(result.getString("sesso")));

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
		Connection connection = null;
		List<Utente> utenti = new ArrayList<Utente>();

		try {
			connection = this.dataSource.getConnection();
			Utente utente;
			PreparedStatement statement;
			String query = "SELECT * FROM utente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setSesso(Sesso.valueOf(result.getString("sesso")));
				utenti.add(utente);
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
		return utenti;
	}

	public void update(Utente t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE utente SET " +
					"utente.password = ?, " +
					"utente.nome = ?, " +
					"utente.cognome = ?, " +
					"utente.sesso = ? " +
					"WHERE utente.email = ?";
			statement = connection.prepareStatement(query);

			statement.setString(1, t.getPassword());
			statement.setString(2, t.getNome());
			statement.setString(3, t.getCognome());
			statement.setString(4, t.getSesso().name());
			statement.setString(5, t.getEmail());

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

	public void delete(Utente t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM utente WHERE utente.email = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, t.getEmail());
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
