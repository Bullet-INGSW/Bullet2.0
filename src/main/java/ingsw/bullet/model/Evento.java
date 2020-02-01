package ingsw.bullet.model;

import java.time.LocalDateTime;
import java.util.List;

public class Evento {

    private int id_evento;
    private int id_calendario;
    private int id_etichetta;
    private String nome;
    private String descrizione;
    private LocalDateTime data_inizio;
    private LocalDateTime data_fine;
    private boolean periodicita;
    private boolean full_day;
    private int id_recurrence;
    private String recurrence_rule;

    private List<Partecipante> partecipanti;

    public Evento() {
    }

    public Evento(int id_evento,
                  int id_calendario,
                  int id_etichetta,
                  String nome,
                  String descrizione,
                  LocalDateTime data_inizio,
                  LocalDateTime data_fine,
                  boolean periodicita,
                  boolean full_day,
                  int id_recurrence,
                  String recurrence_rule,
                  List<Partecipante> partecipanti) {

        this.id_evento = id_evento;
        this.id_calendario = id_calendario;
        this.id_etichetta = id_etichetta;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.periodicita = periodicita;
        this.full_day = full_day;
        this.id_recurrence = id_recurrence;
        this.recurrence_rule = recurrence_rule;
        this.partecipanti = partecipanti;
    }

    @Override
    public String toString() {
    	return	"[Evento] {" + "\n" +
    			"id_evento: " + this.id_evento + "\n" +
    			"id_calendario: " + this.id_calendario + "\n" +
    			"id_etichetta: " + this.id_etichetta + "\n" +
                "nome: " + this.nome + "\n" +
    			"descrizione: " + this.descrizione + "\n" +
    			"data_inizio: " + this.data_inizio + "\n" +
    			"data_fine: " + this.data_fine + "\n" +
                "periodicità: " + this.periodicita + "\n" +
                "full_day: " + this.full_day + "\n" +
                "id_recurrence: " + this.id_recurrence + "\n" +
                "recurrence_rule: " + this.recurrence_rule + "\n}\n";
    }

    public int getIdEvento() {return id_evento;}

    public void setIdEvento(int id_evento) {this.id_evento = id_evento;}

    public int getIdCalendario() {return id_calendario;}

    public void setIdCalendario(int id_calendario) {this.id_calendario = id_calendario;}

    public int getIdEtichetta() {return id_etichetta;}

    public void setIdEtichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public LocalDateTime getDataInizio() {return data_inizio;}

    public void setDataInizio(LocalDateTime data_inizio) {this.data_inizio = data_inizio;}

    public LocalDateTime getDataFine() {return data_fine;}

    public void setDataFine(LocalDateTime data_fine) {this.data_fine = data_fine;}

    public boolean getPeriodicita() {return periodicita;}

    public void setPeriodicita(boolean periodicita) {this.periodicita = periodicita;}

    public boolean isFullDay() {return full_day;}

    public void setFullDay(boolean full_day) {this.full_day = full_day;}

    public int getIdRecurrence() {return id_recurrence;}

    public void setIdRecurrence(int recurrence_rule) {this.id_recurrence = id_recurrence;}

    public String getRecurrenceRule() {return recurrence_rule;}

    public void setRecurrenceRule(String recurrence_rule) {this.recurrence_rule = recurrence_rule;}

    public List<Partecipante> getPartecipanti() {return partecipanti;}

    public void setPartecipanti(List<Partecipante> partecipanti) {this.partecipanti = partecipanti;}
}
