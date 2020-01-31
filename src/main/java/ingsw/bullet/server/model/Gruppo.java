package ingsw.bullet.server.model;

import java.util.ArrayList;

public class Gruppo {

    private int id_gruppo;
    private String nome;
    private ArrayList<Membro> membri;
    private ArrayList<Membro> amministratori;
    private ArrayList<Calendario> calendari;
    private ArrayList<TDL> tdl;


    public Gruppo() {
    }

    public Gruppo(int id_gruppo, String nome) {
        this.id_gruppo = id_gruppo;
        this.nome = nome;
    }

    @Override
    public String toString() {
    	return	"[Gruppo] {" + "\n" +
    			"id_gruppo: " + this.id_gruppo + "\n" +
    			"nome: " + this.nome + "\n}\n";
    }

    public int getIdGruppo() {return id_gruppo;}

    public void setIdGruppo(int id_gruppo) {this.id_gruppo = id_gruppo;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
