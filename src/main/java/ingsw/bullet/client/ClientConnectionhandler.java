package ingsw.bullet.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.client.NetworkUtility.KryoUtil;
import ingsw.bullet.client.NetworkUtility.Richiesta;


import java.io.IOException;
import java.util.ArrayList;

public class ClientConnectionhandler {

    public boolean inAttesa=false;
    public boolean risultatoBool=true;
    public int risultatoInt=-1;
    public ArrayList<String> risultatostringArrayList=null;
    public Richiesta.TipoRichiesta tipoAttuale=null;


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
                    risultatoBool=(Boolean)object;
                    }//se boolean

                  if(object instanceof  Integer){
                      Integer i=(Integer)object;
                      risultatoInt=i;
                    }

                  if(object instanceof ArrayList<?>){
                      if(((ArrayList<?>)object).get(0) instanceof String)
                      {
                         risultatostringArrayList=(ArrayList<String>)object;
                      }


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
/*












 */

   // public static void main(String[] args) {
    //    new ClientConnectionhandler();
    //}
}
