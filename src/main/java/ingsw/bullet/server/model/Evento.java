package ingsw.bullet.server.model;

import java.time.LocalDateTime;

public class Evento {

    private int id_evento;
    private int id_calendario;
    private int id_etichetta;
    private String descrizione;
    private LocalDateTime data_inizio;
    private LocalDateTime data_fine;
    private boolean periodicita;
    private String nome;

    public Evento() {
    }

    public Evento(int id_evento, int id_calendario, int id_etichetta, String descrizione, LocalDateTime data_inizio, LocalDateTime data_fine, boolean periodicita, String nome) {
        this.id_evento = id_evento;
        this.id_calendario = id_calendario;
        this.id_etichetta = id_etichetta;
        this.descrizione = descrizione;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.periodicita = periodicita;
        this.nome = nome;
    }

    @Override
    public String toString() {
    	return	"[Evento] {" + "\n" +
    			"id_evento: " + this.id_evento + "\n" +
    			"id_calendario: " + this.id_calendario + "\n" +
    			"id_etichetta: " + this.id_etichetta + "\n" +
    			"descrizione: " + this.descrizione + "\n" +
    			"data_inizio: " + this.data_inizio + "\n" +
    			"data_fine: " + this.data_fine + "\n" +
    			"periodicità: " + this.periodicita + "\n}\n";
    }

    public int getIdEvento() {return id_evento;}

    public void setIdEvento(int id_evento) {this.id_evento = id_evento;}

    public int getIdCalendario() {return id_calendario;}

    public void setIdCalendario(int id_calendario) {this.id_calendario = id_calendario;}

    public int getIdEtichetta() {return id_etichetta;}

    public void setIdEtichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public LocalDateTime getDataInizio() {return data_inizio;}

    public void setDataInizio(LocalDateTime data_inizio) {this.data_inizio = data_inizio;}

    public LocalDateTime getDataFine() {return data_fine;}

    public void setDataFine(LocalDateTime data_fine) {this.data_fine = data_fine;}

    public boolean getPeriodicita() {return periodicita;}

    public void setPeriodicita(boolean periodicita) {this.periodicita = periodicita;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
