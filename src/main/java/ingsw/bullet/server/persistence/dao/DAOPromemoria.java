package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Promemoria;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAOPromemoria implements DAOInterface<Promemoria> {
	
	private DataSource dataSource;

	public DAOPromemoria(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Promemoria t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "INSERT INTO promemoria (id_tdl, id_etichetta, descrizione, completato, scadenza) VALUES (?,?,?,?,?);";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdTDL());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getDescrizione());
			statement.setBoolean(4, t.isCompletato());
			statement.setTimestamp(5, Timestamp.valueOf(t.getScadenza()));

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

	public Promemoria findByPrimaryKey(Object... keys) {
		Connection connection = null;
		Promemoria promemoria = null;

		// parser chiavi primarie...
		int id_promemoria = (Integer)keys[0];

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM promemoria WHERE promemoria.id_promemoria = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_promemoria);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				promemoria = new Promemoria();
				promemoria.setIdPromemoria(result.getInt("id_promemoria"));
				promemoria.setIdTDL(result.getInt("id_tdl"));
				promemoria.setIdEtichetta(result.getInt("id_etichetta"));
				promemoria.setDescrizione(result.getString("descrizione"));
				promemoria.setCompletato(result.getBoolean("completato"));
				promemoria.setScadenza((result.getTimestamp("scadenza")).toLocalDateTime());

				promemoria.setPartecipanti(new DAOPartecipantePromemoria(dataSource)
						.findBypromemoria(id_promemoria));
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
		return promemoria;
	}

	public List<Promemoria> findByTDL(int id_tdl) {
		Connection connection = null;
		Promemoria promemoria = null;
		List<Promemoria> pr = new ArrayList<Promemoria>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM promemoria WHERE promemoria.id_tdl = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_tdl);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				promemoria = new Promemoria();
				promemoria.setIdPromemoria(result.getInt("id_promemoria"));
				promemoria.setIdTDL(result.getInt("id_tdl"));
				promemoria.setIdEtichetta(result.getInt("id_etichetta"));
				promemoria.setDescrizione(result.getString("descrizione"));
				promemoria.setCompletato(result.getBoolean("completato"));
				promemoria.setScadenza((result.getTimestamp("scadenza")).toLocalDateTime());

				promemoria.setPartecipanti(new DAOPartecipantePromemoria(dataSource)
						.findBypromemoria(promemoria.getIdPromemoria()));

				pr.add(promemoria);

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
		return pr;
	}

	public List<Promemoria> findByEtichetta(int id_etichetta) {
		Connection connection = null;
		Promemoria promemoria = null;
		List<Promemoria> pr = new ArrayList<Promemoria>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM promemoria WHERE promemoria.etichetta = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id_etichetta);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				promemoria = new Promemoria();
				promemoria.setIdPromemoria(result.getInt("id_promemoria"));
				promemoria.setIdTDL(result.getInt("id_tdl"));
				promemoria.setIdEtichetta(result.getInt("id_etichetta"));
				promemoria.setDescrizione(result.getString("descrizione"));
				promemoria.setCompletato(result.getBoolean("completato"));
				promemoria.setScadenza((result.getTimestamp("scadenza")).toLocalDateTime());

				promemoria.setPartecipanti(new DAOPartecipantePromemoria(dataSource)
						.findBypromemoria(promemoria.getIdPromemoria()));

				pr.add(promemoria);

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
		return pr;
	}

	public List<Promemoria> findAll() {
		Connection connection = null;
		Promemoria promemoria = null;
		List<Promemoria> pr = new ArrayList<Promemoria>();

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM promemoria";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				promemoria = new Promemoria();
				promemoria.setIdPromemoria(result.getInt("id_promemoria"));
				promemoria.setIdTDL(result.getInt("id_tdl"));
				promemoria.setIdEtichetta(result.getInt("id_etichetta"));
				promemoria.setDescrizione(result.getString("descrizione"));
				promemoria.setCompletato(result.getBoolean("completato"));
				promemoria.setScadenza((result.getTimestamp("scadenza")).toLocalDateTime());
				pr.add(promemoria);

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
		return pr;
	}

	public void update(Promemoria t) {
		Connection connection = null;

		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "UPDATE promemoria SET" +
					"promemoria.id_tdl = ?, " +
					"promemoria.id_etichetta = ?, " +
					"promemoria.descrizione = ?, " +
					"promemoria.completato = ?, " +
					"promemoria.scadenza = ? " +
					"WHERE promemoria.id_promemoria = ?";
			statement = connection.prepareStatement(query);

			statement.setInt(1, t.getIdTDL());
			statement.setInt(2, t.getIdEtichetta());
			statement.setString(3, t.getDescrizione());
			statement.setBoolean(4, t.isCompletato());
			statement.setTimestamp(5, Timestamp.valueOf(t.getScadenza()));

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

	public void delete(Promemoria t) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM promemoria WHERE promemoria.id_promemoria = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, t.getIdPromemoria());
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
