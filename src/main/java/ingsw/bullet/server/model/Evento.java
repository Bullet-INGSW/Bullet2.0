package ingsw.bullet.server.model;

import java.util.Date;

public class Evento {

    private int id_evento;
    private int id_calendario;
    private int id_etichetta;
    private String descrizione;
    private Date data_inizio;
    private Date data_fine;
    private boolean periodicita;
    private String nome;

    public Evento() {
    }

    public Evento(int id_evento, int id_calendario, int id_etichetta, String descrizione, Date data_inizio, Date data_fine, boolean periodicita, String nome) {
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
        return "Evento{" +
                "id_evento=" + id_evento +
                ", id_calendario=" + id_calendario +
                ", id_etichetta=" + id_etichetta +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", periodicita=" + periodicita +
                ", nome='" + nome + '\'' +
                '}';
    }

    public int getId_evento() {return id_evento;}

    public void setId_evento(int id_evento) {this.id_evento = id_evento;}

    public int getId_calendario() {return id_calendario;}

    public void setId_calendario(int id_calendario) {this.id_calendario = id_calendario;}

    public int getId_etichetta() {return id_etichetta;}

    public void setId_etichetta(int id_etichetta) {this.id_etichetta = id_etichetta;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public Date getData_inizio() {return data_inizio;}

    public void setData_inizio(Date data_inizio) {this.data_inizio = data_inizio;}

    public Date getData_fine() {return data_fine;}

    public void setData_fine(Date data_fine) {this.data_fine = data_fine;}

    public boolean isPeriodicita() {return periodicita;}

    public void setPeriodicita(boolean periodicita) {this.periodicita = periodicita;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
