package ingsw.bullet.model;

import java.util.ArrayList;
import java.util.List;

public class Calendario implements GestoreAttivita{

    private int id_calendario = 0;
    private int id_gruppo = 0;
    private String email = null;
    private String nome = null;

    private List<Evento> eventi = new ArrayList<>();
    private List<Etichetta> etichette = new ArrayList<>();

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
        return	"[Calendario] {" + "\n" +
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

    public void setNome(String descrizione) {this.nome = descrizione;}

    public List<Evento> getEventi() { return eventi; }

    public void setEventi(List<Evento> eventi) { this.eventi = eventi; }

    public List<Etichetta> getEtichette() { return etichette; }

    public void setEtichette(List<Etichetta> etichette) { this.etichette = etichette; }
}
