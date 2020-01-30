package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Evento;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
			String query = "INSERT INTO evento (id_calendario, id_etichetta, descrizione, data_inizio, data_fine, periodicita) VALUES (?,?,?,?,?,?);";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdCalendario());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getDescrizione());
			statement.setTimestamp(4, Timestamp.valueOf(t.getDataInizio()));
			statement.setTimestamp(5, Timestamp.valueOf(t.getDataFine()));
			statement.setBoolean(6, t.getPeriodicita());

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
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));

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
		return evento;

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

			if (result.next()) {
				evento = new Evento();
				evento.setIdEvento(result.getInt("id_evento"));
				evento.setIdCalendario(result.getInt("id_calendario"));
				evento.setIdEtichetta(result.getInt("id_etichetta"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setDataInizio((result.getTimestamp("data_inizio")).toLocalDateTime());
				evento.setDataFine((result.getTimestamp("data_fine")).toLocalDateTime());
				evento.setPeriodicita(result.getBoolean("periodicita"));
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
			String query = "UPDATE evento SET" +
					"evento.id_calendario = ?, " +
					"evento.id_etichetta = ?, " +
					"evento.descrizione = ?, " +
					"evento.data_inizio = ?, " +
					"evento.data_fine = ?, " +
					"evento.periodicita = ? " +
					"WHERE evento.id_evento = ?";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdCalendario());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getDescrizione());
			statement.setTimestamp(4, Timestamp.valueOf(t.getDataInizio()));
			statement.setTimestamp(5, Timestamp.valueOf(t.getDataFine()));
			statement.setBoolean(6, t.getPeriodicita());
			statement.setInt(7, t.getIdEvento());

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
