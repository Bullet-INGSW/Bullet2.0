package ingsw.bullet.client.logic;

import java.util.ArrayList;

public class DBClient implements DBClientInterface {

    protected static DBClient dbClient;

    public static DBClient getIstance()
    {
        if(dbClient == null)
            dbClient = new DBClient();
        return dbClient;
    }

    @Override
    public boolean esisteUtente(String email) {
        return false;
    }

    @Override
    public int creaGruppo(String amministratore, String nome, ArrayList<String> membri) {
        return 0;
    }

    @Override
    public ArrayList<String> getElencoGruppi(String email) {
        return null;
    }

    @Override
    public ArrayList<String> getElencoMembri(String gruppo) {
        return null;
    }

    @Override
    public boolean setMembriGruppo(String gruppo, ArrayList<String> elencoMembri) {
        return false;
    }

    @Override
    public boolean getUtente(String nome, String cognome, String email, boolean sesso) {
        return false;
    }

    @Override
    public boolean insertUtente(String nome, String cognome, String email, boolean sesso, String password) {
        return false;
    }

    @Override
    public boolean rimuoviMembro(String gruppo, String email, String amministratore) {
        return false;
    }


    @Override
    public boolean login(String email, String password) {
        return false;
    }

    @Override
    public ArrayList<String> getNotifiche(String utente) {
        return null;
    }

    @Override
    public boolean setNotifiche(String utente, ArrayList<String> notifiche) {
        return false;
    }

    @Override
    public boolean aggiungiNotiche(String utente, String notifica) {
        return false;
    }

    @Override
    public boolean aggiungiNotificheAlGruppo(String gruppo, String notifica) {
        return false;
    }

    @Override
    public ArrayList<String> getElencoCalendariCondivisi(String utente) {
        return null;
    }

    @Override
    public ArrayList<String> getElencoTDLCondivise(String utente) {
        return null;
    }

    @Override
    public ArrayList<String> utentiConNomiRegex(String regexEmail) {
        return null;
    }

    @Override
    public boolean esisteGruppo(String gruppo) {
        return false;
    }
}
