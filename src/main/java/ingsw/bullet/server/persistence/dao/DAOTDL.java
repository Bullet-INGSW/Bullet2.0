package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Calendario;
import ingsw.bullet.server.model.TDL;
import ingsw.bullet.server.persistence.DataSource;
import ingsw.bullet.server.persistence.dao.DAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTDL implements DAOInterface<TDL> {
	
	private DataSource dataSource;

	public DAOTDL(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(TDL t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO calendario (id_gruppo, email, nome) VALUES (?,?,?);";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdGruppo());
			statement.setString(2, t.getEmail());
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

	public TDL findByPrimaryKey(Object... keys) {
		Connection connection = null;
		TDL tdl = null;

		// parser chiavi primarie...
		int id_tdl = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM tdl WHERE tdl.id_tdl = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_tdl);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				tdl = new TDL();
				tdl.setIdTDL(result.getInt("id_tdl"));
				tdl.setIdGruppo(result.getInt("id_gruppo"));
				tdl.setEmail(result.getString("email"));
				tdl.setNome(result.getString("nome"));

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
		return tdl;
	}

	public List<TDL> findByGruppo(int id_gruppo) {
		Connection connection = null;
		List<TDL> tdls = new ArrayList<TDL>();

		try {
			connection = this.dataSource.getConnection();
			TDL tdl;
			PreparedStatement statement;
			String query = "SELECT * FROM tdl WHERE tdl.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_gruppo);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				tdl = new TDL();
				tdl.setIdTDL(result.getInt("id_tdl"));
				tdl.setIdGruppo(result.getInt("id_gruppo"));
				tdl.setEmail(result.getString("email"));
				tdl.setNome(result.getString("nome"));
				tdls.add(tdl);
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
		return tdls;
	}

	public List<TDL> findByUtente(String email) {
		Connection connection = null;
		List<TDL> tdls = new ArrayList<TDL>();

		try {
			connection = this.dataSource.getConnection();
			TDL tdl;
			PreparedStatement statement;
			String query = "SELECT * FROM tdl WHERE tdl.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				tdl = new TDL();
				tdl.setIdTDL(result.getInt("id_tdl"));
				tdl.setIdGruppo(result.getInt("id_gruppo"));
				tdl.setEmail(result.getString("email"));
				tdl.setNome(result.getString("nome"));
				tdls.add(tdl);
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
		return tdls;
	}

	public List<TDL> findAll() {
		Connection connection = null;
		List<TDL> tdls = new ArrayList<TDL>();

		try {
			connection = this.dataSource.getConnection();
			TDL tdl;
			PreparedStatement statement;
			String query = "SELECT * FROM tdl";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				tdl = new TDL();
				tdl.setIdTDL(result.getInt("id_tdl"));
				tdl.setIdGruppo(result.getInt("id_gruppo"));
				tdl.setEmail(result.getString("email"));
				tdl.setNome(result.getString("nome"));
				tdls.add(tdl);
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
		return tdls;
	}

	public void update(TDL t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE tdl SET " +
					"tdl.id_gruppo = ?, " +
					"tdl.email = ?, " +
					"tdl.nome = ? " +
					"WHERE tdl.id_tdl = ? ";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdGruppo());
			statement.setString(2, t.getEmail());
			statement.setString(3, t.getNome());
			statement.setInt(4, t.getIdTDL());

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

	public void delete(TDL t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM tdl WHERE tdl.id_tdl = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdTDL());
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
