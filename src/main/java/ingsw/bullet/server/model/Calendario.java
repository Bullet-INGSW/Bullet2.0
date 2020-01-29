package ingsw.bullet.server.model;

public class Calendario {

    private int id_calendario;
    private int id_gruppo;
    private String email;
    private String descrizione;

    public Calendario() {
    }

    public Calendario(int id_calendario, int id_gruppo, String email, String descrizione) {
        this.id_calendario = id_calendario;
        this.id_gruppo = id_gruppo;
        this.email = email;
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "id_calendario=" + id_calendario +
                ", id_gruppo=" + id_gruppo +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId_calendario() {return id_calendario;}

    public void setId_calendario(int id_calendario) {this.id_calendario = id_calendario;}

    public int getId_gruppo() {return id_gruppo;}

    public void setId_gruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
}
