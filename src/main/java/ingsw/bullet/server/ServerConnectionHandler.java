package ingsw.bullet.server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.client.ConnectionObject.LoginInfo;
import ingsw.bullet.client.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Error;
import ingsw.bullet.server.persistence.DBManager;

import java.io.IOException;
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
                        if(object instanceof LoginInfo){
                            LoginInfo info=(LoginInfo)object;
                            login(connection,info);
                        }

                  } //

                   else {
                       //se è loggato






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
    void login(Connection connection, LoginInfo info){
        String email=info.getEmail();
        String password=info.getPassword();
        if(DBManager.getInstance().getUser(String email) != 1){
            //se non esiste
            Error errore=new Error(Error.ErrorType.UserNotExist);
            connection.sendUDP(errore);
        }

        else{
            ///sostituire in equals to
            if(DBManager.getInstance().getPassword(String email) == password){
                //password correttam, aggiungere alle connessioni
                connessioniLoggate.put(connection,email);
                connessioniNONloggate.remove(connection);
            }

            else{
                //pass sbagliata
                Error errore= new Error(Error.ErrorType.WrongPassword);
                connection.sendUDP(errore);
            }


        }

    }




    //----------------AVVIO SERVER----------------------------
    public static void main(String[] args) {
        ServerConnectionHandler grotta=new ServerConnectionHandler();
    }
}
