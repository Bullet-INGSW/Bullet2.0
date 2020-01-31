package ingsw.bullet.server.model;

public class Partecipante {
    private String email;
    private  int id_evento;
    private boolean presente;

    public Partecipante() {
    }

    public Partecipante(String email, int id_evento, boolean presente) {
        this.email = email;
        this.id_evento = id_evento;
        this.presente = presente;
    }

    @Override
    public String toString() {
        return	"[Partecipante] {" + "\n" +
                "email: " + this.email + "\n" +
                "id_evento: " + this.id_evento + "\n" +
                "presente: " + this.presente + "\n}\n";
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getIdEvento() {return id_evento;}

    public void setIdEvento(int id_evento) {this.id_evento = id_evento;}

    public boolean isPresente() {return presente;}

    public void setPresente(boolean presente) {this.presente = presente;}
}
