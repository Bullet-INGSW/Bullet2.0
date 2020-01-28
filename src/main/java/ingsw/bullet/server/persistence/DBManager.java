package ingsw.bullet.server.persistence;

import ingsw.bullet.server.model.Utente;
import ingsw.bullet.server.persistence.dao.DAOUtente;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class DBManager {
	static Dotenv dotenv = Dotenv.configure().load();

	private final static String url = dotenv.get("DB_URL");
	private final static String user = dotenv.get("DB_USER");
	private final static String password = dotenv.get("DB_PASS");

	private static DataSource dataSource;

	static {
		try {
			// System.out.println("DATI ------------ " + url + " " + user + " " + password);
			Class.forName("org.mariadb.jdbc.Driver");
			dataSource = new DataSource(url, user, password);
			System.out.println("Connessione al DB ok");
		} catch (Exception e) {
			System.err.println("org.mariadb.jdbc.Driver: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	public static DBManager instance = null;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}

	// UTENTI //
	public Utente findUserByPrimaryKey(String email) {
		return getDAOUtente().findByPrimaryKey(email);
	}

	public List<Utente> getAllUsers() {
		return getDAOUtente().findAll();
	}

	public void addUser(Utente utente) {
		getDAOUtente().save(utente);
	}

	public void deleteUser(Utente utente) {
		getDAOUtente().delete(utente);
	}

	public DAOUtente getDAOUtente() {
		return new DAOUtente(dataSource);
	}

}
