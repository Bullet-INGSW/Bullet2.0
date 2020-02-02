package ingsw.bullet.server.persistence;

import ingsw.bullet.model.*;
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
	public Utente findUtenteByPrimaryKey(String email) {
		return getDAOUtente().findByPrimaryKey(email);
	}

	public List<Utente> getAllUtente() {
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

	public List<Calendario> findCalendarioByUtente(String email) {
		return getDAOCalendario().findByUtente(email);
	}

	public List<Calendario> findCalendarioByMembro(String email) {
		return getDAOCalendario().findByMembro(email);
	}

	public List<Calendario> findCalendarioByGruppo(int id) {
		return getDAOCalendario().findByGruppo(id);
	}

	public List<Calendario> getAllCalendario() {
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

	public List<Evento> findEventoByCalendario(int id) {
		return getDAOEvento().findByCalendario(id);
	}

	public List<Evento> findEventoByEtichetta(int id) {
		return getDAOEvento().findByEtichetta(id);
	}

	public List<Evento> getAllEvento() {
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

	public List<TDL> findTDLByGruppo(int id) {
		return getDAOTDL().findByGruppo(id);
	}

	public List<TDL> findTDLByUtente(String email) {
		return getDAOTDL().findByUtente(email);
	}

	public List<TDL> findTDLByMembro(String email) {
		return getDAOTDL().findByMembro(email);
	}

	public List<TDL> getAllTDL() {
		return getDAOTDL().findAll();
	}

	public void addTDL(TDL tdl) {
		getDAOTDL().save(tdl);
	}

	public void deleteTDL(TDL tdl) {
		getDAOTDL().delete(tdl);
	}

	public DAOTDL getDAOTDL() {
		return new DAOTDL(dataSource);
    }
    
    // PROMEMORIA //
	public Promemoria findPromemoriaByPrimaryKey(int id) {
		return getDAOPromemoria().findByPrimaryKey(id);
	}

	public List<Promemoria> findPromemoriaByTDL(int id) {
		return getDAOPromemoria().findByTDL(id);
	}

	public List<Promemoria> findPromemoriaByEtichetta(int id) {
		return getDAOPromemoria().findByEtichetta(id);
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

	public List<Notifica> findNotificaByUtente(String email) {
		return getDAONotifica().findByUtente(email);
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
	public Membro findMembroByPrimaryKey(String email, int id_gruppo) {
		return getDAOMembro().findByPrimaryKey(email, id_gruppo);
	}

	public List<Membro> findMembroByUtente(String email) { return getDAOMembro().findByUtente(email); }

	public List<Membro> findMembroByGruppo(int id) { return getDAOMembro().findByGruppo(id); }

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

	// PARTECIPANTE_EVENTO //
	public Partecipante findPartecipanteEventoByPrimaryKey(String email, int id) {
		return getDAOPartecipanteEvento().findByPrimaryKey(email, id);
	}

	public List<Partecipante> findPartecipanteEventoByUtente(String email) {
		return getDAOPartecipanteEvento().findByUtente(email);
	}

	public List<Partecipante> findPartecipanteByEvento(int id) {
		return getDAOPartecipanteEvento().findByEvento(id);
	}

	public List<Partecipante> getAllPartecipanteEvento() {
		return getDAOPartecipanteEvento().findAll();
	}

	public void addPartecipanteEvento(Partecipante partecipante) {
		getDAOPartecipanteEvento().save(partecipante);
	}

	public void deletePartecipanteEvento(Partecipante partecipante) {
		getDAOPartecipanteEvento().delete(partecipante);
	}

	public DAOPartecipanteEvento getDAOPartecipanteEvento() {
		return new DAOPartecipanteEvento(dataSource);
	}

	// PARTECIPANTE_PROMEMORIA //
	public Partecipante findPartecipantePromemoriaByPrimaryKey(String email, int id) {
		return getDAOPartecipantePromemoria().findByPrimaryKey(email, id);
	}

	public List<Partecipante> findPartecipantePromemoriaByUtente(String email) {
		return getDAOPartecipantePromemoria().findByUtente(email);
	}

	public List<Partecipante> findPartecipanteByPromemoria(int id) {
		return getDAOPartecipantePromemoria().findByPromemoria(id);
	}

	public List<Partecipante> getAllPartecipantePromemoria() {
		return getDAOPartecipantePromemoria().findAll();
	}

	public void addPartecipantePromemoria(Partecipante partecipante) {
		getDAOPartecipantePromemoria().save(partecipante);
	}

	public void deletePartecipantePromemoria(Partecipante partecipante) {
		getDAOPartecipantePromemoria().delete(partecipante);
	}

	public DAOPartecipantePromemoria getDAOPartecipantePromemoria() {
		return new DAOPartecipantePromemoria(dataSource);
	}

}
