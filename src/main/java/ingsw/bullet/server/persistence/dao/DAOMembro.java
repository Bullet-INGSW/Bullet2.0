package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.model.Membro;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMembro implements DAOInterface<Membro> {
	
	private DataSource dataSource;

	public DAOMembro(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Membro t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO membro (email, id_gruppo, admin) VALUES (?,?,?);";
			statement = connection.prepareStatement(query);
			
			statement.setString(1, t.getEmail());
			statement.setInt(2, t.getIdGruppo());
			statement.setBoolean(3, t.isAdmin());
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

	public Membro findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Membro membro = null;

		// parser chiavi primarie...
		String email = (String)keys[0];
		int id_gruppo = (Integer)keys[1];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM membro WHERE membro.email = ? AND membro.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setInt(2, id_gruppo);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				membro = new Membro();
				membro.setEmail(result.getString("email"));
				membro.setIdGruppo(result.getInt("id_gruppo"));
				membro.setAdmin(result.getBoolean("admin"));

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
		return membro;
	}

	public List<Membro> findByUtente(String email) {
		Connection connection = null;
		Membro membro = null;
		List<Membro> membri = new ArrayList<Membro>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM membro WHERE membro.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				membro = new Membro();
				membro.setEmail(result.getString("email"));
				membro.setIdGruppo(result.getInt("id_gruppo"));
				membro.setAdmin(result.getBoolean("admin"));
				membri.add(membro);

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
		return membri;
	}

	public List<Membro> findByGruppo(int id_gruppo) {
		Connection connection = null;
		Membro membro = null;
		List<Membro> membri = new ArrayList<Membro>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM membro WHERE membro.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_gruppo);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				membro = new Membro();
				membro.setEmail(result.getString("email"));
				membro.setIdGruppo(result.getInt("id_gruppo"));
				membro.setAdmin(result.getBoolean("admin"));
				membri.add(membro);

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
		return membri;
	}

	public List<Membro> findAll() {
		Connection connection = null;
		Membro membro = null;
		List<Membro> membri = new ArrayList<Membro>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM membro";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				membro = new Membro();
				membro.setEmail(result.getString("email"));
				membro.setIdGruppo(result.getInt("id_gruppo"));
				membro.setAdmin(result.getBoolean("admin"));
				membri.add(membro);

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
		return membri;
	}

	public void update(Membro t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE membro SET " +
					"membro.admin = ? " +
					"WHERE membro.email = ? " +
					"AND membro.id_gruppo = ? ";
			statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			statement.setBoolean(1, t.isAdmin());
			statement.setString(2, t.getEmail());
			statement.setInt(3, t.getIdGruppo());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			String email=resultSet.getString(2);
			t = findByPrimaryKey(email,id);




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

	public void delete(Membro t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM membro WHERE membro.email = ? AND membro.id_gruppo = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, t.getEmail());
			statement.setInt(2, t.getIdGruppo());
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
