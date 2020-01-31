package ingsw.bullet.server.model;

import java.util.List;

public class Utente {
	
	public enum Sesso {M,F};
	
	private String nome;
	private String cognome;
	private Sesso sesso;
	private String email;
	private String password;
	private List<Notifica> notifiche;
	
	public Utente() {
	}
	
	public Utente(String nome, String cognome, Sesso sesso, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return  "[Utente] {" + "\n" +
				"nome: " + this.nome + "\n" +
				"cognome: " + this.cognome + "\n" +
				"sesso: " + this.sesso + "\n" +
				"email: " + this.email + "\n" +
				"password: " + this.password + "\n}\n";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Notifica> getNotifiche() { return notifiche; }

	public void setNotifiche(List<Notifica> notifiche) { this.notifiche = notifiche; }
}