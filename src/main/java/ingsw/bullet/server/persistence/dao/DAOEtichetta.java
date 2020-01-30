package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Etichetta;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String query = "INSERT INTO etichetta (colore, nome) VALUES (?,?);";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getColore());
			statement.setString(2, t.getNome());

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

			if (result.next()) {
				etichetta = new Etichetta();
				etichetta.setIdEtichetta(result.getInt("id_etichetta"));
				etichetta.setColore(result.getInt("colore"));
				etichetta.setNome(result.getString("nome"));

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
				etichetta.setColore(result.getInt("colore"));
				etichetta.setNome(result.getString("nome"));
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
					"etichetta.colore = ?, " +
					"etichetta.nome = ? " +
					"WHERE etichetta.id_etichetta = ?";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getColore());
			statement.setString(2, t.getNome());
			statement.setInt(3, t.getIdEtichetta());

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
