package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.model.Calendario;
import ingsw.bullet.model.Etichetta;
import ingsw.bullet.model.Evento;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.*;
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
			String query = "INSERT INTO calendario (id_gruppo, email, nome) VALUES (?,?,?);";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, t.getIdGruppo());
			statement.setString(2, t.getEmail());
			statement.setString(3, t.getNome());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			while (resultSet.next())
			{
				t.setIdCalendario(resultSet.getInt(1));
				t.setEmail(resultSet.getString(2));
				t.setNome(resultSet.getString(3));
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

	public Calendario findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Calendario calendario = null;

		// parser chiavi primarie...
		int id_calendario = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM calendario WHERE calendario.id_calendario = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_calendario);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				calendario = new Calendario();
				calendario.setIdCalendario(result.getInt("id_calendario"));
				calendario.setIdGruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setNome(result.getString("nome"));
				setEventiAndEtichette(calendario);

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

	public List<Calendario> findByUtente(String email) {
		Connection connection = null;
		List<Calendario> calendari = new ArrayList<Calendario>();

		try {
			connection = this.dataSource.getConnection();
			Calendario calendario;
			PreparedStatement statement;
			String query = "SELECT * FROM calendario WHERE calendario.email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				calendario = new Calendario();
				calendario.setIdCalendario(result.getInt("id_calendario"));
				calendario.setIdGruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setNome(result.getString("nome"));
				calendari.add(calendario);
				setEventiAndEtichette(calendario);
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

	public List<Calendario> findByGruppo(int id_gruppo) {
		Connection connection = null;
		List<Calendario> calendari = new ArrayList<Calendario>();

		try {
			connection = this.dataSource.getConnection();
			Calendario calendario;
			PreparedStatement statement;
			String query = "SELECT * FROM calendario WHERE calendario.id_gruppo = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_gruppo);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				calendario = new Calendario();
				calendario.setIdCalendario(result.getInt("id_calendario"));
				calendario.setIdGruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setNome(result.getString("nome"));
				calendari.add(calendario);
				setEventiAndEtichette(calendario);
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
				calendario.setIdCalendario(result.getInt("id_calendario"));
				calendario.setIdGruppo(result.getInt("id_gruppo"));
				calendario.setEmail(result.getString("email"));
				calendario.setNome(result.getString("nome"));
				calendari.add(calendario);
				setEventiAndEtichette(calendario);
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
			String query = "UPDATE calendario SET " +
					"calendario.id_gruppo = ?, " +
					"calendario.email = ?, " +
					"calendario.nome = ? " +
					"WHERE calendario.id_calendario = ? ";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdGruppo());
			statement.setString(2, t.getEmail());
			statement.setString(3, t.getNome());
			statement.setInt(4, t.getIdCalendario());

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
			statement.setInt(1, t.getIdCalendario());
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

	private void setEventiAndEtichette(Calendario calendario)
	{
		DAOEtichetta daoEtichetta = null;
		List<Evento> eventi = new DAOEvento(dataSource).findByCalendario(calendario.getIdCalendario());
		List<Etichetta> etichette = new ArrayList<>();
		for(Evento e:eventi)
		{
			if(daoEtichetta == null)
				daoEtichetta = new DAOEtichetta(dataSource);
			Etichetta etichetta = daoEtichetta.findByPrimaryKey(e.getIdEtichetta());
			if(!etichette.contains(etichetta))
				etichette.add(etichetta);
		}
		calendario.setEventi(eventi);
		calendario.setEtichette(etichette);
	}
    
}
