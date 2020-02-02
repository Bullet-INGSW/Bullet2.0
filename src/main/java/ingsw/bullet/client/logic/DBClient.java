package ingsw.bullet.client.logic;

import com.esotericsoftware.kryonet.Client;
import ingsw.bullet.client.ClientConnectionhandler;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;

public class DBClient implements DBClientInterface {


    protected static DBClient dbClient;

    public static DBClient getIstance()
    {
        if(dbClient == null)
            dbClient = new DBClient();
        return dbClient;
    }

    @Override
    public boolean login(String email, String password) {
        String s="login";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setStringa2(password);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Calendario findCalendarioById(int id) {
        String s="findCalendarioById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getCalendario();

    }

    @Override
    public Calendario findCalendarioPersonaleByEmail(String email) {
        String s="findCalendarioPersonaleByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getCalendario();
    }

    @Override
    public List<Calendario> findCalendariCondivisiByEmail(String email) {
        String s="findCalendariCondivisiByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getListCalendario();
    }

    @Override
    public Calendario insertCalendario(Calendario c) {
        String s="insertCalendario";
        Richiesta r=new Richiesta(s);
        r.setCalendario(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getCalendario();
    }
    @Override
    public Calendario updateCalendario(Calendario c) {
        String s="updateCalendario";
        Richiesta r=new Richiesta(s);
        r.setCalendario(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getCalendario();}
    @Override
    public boolean removeCalendario(int id) {
        String s="removeCalendario";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Etichetta findEtichettaById(int id) {
        String s="findEtichettaById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEtichetta();
    }
    @Override
    public Etichetta insertEtichetta(Etichetta c) {
        String s="insertEtichetta";
        Richiesta r=new Richiesta(s);
        r.setEtichetta(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEtichetta();
    }
    @Override
    public Etichetta updateEtichetta(Etichetta c) {
        String s="updateEtichetta";
        Richiesta r=new Richiesta(s);
        r.setEtichetta(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEtichetta();
    }
    @Override
    public boolean removeEtichetta(int id) {
        String s="removeEtichetta";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Evento findEventoById(int id) {
        String s="findEventoById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEvento();
    }

    public Evento insertEvento(Evento c) {
        String s="insertEvento";
        Richiesta r=new Richiesta(s);
        r.setEvento(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEvento();

    }

    @Override
    public Evento updateEvento(Evento c) {
        String s="updateEvento";
        Richiesta r=new Richiesta(s);
        r.setEvento(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getEvento();
    }
    @Override
    public boolean removeEvento(int id) {
        String s="removeEvento";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Gruppo findGruppoById(int id) {
        String s="findGruppoById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getGruppo();
    }
    @Override
    public Gruppo insertGruppo(Gruppo c) {
        String s="insertGruppo";
        Richiesta r=new Richiesta(s);
        r.setGruppo(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getGruppo();
    }
    @Override
    public Gruppo updateGruppo(Gruppo c) {
        String s="updateGruppo";
        Richiesta r=new Richiesta(s);
        r.setGruppo(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getGruppo();
    }
    @Override
    public boolean removeGruppo(int id) {
        String s="removeGruppo";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }
    @Override
    public List<Gruppo> findGroupByEmail(String email) {
        String s="findGroupByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getListGruppo();
    }


    @Override
    public Membro insertMembro(Membro c) {
        String s="insertMembro";
        Richiesta r=new Richiesta(s);
        r.setMembro(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getMembro();
    }
    @Override
    public Membro updateMembro(Membro c) {
        String s="updateMembro";
        Richiesta r=new Richiesta(s);
        r.setMembro(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getMembro();
    }

    @Override
    public Membro findMembro(String email, String idGruppo) {
        String s="findMembro";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setStringa2(idGruppo);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getMembro();
    }

    @Override
    public boolean removeMembro(String email, String idGruppo) {
        String s="removeMembro";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setStringa2(idGruppo);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Notifica findNotificaById(int id) {
        String s="findNotificaById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getNotifica();
    }
    @Override
    public Notifica insertNotifica(Notifica c) {
        String s="insertNotifica";
        Richiesta r=new Richiesta(s);
        r.setNotifica(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getNotifica();
    }
    @Override
    public Notifica updateNotifica(Notifica c) {
        String s="updateNotifica";
        Richiesta r=new Richiesta(s);
        r.setNotifica(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getNotifica();
    }
    @Override
    public boolean removeNotifica(int id) {
        String s="removeNotifica";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public Promemoria findPromemoriaById(int id) {
        String s="findPromemoriaById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPromemoria();
    }
    @Override
    public Promemoria insertPromemoria(Promemoria c) {
        String s="insertPromemoria";
        Richiesta r=new Richiesta(s);
        r.setPromemoria(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPromemoria();
    }
    @Override
    public Promemoria updatePromemoria(Promemoria c) {
        String s="updatePromemoria";
        Richiesta r=new Richiesta(s);
        r.setPromemoria(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPromemoria();
    }
    @Override
    public boolean removePromemoria(int id) {
        String s="removePromemoria";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public TDL findTDLById(int id) {
        String s="findTDLById";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getTDL();
    }
    @Override
    public TDL insertTDL(TDL c) {
        String s="insertTDL";
        Richiesta r=new Richiesta(s);
        r.setTDL(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getTDL();
    }
    @Override
    public TDL updateTDL(TDL c) {
        String s="updateTDL";
        Richiesta r=new Richiesta(s);
        r.setTDL(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getTDL();
    }
    @Override
    public boolean removeTDL(int id) {
        String s="removeTDL";
        Richiesta r=new Richiesta(s);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    @Override
    public TDL findTDLPersonaleByEmail(String email) {
        String s="findTDLPersonaleByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getTDL();
    }

    @Override
    public List<TDL> findTDLCondiviseByEmail(String email) {
        String s="findTDLCondiviseByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getListTDL();
    }

    @Override
    public Utente findUtenteByEmail(String email) {
        String s="findUtenteByEmail";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            System.out.println("in attesa");
        }

        return istanza().getUtente();
    }
    @Override
    public Utente insertUtente(Utente c) {
        String s="insertUtente";
        Richiesta r=new Richiesta(s);
        r.setUtente(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getUtente();
    }
    @Override
    public Utente updateUtente(Utente c) {
        String s="updateUtente";
        Richiesta r=new Richiesta(s);
        r.setUtente(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getUtente();
    }
    @Override
    public boolean removeUtente(String email) {
        String s="removeUtente";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

//Partecipante promemoria
    @Override
    public Partecipante findPartecipantePromemoriaById(int id,String email) {
        String s="findPartecipantePromemoriaByEmail";

        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setNum(id);
        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPartecipante();
    }
    @Override
    public Partecipante insertPartecipantePromemoria(Partecipante c) {
        String s="insertPartecipantePromemoria";
        Richiesta r=new Richiesta(s);
        r.setPartecipante(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPartecipante();
    }
    @Override
    public Partecipante updatePartecipantePromemoria(Partecipante c) {
        String s="updatePartecipantePromemoria";
        Richiesta r=new Richiesta(s);
        r.setPartecipante(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPartecipante();
    }
    @Override
    public boolean removePartecipantePromemoria(int id,String email) {
        String s="removePartecipantePromemoria";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

//Partecipante evento
    @Override
    public Partecipante findPartecipanteEventoById(int id,String email) {
    String s="findPartecipanteEventoById";
    Richiesta r=new Richiesta(s);
    r.setStringa(email);
    r.setNum(id);

    setAttesa(true);

    client().sendTCP(r);
    while(inAttesa()){
        //Lock?
    }

    return istanza().getPartecipante();
}
    @Override
    public Partecipante insertPartecipanteEvento(Partecipante c) {
        String s="insertPartecipanteEvento";
        Richiesta r=new Richiesta(s);
        r.setPartecipante(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPartecipante();
    }
    @Override
    public Partecipante updatePartecipanteEvento(Partecipante c) {
        String s="updatePartecipanteEvento";
        Richiesta r=new Richiesta(s);
        r.setPartecipante(c);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().getPartecipante();
    }
    @Override
    public boolean removePartecipanteEvento(int id,String email) {
        String s="removePartecipanteEvento";
        Richiesta r=new Richiesta(s);
        r.setStringa(email);
        r.setNum(id);

        setAttesa(true);

        client().sendTCP(r);
        while(inAttesa()){
            //Lock?
        }

        return istanza().isBool();
    }

    //---------Altri metodi----------
    private Client client(){
        return ClientConnectionhandler.getInstance().client;
    }

    private boolean inAttesa(){
        return ClientConnectionhandler.getInstance().isInAttesa();
    }

    private void setAttesa(boolean b){
        ClientConnectionhandler.getInstance().setInAttesa(b);
    }

    private ClientConnectionhandler istanza(){
        return ClientConnectionhandler.getInstance();
    }
}
