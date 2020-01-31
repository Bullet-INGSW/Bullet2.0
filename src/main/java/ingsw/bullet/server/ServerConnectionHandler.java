package ingsw.bullet.server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import ingsw.bullet.client.NetworkUtility.*;
import ingsw.bullet.server.persistence.DBManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;



public class ServerConnectionHandler {
    HashMap<Connection,String> connessioniLoggate;
    Vector<Connection> connessioniNONloggate;
    public ServerConnectionHandler() {
        Log.set(Log.LEVEL_DEBUG);

        Server server= new Server();
        KryoUtil.registerServerClasses(server);

        server.addListener(new Listener() {
               @Override
               public void connected(Connection connection) {

                   System.out.println("Connesso");
                   connessioniNONloggate.add(connection);
               }

               @Override
               public void disconnected(Connection connection) {

                   System.out.println("Disconnesso");
                    connessioniNONloggate.remove(connection);
                    connessioniLoggate.remove(connection);

               }

               @Override
               public void received(Connection connection, Object object) {
                  if(connessioniNONloggate.contains(connection)){
                      if(object instanceof Richiesta){
                          Richiesta r=(Richiesta)object;
                          Richiesta.TipoRichiesta tipo=r.getTipo();

                          switch (tipo){
                              case login:
                                  login(connection,r);
                                  break;
                              case insertUtente:
                                  //VA QUA INSERT  UTENTE?
                                  System.out.println("PLACEHOLDER");
                              default:
                                  break;

                          }

                      }
                  } //

                  else {
                      if(object instanceof Richiesta){
                          Richiesta r=(Richiesta)object;
                          Richiesta.TipoRichiesta tipo=r.getTipo();

                          switch (tipo){
                              case getUtente:
                                  getUtente(connection,r);
                                  break;
                              case creaGruppo:
                                  creaGruppo(connection,r);

                              case esisteGruppo:
                                  esisteGruppo(connection,r);
                                  break;
                              case esisteUtente:
                                  esisteUtente(connection,r);
                                  break;
                              case getNotifiche:
                                  getNotifiche(connection,r);
                                  break;
                              case setNotifiche:
                                  setNotifiche(connection,r);
                                  break;
                              case rimuoviMembro:
                                  rimuoviMembro(connection,r);
                                  break;
                              case aggiungiMembro:
                                  aggiungiMembro(connection,r);
                                  break;
                              case getElencoGruppi:
                                  getElencoGruppi(connection,r);
                                  break;
                              case getElencoMembri:
                                  getElencomembri(connection,r);
                                  break;
                              case getElencoCalendariCondivisi:
                                  getElencoCalendariCondivisi(connection,r);
                                  break;

                              case setMembriGruppo:
                                  setMembriGruppo(connection,r);
                                  break;
                              case aggiungiNotificheAlGruppo:
                                  aggiungiNotificheAlGruppo(connection,r);
                                  break;
                              case getElencoTDLCondivise:
                                  getElencoTDLCondivise(connection,r);
                                  break;
                              case utentiConNomiRegex:
                                  utentiConNomiRegex(connection,r);
                                  break;
                              case aggiungiNotifica:
                                  aggiungiNotifica(connection,r);
                                  break;
                                  //case

                              default:
                                  break;
                                  //IN CASO DI RICHIESTA NON GESTIBILE
                          }

                      }

                  }

               }
        }

        );//fine add listener

        try{
            server.bind(KryoUtil.TCP_PORT,KryoUtil.UDP_PORT);
        }

        catch (IOException e){
            e.getStackTrace();
        }

        server.start();
    }



