package ingsw.bullet.server.NetworkUtility;

import ingsw.bullet.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Richiesta {


    private String tipoRichiesta=null;

    private int num;
    private String stringa;
    private String stringa2;

    public Richiesta(){

    }
    public String getStringa2() {
        return stringa2;
    }

    public void setStringa2(String stringa2) {
        this.stringa2 = stringa2;
    }

    private Calendario calendario=null;
    private Etichetta etichetta=null;
    private Evento evento=null;
    private Gruppo gruppo=null;
    private Membro membro=null;
    private  Notifica notifica=null;
    private Promemoria promemoria=null;
    private TDL TDL=null;
    private Utente utente=null;
    private Partecipante partecipante=null;

    public Richiesta(String s) {
        tipoRichiesta=s;
    }




    public String getTipoRichiesta() {
        return tipoRichiesta;
    }

    public void setTipoRichiesta(String tipoRichiesta) {
        this.tipoRichiesta = tipoRichiesta;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Etichetta getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(Etichetta etichetta) {
        this.etichetta = etichetta;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Notifica getNotifica() {
        return notifica;
    }

    public void setNotifica(Notifica notifica) {
        this.notifica = notifica;
    }

    public Promemoria getPromemoria() {
        return promemoria;
    }

    public void setPromemoria(Promemoria promemoria) {
        this.promemoria = promemoria;
    }

    public TDL getTDL() {
        return TDL;
    }

    public void setTDL(TDL TDL) {
        this.TDL = TDL;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }

    public void setPartecipante(Partecipante partecipante) {
        this.partecipante = partecipante;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStringa() {
        return stringa;
    }

    public void setStringa(String stringa) {
        this.stringa = stringa;
    }
}
