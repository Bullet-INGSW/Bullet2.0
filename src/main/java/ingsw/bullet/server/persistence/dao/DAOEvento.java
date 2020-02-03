package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.model.Evento;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEvento implements DAOInterface<Evento> {

	private DataSource dataSource;

	public DAOEvento(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Evento t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO evento (" +
					"id_calendario, " +
					"id_etichetta, " +
					"nome, " +

					"descrizione, " +
					"data_inizio, " +
					"data_fine, " +

					"periodicita, " +
					"full_day, " +
					"id_recurrence," +

					"recurrence_rule " +

					") VALUES (?,?,?, ?,?,? ,?,?,? ,?);";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, t.getIdCalendario());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getNome());
			statement.setString(4, t.getDescrizione());
			statement.setTimestamp(5, Timestamp.valueOf(t.getDataInizio()));
			statement.setTimestamp(6, Timestamp.valueOf(t.getDataFine()));
			statement.setBoolean(7, t.getPeriodicita());
			statement.setBoolean(8, t.isFullDay());
			if(t.getIdRecurrence() == 0)
				statement.setNull(9, Types.INTEGER);
			else
				statement.setInt(9, t.getIdRecurrence());
			statement.setString(10, t.getRecurrenceRule());

			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			t.setIdEvento(id);


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

	public Evento findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Evento evento = null;

		// parser chiavi primarie...
		int id_evento = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM evento WHERE evento.id_evento = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_evento);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				evento = new Evento();
				evento.setIdEvento(result.getInt("id_evento"));
				evento.setIdCalendario(result.getInt("id_calendario"));
				evento.setIdEtichetta(result.getInt("id_etichetta"));
				evento.setNome(result.getString("nome"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));
				evento.setFullDay(result.getBoolean("full_day"));
				evento.setIdRecurrence(result.getInt("id_recurrence"));
				evento.setRecurrenceRule(result.getString("recurrence_rule"));

			}
			evento.setPartecipanti(new DAOPartecipanteEvento(dataSource).findByEvento(id_evento));

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return evento;

	}

	public List<Evento> findByCalendario(int id_calendario) {
		Connection connection = null;
		Evento evento = null;
		List<Evento> eventi = new ArrayList<Evento>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM evento WHERE evento.id_calendario = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_calendario);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				evento = new Evento();
				evento.setIdEvento(result.getInt("id_evento"));
				evento.setIdCalendario(result.getInt("id_calendario"));
				evento.setIdEtichetta(result.getInt("id_etichetta"));
				evento.setNome(result.getString("nome"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));
				evento.setFullDay(result.getBoolean("full_day"));
				evento.setIdRecurrence(result.getInt("id_recurrence"));
				evento.setRecurrenceRule(result.getString("recurrence_rule"));

				evento.setPartecipanti(new DAOPartecipanteEvento(dataSource).findByEvento(evento.getIdEvento()));

				eventi.add(evento);

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
		return eventi;
	}

	public List<Evento> findByEtichetta(int id_etichetta) {
		Connection connection = null;
		Evento evento = null;
		List<Evento> eventi = new ArrayList<Evento>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM evento WHERE evento.id_etichetta = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_etichetta);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				evento = new Evento();
				evento.setIdEvento(result.getInt("id_evento"));
				evento.setIdCalendario(result.getInt("id_calendario"));
				evento.setIdEtichetta(result.getInt("id_etichetta"));
				evento.setNome(result.getString("nome"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));
				evento.setFullDay(result.getBoolean("full_day"));
				evento.setIdRecurrence(result.getInt("id_recurrence"));
				evento.setRecurrenceRule(result.getString("recurrence_rule"));

				eventi.add(evento);

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
		return eventi;
	}

	public List<Evento> findAll() {
		Connection connection = null;
		Evento evento = null;
		List<Evento> eventi = new ArrayList<Evento>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM evento";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				evento = new Evento();
				evento.setIdEvento(result.getInt("id_evento"));
				evento.setIdCalendario(result.getInt("id_calendario"));
				evento.setIdEtichetta(result.getInt("id_etichetta"));
				evento.setNome(result.getString("nome"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));
				evento.setFullDay(result.getBoolean("full_day"));
				evento.setIdRecurrence(result.getInt("id_recurrence"));
				evento.setRecurrenceRule(result.getString("recurrence_rule"));

				eventi.add(evento);

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
		return eventi;

	}

	public void update(Evento t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE evento SET " +
					"evento.id_calendario = ?, " +
					"evento.id_etichetta = ?, " +
					"evento.descrizione = ?, " +
					"evento.nome = ?, " +
					"evento.data_inizio = ?, " +
					"evento.data_fine = ?, " +
					"evento.periodicita = ?, " +
					"evento.full_day = ?, " +
					"evento.id_recurrence = ?, " +
					"evento.recurrence_rule = ? " +
					"WHERE evento.id_evento = ?";

			statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, t.getIdCalendario());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getNome());
			statement.setString(4, t.getDescrizione());
			statement.setTimestamp(5, Timestamp.valueOf(t.getDataInizio()));
			statement.setTimestamp(6, Timestamp.valueOf(t.getDataFine()));
			statement.setBoolean(7, t.getPeriodicita());
			statement.setBoolean(8, t.isFullDay());
			if(t.getIdRecurrence() == 0)
				statement.setNull(9, Types.INTEGER);
			else
				statement.setInt(9, t.getIdRecurrence());
			statement.setString(10, t.getRecurrenceRule());
			statement.setInt(11, t.getIdEvento());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			t.setIdEvento(id);

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

	public void delete(Evento t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM evento WHERE evento.id_evento = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdEvento());
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