    //-------------------METODI DI GESTIONE------------------------
    void login(Connection connection, Richiesta r){

        String email=r.getValori().get("email");
        String password=r.getValori().get("password");
        if(DBManager.getInstance().getUser(email) != 1){
            //se non esiste
            Error errore=new Error(Error.ErrorType.UserNotExist);
            //connection.sendUDP(errore);

            connection.sendUDP(false);
        }

        else{
            ///sostituire in equals to
            if(DBManager.getInstance().getPassword( email) == password){
                //password correttam, aggiungere alle connessioni
                connessioniLoggate.put(connection,email);
                connessioniNONloggate.remove(connection);

                connection.sendUDP(true);
            }

            else{
                //pass sbagliata
                Error errore= new Error(Error.ErrorType.WrongPassword);
                connection.sendUDP(false);
            }


        }

    }
    void getUtente(Connection connection,Richiesta r){
        String nome=r.getValori().get("nome");
        String cognome=r.getValori().get("cognome");
        String email=r.getValori().get("email");
        String sesso=r.getValori().get("sesso");

        if(DBManager.getInstance().getUtente(nome,cognome,email,sesso)!=null ){
            connection.sendUDP(true);
        }
        else{
            connection.sendUDP(false);
        }

    }
    void creaGruppo(Connection connection, Richiesta r) {
        String nome=r.getValori().get("nome");
        String ammministratore=r.getValori().get("amministratore");
        ArrayList<String> membri= r.getArray();

        //I METODI SUL DB DOVE MANDO ANCHE I BOOKL
    }
    void esisteGruppo(Connection connection, Richiesta r) {
        String gruppo=r.getValori().get("gruppo");
        //POI CHIEDERE A MARIA PER CHE RESTITUISCONO
        if(DBManager.getInstance().findGruppo(gruppo)==1)
            connection.sendUDP(true);

        else
            connection.sendUDP(false);

    }
    void esisteUtente(Connection connection, Richiesta r) {
        String email=r.getValori().get("email");
        if(DBManager.getInstance().findUserByPrimaryKey(email)!=null)
            connection.sendUDP(true);
        else
            connection.sendUDP(false);
    }
    void getNotifiche(Connection connection, Richiesta r) {
        String utente=r.getValori().get("utente");

        ArrayList<String>notifiche=DBManager.getInstance().getNotifiche(utente);
        connection.sendUDP(notifiche);
    }
    void setNotifiche(Connection connection, Richiesta r) {
        String utente=r.getValori().get("utente");
        ArrayList<String>notifiche= r.getArray();

        if(DBManager.getInstance().setNotifiche(utente,notifiche))
            connection.sendUDP(true);

        else
            connection.sendUDP(false);
    }
    void rimuoviMembro(Connection connection, Richiesta r) {
        String amministratore=r.getValori().get("amministratore");
        String gruppo=r.getValori().get("gruppo");
        String email=r.getValori().get("email");

        if(DBManager.getInstance().rimuoviMembro(amministratore,gruppo,email))
            connection.sendUDP(true);
        else
            connection.sendUDP(false);
    }
    void aggiungiMembro(Connection connection, Richiesta r) {
        String amministratore=r.getValori().get("amministratore");
        String gruppo=r.getValori().get("gruppo");
        String email=r.getValori().get("email");

        if(DBManager.getInstance().aggiungiMembro(amministratore,gruppo,email))
            connection.sendUDP(true);
        else
            connection.sendUDP(false);
    }
    void getElencoGruppi(Connection connection, Richiesta r) {
        String email=r.getValori().get("email");
        ArrayList<String>notifiche=DBManager.getInstance().getElencoGruppi(email);
        connection.sendUDP(notifiche);
    }
    void getElencomembri(Connection connection, Richiesta r) {
        String gruppo=r.getValori().get("gruppo");
        ArrayList<String>membri=DBManager.getInstance().getElencoMembri(gruppo);
        connection.sendUDP(membri);
    }
    void getElencoCalendariCondivisi(Connection connection, Richiesta r) {
        String utente=r.getValori().get("utente");
        ArrayList<String>calendari=DBManager.getInstance().getElencoCalendariCondivisi(utente);
        connection.sendUDP(calendari);
    }
    void setMembriGruppo(Connection connection, Richiesta r) {
        String gruppo=r.getValori().get("gruppo");
        ArrayList<String>membri=r.getArray();
        if(DBManager.getInstance().setMembriGruppo(gruppo,membri));
        connection.sendUDP(true);
        else
            connection.sendUDP(false);


    }
    void aggiungiNotificheAlGruppo(Connection connection, Richiesta r) {
        String gruppo=r.getValori().get("gruppo");
        String notifica=r.getValori().get("notifica");
        if(DBManager.getInstance().aggiungiNotificheAlGruppo(gruppo,notifica))
            connection.sendUDP(true);
        else
            connection.sendUDP(false);

    }
    void getElencoTDLCondivise(Connection connection, Richiesta r) {
        String utente=r.getValori().get("utente");
        ArrayList<String>TDL=DBManager.getInstance().getElencoTDLCondivise(utente);
        connection.sendUDP(TDL);
    }
    void utentiConNomiRegex(Connection connection, Richiesta r) {
        String regex=r.getValori().get("regex");
        ArrayList<String>utenti=DBManager.getInstance().utentiConNomiRegex(regex);
        connection.sendUDP(utenti);
    }
    void aggiungiNotifica(Connection connection, Richiesta r) {
        String utente=r.getValori().get("utente");
        String notifica=r.getValori().get("notifica");
        if(DBManager.getInstance().aggiungiNotifica(utente,notifica))
            connection.sendUDP(true);
        else
            connection.sendUDP(false);
    }



    //----------------AVVIO SERVER----------------------------
    public static void main(String[] args) {
        ServerConnectionHandler grotta=new ServerConnectionHandler();
    }
}
