package ingsw.bullet.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.NetworkUtility.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;


//DEVO CAMBIARE GLI IMPORT? VEDI MODEL

import java.io.IOException;
import java.util.ArrayList;

public class ClientConnectionhandler {

     boolean inAttesa=false;
     int num;
    boolean bool;

     Calendario calendario=null;
     Etichetta etichetta=null;
     Evento evento=null;
     Gruppo gruppo=null;
     Membro membro=null;
      Notifica notifica=null;
     Promemoria promemoria=null;
     TDL TDL=null;
     Utente utente=null;
     Partecipante partecipante=null;


    private static ClientConnectionhandler instance=null;

    public static ClientConnectionhandler getInstance(){
        if(instance==null){
            instance=new ClientConnectionhandler();
        }

        return instance;
    }


    public Client client;

    private ClientConnectionhandler() {
        Log.set(Log.LEVEL_DEBUG);

        client=new Client();

        KryoUtil.registerClientClasses(client);
        new Thread(client).start();

        client.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {


            }

            @Override
            public void disconnected(Connection connection) {

                System.out.println("Disconesso dal server");
            }

            @Override
            public void received(Connection connection, Object object) {

                if(inAttesa){

                if(object instanceof Boolean){
                     setBool((Boolean)object);
                    }//se boolean

                if(object instanceof  Integer){
                      setNum((Integer)object);
                    }

                if(object instanceof  Calendario){
                        setCalendario((Calendario)object);
                    }

                if(object instanceof  Etichetta){
                    setEtichetta((Etichetta)object);
                }

                if(object instanceof  Evento){
                    setEvento((Evento) object);
                }

                if(object instanceof  Gruppo){
                    setGruppo((Gruppo)object);
                }

                if(object instanceof  Membro){
                    setMembro((Membro) object);
                }

                if(object instanceof  Notifica){
                    setNotifica((Notifica) object);
                }

                if(object instanceof  Promemoria){
                    setPromemoria((Promemoria) object);
                }

                if(object instanceof  TDL){
                    setTDL((TDL) object);
                }

                if(object instanceof  Utente){
                    setUtente((Utente) object);
                }

                if(object instanceof  Partecipante){
                    setPartecipante((Partecipante) object);
                }



                  inAttesa=false;
              }// se sto aspettando la risposta da una richiesta

            else{
                //se non sto aspettando nulla


              }

            }
        });

        try {
            client.connect(5000,"127.0.0.1",KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //-------------GETTER E SETTER-----------

    public boolean isInAttesa() {
        return inAttesa;
    }

    public void setInAttesa(boolean inAttesa) {
        this.inAttesa = inAttesa;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
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

    public ingsw.bullet.server.model.TDL getTDL() {
        return TDL;
    }

    public void setTDL(ingsw.bullet.server.model.TDL TDL) {
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


}
