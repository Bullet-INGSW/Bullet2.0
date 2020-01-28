package ingsw.bullet.server.persistence;

public class DBManager {

	private final static String url = "jdbc:mariadb://bullet.cbogc2q03ixz.eu-west-2.rds.amazonaws.com:3306/bullet";
	private final static String user = "admin";
	private final static String password = "6e8PM37WTKmRG7xkdrYK";

	private static DataSource dataSource;

	static {
		try {
			System.out.println("DATI ------------ " + url + " " + user + " " + password);
			Class.forName("org.mariadb.jdbc.Driver");
			dataSource = new DataSource(url, user, password);
			System.out.println("    Connessione al DB ok\n");
		} catch (Exception e) {
			System.err.println("    mariamllml.class: failed to load MySQL JDBC driver\n" + e);
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


}
