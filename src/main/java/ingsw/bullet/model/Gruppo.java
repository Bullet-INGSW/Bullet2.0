package ingsw.bullet.model;

import java.util.List;

public class Gruppo {

    private int id_gruppo;
    private String nome;

    private List<Membro> membri;
    private List<Membro> amministratori;
    private List<Calendario> calendari;
    private List<TDL> tdl;


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

    public List<Membro> getMembri() {return membri;}

    public void setMembri(List<Membro> membri) {this.membri = membri;}

    public List<Membro> getAmministratori() {return amministratori;}

    public void setAmministratori(List<Membro> amministratori) {this.amministratori = amministratori;}

    public List<Calendario> getCalendari() {return calendari;}

    public void setCalendari(List<Calendario> calendari) {this.calendari = calendari;}

    public List<TDL> getTDL() {return tdl;}

    public void setTDL(List<TDL> calendari) {this.tdl = tdl;}
}
