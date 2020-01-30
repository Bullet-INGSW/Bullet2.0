package ingsw.bullet.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.persistence.DBManager;


import java.io.IOException;
import java.util.ArrayList;

public class ClientConnectionhandler {

    public boolean inAttesa=false;
    public boolean risultatoBool=true;
    public int risultatoInt=-1;
    public ArrayList<String> risultatostringArrayList=null;



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
                //OggettoInviabile inviabile=new OggettoInviabile("UEUEFRATM");
                //client.sendTCP(inviabile);
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("M'agg disconesso");
            }

            @Override
            public void received(Connection connection, Object object) {
               if(object instanceof String){
                   String ricevuto=(String)object;
                   System.out.println("HO ricevuto il testo "+ricevuto);
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
