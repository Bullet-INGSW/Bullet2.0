package ingsw.bullet.client.logic.calendar;


import java.sql.Time;

public class Evento {

    protected int id;
    protected String titolo;
    protected Etichetta etichetta;
    protected Time dataInizio;
    protected Time dataFine;
    protected boolean fullDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Etichetta getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(Etichetta etichetta) {
        this.etichetta = etichetta;
    }

    public Time getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Time dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Time getDataFine() {
        return dataFine;
    }

    public void setDataFine(Time dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isFullDay() {
        return fullDay;
    }

    public void setFullDay(boolean fullDay) {
        this.fullDay = fullDay;
    }

    public Evento(int id, String titolo, Etichetta etichetta, Time dataInizio, Time dataFine, boolean fullDay) {
        this.id = id;
        this.titolo = titolo;
        this.etichetta = etichetta;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.fullDay = fullDay;
    }

}
