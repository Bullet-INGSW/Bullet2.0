package ingsw.bullet.server.persistence;

import ingsw.bullet.server.model.*;
import ingsw.bullet.server.persistence.dao.*;
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
			System.err.println("org.mariadb.jdbc.Driver: failed to load MariaDB JDBC driver\n" + e);
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

	// UTENTE //
	public Utente findUserByPrimaryKey(String email) {
		return getDAOUtente().findByPrimaryKey(email);
	}

	public List<Utente> getAllUsers() {
		return getDAOUtente().findAll();
	}

	public void addUtente(Utente utente) {
		getDAOUtente().save(utente);
	}

	public void deleteUtente(Utente utente) {
		getDAOUtente().delete(utente);
	}

	public DAOUtente getDAOUtente() {
		return new DAOUtente(dataSource);
	}

	// CALENDARIO //
	public Calendario findCalendarioByPrimaryKey(int id) {
		return getDAOCalendario().findByPrimaryKey(id);
	}

	public List<Calendario> getAllCalendari() {
		return getDAOCalendario().findAll();
	}

	public void addCalendario(Calendario calendario) {
		getDAOCalendario().save(calendario);
	}

	public void deleteCalendario(Calendario calendario) {
		getDAOCalendario().delete(calendario);
	}

	public DAOCalendario getDAOCalendario() {
		return new DAOCalendario(dataSource);
	}

	// EVENTO //
	public Evento findEventoByPrimaryKey(int id) {
		return getDAOEvento().findByPrimaryKey(id);
	}

	public List<Evento> getAllEventi() {
		return getDAOEvento().findAll();
	}

	public void addEvento(Evento evento) {
		getDAOEvento().save(evento);
	}

	public void deleteEvento(Evento evento) {
		getDAOEvento().delete(evento);
	}

	public DAOEvento getDAOEvento() {
		return new DAOEvento(dataSource);
    }
    
    // TDL //
	public TDL findTDLByPrimaryKey(int id) {
		return getDAOTDL().findByPrimaryKey(id);
	}

	public List<TDL> getAllTDL() {
		return getDAOTDL().findAll();
	}

	public void addTDL(TDL TDL) {
		getDAOTDL().save(TDL);
	}

	public void deleteTDL(TDL TDL) {
		getDAOTDL().delete(TDL);
	}

	public DAOTDL getDAOTDL() {
		return new DAOTDL(dataSource);
    }
    
    // PROMEMORIA //
	public Promemoria findPromemoriaByPrimaryKey(int id) {
		return getDAOPromemoria().findByPrimaryKey(id);
	}

	public List<Promemoria> getAllPromemoria() {
		return getDAOPromemoria().findAll();
	}

	public void addPromemoria(Promemoria promemoria) {
		getDAOPromemoria().save(promemoria);
	}

	public void deletePromemoria(Promemoria promemoria) {
		getDAOPromemoria().delete(promemoria);
	}

	public DAOPromemoria getDAOPromemoria() {
		return new DAOPromemoria(dataSource);
    }
    
    // ETICHETTA //
	public Etichetta findEtichettaByPrimaryKey(int id) {
		return getDAOEtichetta().findByPrimaryKey(id);
	}

	public List<Etichetta> getAllEtichetta() {
		return getDAOEtichetta().findAll();
	}

	public void addEtichetta(Etichetta etichetta) {
		getDAOEtichetta().save(etichetta);
	}

	public void deleteEtichetta(Etichetta etichetta) {
		getDAOEtichetta().delete(etichetta);
	}

	public DAOEtichetta getDAOEtichetta() {
		return new DAOEtichetta(dataSource);
    }
    
    // NOTIFICA //
	public Notifica findNotificaByPrimaryKey(int id) {
		return getDAONotifica().findByPrimaryKey(id);
	}

	public List<Notifica> getAllNotifica() {
		return getDAONotifica().findAll();
	}

	public void addNotifica(Notifica notifica) {
		getDAONotifica().save(notifica);
	}

	public void deleteNotifica(Notifica notifica) {
		getDAONotifica().delete(notifica);
	}

	public DAONotifica getDAONotifica() {
		return new DAONotifica(dataSource);
    }
    
    // GRUPPO //
	public Gruppo findGruppoByPrimaryKey(int id) {
		return getDAOGruppo().findByPrimaryKey(id);
	}

	public List<Gruppo> getAllGruppo() {
		return getDAOGruppo().findAll();
	}

	public void addGruppo(Gruppo gruppo) {
		getDAOGruppo().save(gruppo);
	}

	public void deleteGruppo(Gruppo gruppo) {
		getDAOGruppo().delete(gruppo);
	}

	public DAOGruppo getDAOGruppo() {
		return new DAOGruppo(dataSource);
    }
    
    // MEMBRO //
	public Membro findMembroByPrimaryKey(int id) {
		return getDAOMembro().findByPrimaryKey(id);
	}

	public List<Membro> getAllMembro() {
		return getDAOMembro().findAll();
	}

	public void addMembro(Membro membro) {
		getDAOMembro().save(membro);
	}

	public void deleteMembro(Membro membro) {
		getDAOMembro().delete(membro);
	}

	public DAOMembro getDAOMembro() {
		return new DAOMembro(dataSource);
	}

}
