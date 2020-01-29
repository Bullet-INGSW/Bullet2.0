package ingsw.bullet.server.model;

import java.util.Date;

public class Promemoria {

    private int id_promemoria;
    private int id_tdl;
    private int id_etichetta;
    private String descrizione;
    private boolean completato;
    private Date scadenza;

    public Promemoria() {
    }

    public Promemoria(int id_promemoria, int id_tdl, int id_etichetta, String descrizione, boolean completato, Date scadenza) {
        this.id_promemoria = id_promemoria;
        this.id_tdl = id_tdl;
        this.id_etichetta = id_etichetta;
        this.descrizione = descrizione;
        this.completato = completato;
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Promemoria{" +
                "id_promemoria=" + id_promemoria +
                ", id_tdl=" + id_tdl +
                ", id_etichetta=" + id_etichetta +
                ", descrizione='" + descrizione + '\'' +
                ", completato=" + completato +
                ", scadenza=" + scadenza +
                '}';
    }

    public int getId_promemoria() {return id_promemoria;}

    public void setId_promemoria(int id_promemoria) {this.id_promemoria = id_promemoria;}

    public int getId_tdl() {return id_tdl;}

    public void setId_tdl(int id_tdl) {this.id_tdl = id_tdl;}

    public int getId_etichetta() {return id_etichetta;}

    public void setId_etichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public boolean isCompletato() {return completato;}

    public void setCompletato(boolean completato) {this.completato = completato;}

    public Date getScadenza() {return scadenza;}

    public void setScadenza(Date scadenza) {this.scadenza = scadenza;}
}
