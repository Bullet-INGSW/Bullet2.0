package ingsw.bullet.server.model;

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
        return "Notifica{" +
                "id_notifica=" + id_notifica +
                ", email='" + email + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    public int getId_notifica() {return id_notifica;}

    public void setId_notifica(int id_notifica) {this.id_notifica = id_notifica;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
}
