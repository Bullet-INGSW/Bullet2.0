package ingsw.bullet.model;

import java.util.List;

public class TDL implements GestoreAttivita{

    private int id_tdl;
    private int id_gruppo;
    private String email;
    private String nome;
    private List<Promemoria> promemoria;
    private List<Etichetta> etichette;

    public List<Promemoria> getPromemoria() {
        return promemoria;
    }

    public void setPromemoria(List<Promemoria> promemoria) {
        this.promemoria = promemoria;
    }

    public List<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(List<Etichetta> etichette) {
        this.etichette = etichette;
    }

    public TDL() {
    }

    public TDL(int id_tdl, int id_gruppo, String email, String nome) {
        this.id_tdl = id_tdl;
        this.id_gruppo = id_gruppo;
        this.email = email;
        this.nome = nome;
    }

    @Override
    public String toString() {
    	return	"[TDL] {" + "\n" +
    			"id_tdl: " + this.id_tdl + "\n" +
    			"id_gruppo: " + this.id_gruppo + "\n" +
    			"email: " + this.email + "\n" +
    			"nome: " + this.nome + "\n}\n";
    }

    public int getIdTDL() {return id_tdl;}

    public void setIdTDL(int id_tdl) {this.id_tdl = id_tdl;}

    public int getIdGruppo() {return id_gruppo;}

    public void setIdGruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
