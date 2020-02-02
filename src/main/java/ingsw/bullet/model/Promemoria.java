package ingsw.bullet.model;

import java.time.LocalDateTime;
import java.util.List;

public class Promemoria {

    private int id_promemoria;
    private int id_tdl;
    private int id_etichetta;
    private String nome;
    private String descrizione;
    private boolean completato;
    private LocalDateTime scadenza;

    private List<Partecipante> partecipanti;

    public Promemoria() {
    }

    public Promemoria(int id_promemoria, int id_tdl, int id_etichetta, String nome, String descrizione, boolean completato, LocalDateTime scadenza) {
        this.id_promemoria = id_promemoria;
        this.id_tdl = id_tdl;
        this.id_etichetta = id_etichetta;
        this.nome = nome;
        this.descrizione = descrizione;
        this.completato = completato;
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
    	return	"[Promemoria] {" + "\n" +
    			"id_promemoria: " + this.id_promemoria + "\n" +
    			"id_tdl: " + this.id_tdl + "\n" +
    			"id_etichetta: " + this.id_etichetta + "\n" +
                "nome: " + this.nome + "\n" +
    			"descrizione: " + this.descrizione + "\n" +
    			"completato: " + this.completato + "\n" +
    			"scadenza: " + this.scadenza + "\n}\n";
    }

    public int getIdPromemoria() {return id_promemoria;}

    public void setIdPromemoria(int id_promemoria) {this.id_promemoria = id_promemoria;}

    public int getIdTDL() {return id_tdl;}

    public void setIdTDL(int id_tdl) {this.id_tdl = id_tdl;}

    public int getIdEtichetta() {return id_etichetta;}

    public void setIdEtichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public boolean isCompletato() {return completato;}

    public void setCompletato(boolean completato) {this.completato = completato;}

    public LocalDateTime getScadenza() {return scadenza;}

    public void setScadenza(LocalDateTime scadenza) {this.scadenza = scadenza;}

    public List<Partecipante> getPartecipanti() {return partecipanti;}

    public void setPartecipanti(List<Partecipante> partecipanti) {this.partecipanti = partecipanti;}
}
