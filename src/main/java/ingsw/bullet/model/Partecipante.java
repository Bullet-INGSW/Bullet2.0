package ingsw.bullet.model;

public class Partecipante {
    private String email;
    private int id;
    private boolean presente;

    public Partecipante() {
    }

    public Partecipante(String email, int id, boolean presente) {
        this.email = email;
        this.id = id;
        this.presente = presente;
    }

    @Override
    public String toString() {
        return	"[Partecipante] {" + "\n" +
                "email: " + this.email + "\n" +
                "id: " + this.id + "\n" +
                "presente: " + this.presente + "\n}\n";
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public boolean isPresente() {return presente;}

    public void setPresente(boolean presente) {this.presente = presente;}
}
