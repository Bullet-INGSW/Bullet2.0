package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.model.Etichetta;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEtichetta implements DAOInterface<Etichetta> {

	private DataSource dataSource;

	public DAOEtichetta(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Etichetta t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO etichetta (nome, colore) VALUES (?,?);";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, t.getNome());
			statement.setInt(2, t.getColore());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			while (resultSet.next())
			{
				t.setIdEtichetta(resultSet.getInt(1));
				t.setNome(resultSet.getString(2));
				t.setColore(resultSet.getInt(3));
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

	public Etichetta findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Etichetta etichetta = null;

		// parser chiavi primarie...
		int id_etichetta = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM etichetta WHERE etichetta.id_etichetta = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_etichetta);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				etichetta = new Etichetta();
				etichetta.setIdEtichetta(result.getInt("id_etichetta"));
				etichetta.setNome(result.getString("nome"));
				etichetta.setColore(result.getInt("colore"));

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
		return etichetta;
	}

	public List<Etichetta> findAll() {
		Connection connection = null;
		List<Etichetta> etichette = new ArrayList<Etichetta>();

		try {
			connection = this.dataSource.getConnection();
			Etichetta etichetta;
			PreparedStatement statement;
			String query = "SELECT * FROM etichetta";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				etichetta = new Etichetta();
				etichetta.setIdEtichetta(result.getInt("id_etichetta"));
				etichetta.setNome(result.getString("nome"));
				etichetta.setColore(result.getInt("colore"));
				etichette.add(etichetta);
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
		return etichette;
	}

	public void update(Etichetta t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE etichetta SET " +
					"etichetta.nome = ?, " +
					"etichetta.colore = ? " +
					"WHERE etichetta.id_etichetta = ?";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getColore());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getNome());

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

	public void delete(Etichetta t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM etichetta WHERE etichetta.id_etichetta = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdEtichetta());
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
