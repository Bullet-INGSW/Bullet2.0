package ingsw.bullet.server.NetworkUtility;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import ingsw.bullet.model.*;
import javafx.event.Event;

import java.util.ArrayList;


public class KryoUtil {

    public static final int TCP_PORT=55234;
    public static  final int UDP_PORT=55235;

    public static void registerServerClasses(Server server){
        register(server.getKryo());

    }

    public static  void registerClientClasses(Client client){
        register(client.getKryo());
    }



    private  static  void register(Kryo kryo){

        //kryo.register(int.class);
        //kryo.register(String.class);
       kryo.register(Boolean.class);
        kryo.register(boolean.class);
        //kryo.register(ArrayList.class);
        kryo.register(Utente.Sesso.class);
        kryo.register(Calendario.class);
        kryo.register(Etichetta.class);
        kryo.register(Evento.class);
        kryo.register(Gruppo.class);
        kryo.register(Membro.class);
        kryo.register(Notifica.class);
        kryo.register(Promemoria.class);
        kryo.register(TDL.class);
        kryo.register(Utente.class);
        kryo.register(Richiesta.class);
        kryo.register(Partecipante.class);
        kryo.register(Errore.class);
        //FINIRE THIS

    }

}
