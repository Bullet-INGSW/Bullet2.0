package ingsw.bullet.model;

public class Notifica {

    private int id_notifica;
    private String email;
    private String descrizione;

    public Notifica() {
    }

    public Notifica(int id_notifica, String email, String descrizione) {
        this.id_notifica = id_notifica;
        this.email = email;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
    	return	"[Notifica] {" + "\n" +
    			"id_notifica: " + this.id_notifica + "\n" +
    			"email: " + this.email + "\n" +
    			"descrizione: " + this.descrizione + "\n}\n";
    }

    public int getIdNotifica() {return id_notifica;}

    public void setIdNotifica(int id_notifica) {this.id_notifica = id_notifica;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
}
