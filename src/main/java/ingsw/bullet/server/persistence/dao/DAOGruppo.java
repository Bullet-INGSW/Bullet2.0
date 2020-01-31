package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Etichetta;
import ingsw.bullet.server.model.Gruppo;
import ingsw.bullet.server.model.Membro;
import ingsw.bullet.server.model.Notifica;
import ingsw.bullet.server.persistence.DBManager;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOGruppo implements DAOInterface<Gruppo> {

	private DataSource dataSource;

	public DAOGruppo(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Gruppo t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO gruppo (nome) VALUES (?);";
			statement = connection.prepareStatement(query);
			statement.setString(1, t.getNome());
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

	public Gruppo findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Gruppo gruppo = null;

		// parser chiavi primarie...
		int id_gruppo = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM gruppo WHERE gruppo.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_gruppo);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				gruppo = new Gruppo();
				gruppo.setIdGruppo(result.getInt("id_gruppo"));
				gruppo.setNome(result.getString("nome"));
			}

			gruppo.setMembri(new DAOMembro(dataSource).findByGruppo(id_gruppo));
			List<Membro> amministratori = new ArrayList<Membro>();
			for(Membro m : gruppo.getMembri())
				if(m.isAdmin())
					amministratori.add(m);
			gruppo.setAmministratori(amministratori);
			gruppo.setCalendari(new DAOCalendario(dataSource).findByGruppo(id_gruppo));
			gruppo.setTDL(new DAOTDL(dataSource).findByGruppo(id_gruppo));

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return gruppo;
	}

	public List<Gruppo> findAll() {
		Connection connection = null;
		Gruppo gruppo = null;
		List<Gruppo> gruppi = new ArrayList<Gruppo>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM gruppo";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				gruppo = new Gruppo();
				gruppo.setIdGruppo(result.getInt("id_gruppo"));
				gruppo.setNome(result.getString("nome"));

				gruppo.setMembri(new DAOMembro(dataSource).findByGruppo(gruppo.getIdGruppo()));
				List<Membro> amministratori = new ArrayList<Membro>();
				for(Membro m : gruppo.getMembri())
					if(m.isAdmin())
						amministratori.add(m);
				gruppo.setAmministratori(amministratori);
				gruppo.setCalendari(new DAOCalendario(dataSource).findByGruppo(gruppo.getIdGruppo()));
				gruppo.setTDL(new DAOTDL(dataSource).findByGruppo(gruppo.getIdGruppo()));

				gruppi.add(gruppo);
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
		return gruppi;
	}

	public void update(Gruppo t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE gruppo SET " +
					"gruppo.nome = ? " +
					"WHERE gruppo.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, t.getNome());
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

	public void delete(Gruppo t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM gruppo WHERE gruppo.id_gruppo = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdGruppo());
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
