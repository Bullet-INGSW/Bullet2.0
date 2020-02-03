package ingsw.bullet.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.NetworkUtility.Errore;
import ingsw.bullet.server.NetworkUtility.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;


//DEVO CAMBIARE GLI IMPORT? VEDI MODEL

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientConnectionhandler {



    public static ClientConnectionhandler getInstance(){
        if(instance==null){
            instance=new ClientConnectionhandler();
        }

        return instance;
    }


    public Client client;

    private ClientConnectionhandler() {
        Log.set(Log.LEVEL_NONE);

        client=new Client();
        KryoUtil.registerClientClasses(client);
        client.setTimeout(500000);
        new Thread(client).start();

        client.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {


            }

            @Override
            public void disconnected(Connection connection) {

            }

            @Override
            public void received(Connection connection, Object object) {

                if(inAttesa){

                if(object instanceof FrameworkMessage.KeepAlive){

                }
                else if(object instanceof Boolean){
                     setBool((Boolean)object);
                    }//se boolean

                else if(object instanceof  Integer){
                      setNum((Integer)object);
                    }

                else if(object instanceof  Calendario){
                        setCalendario((Calendario)object);
                    }

                else if(object instanceof  Etichetta){
                    setEtichetta((Etichetta)object);
                }

                else if(object instanceof  Evento){
                    setEvento((Evento) object);
                }

                else if(object instanceof  Gruppo){
                    setGruppo((Gruppo)object);
                }

                else if(object instanceof  Membro){
                    setMembro((Membro) object);
                }

                else if(object instanceof  Notifica){
                    setNotifica((Notifica) object);
                }

                else if(object instanceof  Promemoria){
                    setPromemoria((Promemoria) object);
                }

                else if(object instanceof  TDL){
                    setTDL((TDL) object);
                }

                else if(object instanceof  Utente){
                    setUtente((Utente) object);
                }

                else if(object instanceof  Partecipante){
                    setPartecipante((Partecipante) object);
                }

                else if(object instanceof List){

                    if( ((List)object).size()>0 && ((List) object).get(0) instanceof Gruppo)
                    setListGruppo((List<Gruppo>)object);

                    else if( ((List)object).size()>0 &&((List)object).get(0) instanceof TDL)
                        setListTDL((List<TDL>)object);

                    else if( ((List)object).size()>0 &&((List)object).get(0) instanceof Calendario)
                        setListCalendario((List<Calendario>)object);
                    else if( ((List)object).size()>0 &&((List)object).get(0) instanceof Evento)
                        setListEvento((List<Evento>)object);
                }

                else if(object instanceof Errore){
                    ritornoVuoto=true;

                }

                else{
                        System.out.println("Impossibile gestire il tipo: " + object.getClass());
                }

                if(!(object instanceof FrameworkMessage.KeepAlive))
                  inAttesa=false;
              }// se sto aspettando la risposta da una richiesta

            else{
                //se non sto aspettando nulla


              }

            }
        });

        try {
            client.connect(1000000,"127.0.0.1",KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
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


    public List<Gruppo> getListGruppo() {
        return listGruppo;
    }

    public void setListGruppo(List<Gruppo> listGruppo) {
        this.listGruppo = listGruppo;
    }

    public List<Calendario> getListCalendario() {
        return listCalendario;
    }

    public void setListCalendario(List<Calendario> listCalendario) {
        this.listCalendario = listCalendario;
    }

    public List<Evento> getListEvento() {
        return listEvento;
    }

    public void setListEvento(List<Evento> listEvento) {
        this.listEvento = listEvento;
    }

    //Attributi
    boolean inAttesa=false;
    int num;
    boolean bool;
    public boolean ritornoVuoto=false;
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
    List<Gruppo> listGruppo=null;
    List<Calendario> listCalendario=null;
    List<TDL> listTDL=null;
    List<Evento> listEvento=null;
    private static ClientConnectionhandler instance=null;

    public List<TDL> getListTDL() {
        return listTDL;
    }

    public void setListTDL(List<TDL> listTDL) {
        this.listTDL = listTDL;
    }
}
