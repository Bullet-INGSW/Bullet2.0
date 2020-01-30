package ingsw.bullet.client.logic;

import ingsw.bullet.client.ClientConnectionhandler;
import ingsw.bullet.client.NetworkUtility.Richiesta;

import java.util.ArrayList;
import java.util.HashMap;

public class DBClient implements DBClientInterface {

    protected static DBClient dbClient;

    public static DBClient getIstance()
    {
        if(dbClient == null)
            dbClient = new DBClient();
        return dbClient;
    }

    @Override
    public boolean aggiungiMembro(String gruppo, String email, String amministratore) {

        //????? amministratore?
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        valori.put("email",email);
        valori.put("amministratore",amministratore);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.aggiungiMembro, valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public boolean esisteUtente(String email) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("email",email);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.esisteUtente,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;

    }


    //che deve tornare?
    @Override
    public int creaGruppo(String amministratore, String nome, ArrayList<String> membri) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("nome",nome);
        valori.put("amministratore",amministratore);

        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.creaGruppo,valori,membri);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoInt;


    }

    @Override
    public ArrayList<String> getElencoGruppi(String email) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("email",email);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getElencoGruppi,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;

    }

    @Override
    public ArrayList<String> getElencoMembri(String gruppo) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getElencoMembri,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;
    }

    @Override
    public boolean setMembriGruppo(String gruppo, ArrayList<String> elencoMembri) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.setMembriGruppo,valori,elencoMembri);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;    }

    @Override
    public boolean getUtente(String nome, String cognome, String email, boolean sesso) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("nome",nome);
        valori.put("cognome",cognome);
        valori.put("email",email);

        valori.put("sesso","DACOMPLETAREASSOLUTAMETNE");
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getUtente,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()==true){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public boolean insertUtente(String nome, String cognome, String email, boolean sesso, String password) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("nome",nome);
        valori.put("cognome",cognome);
        valori.put("email",email);
        valori.put("sesso","DACOMPLETARE");
        valori.put("password",password);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.insertUtente,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public boolean rimuoviMembro(String gruppo, String email, String amministratore) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        valori.put("email",email);
        valori.put("amministratore",amministratore);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.rimuoviMembro,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public boolean login(String email, String password) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("email",email);
        valori.put("password",password);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.login,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;    }

    @Override
    public ArrayList<String> getNotifiche(String utente) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("utente",utente);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getNotifiche,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;    }

    @Override
    public boolean setNotifiche(String utente, ArrayList<String> notifiche) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("utente",utente);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.setNotifiche,valori,notifiche);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public boolean aggiungiNotifica(String utente, String notifica) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("utente",utente);
        valori.put("notifica",notifica);

        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.aggiungiNotifica,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }


    //NOTIFICA
    @Override
    public boolean aggiungiNotificheAlGruppo(String gruppo, String notifica) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        valori.put("notifica",notifica);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.aggiungiNotificheAlGruppo,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;
    }

    @Override
    public ArrayList<String> getElencoCalendariCondivisi(String utente) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("utente",utente);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getElencoCalendariCondivisi,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;
    }

    @Override
    public ArrayList<String> getElencoTDLCondivise(String utente) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("utente",utente);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.getElencoTDLCondivise,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;
    }

    //????????????????????????
    @Override
    public ArrayList<String> utentiConNomiRegex(String regexEmail) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("regex",regexEmail);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.utentiConNomiRegex,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatostringArrayList;
    }

    @Override
    public boolean esisteGruppo(String gruppo) {
        HashMap<String,String> valori=new HashMap<>();
        valori.put("gruppo",gruppo);
        Richiesta richiesta=new Richiesta(Richiesta.TipoRichiesta.esisteGruppo,valori);

        ClientConnectionhandler.getInstance().inAttesa=true;
        ClientConnectionhandler.getInstance().client.sendTCP(richiesta);
        while(inAttesa()){
            //ASPETTA
        }
        return ClientConnectionhandler.getInstance().risultatoBool;    }


    public boolean inAttesa(){
        return ClientConnectionhandler.getInstance().inAttesa;
    }
}
