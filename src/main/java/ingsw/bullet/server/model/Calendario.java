package ingsw.bullet.server.model;

public class Calendario {

    private int id_calendario;
    private int id_gruppo;
    private String email;
    private String nome;

    public Calendario() {
    }

    public Calendario(int id_calendario, int id_gruppo, String email, String nome) {
        this.id_calendario = id_calendario;
        this.id_gruppo = id_gruppo;
        this.email = email;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return	"[TDL] {" + "\n" +
                "id_calendario: " + this.id_calendario + "\n" +
                "id_gruppo: " + this.id_gruppo + "\n" +
                "email: " + this.email + "\n" +
                "nome: " + this.nome + "\n}\n";
    }

    public int getIdCalendario() {return id_calendario;}

    public void setIdCalendario(int id_calendario) {this.id_calendario = id_calendario;}

    public int getIdGruppo() {return id_gruppo;}

    public void setIdGruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNome() {return nome;}

    public void setNome(String descrizione) {this.nome = nome;}
}
