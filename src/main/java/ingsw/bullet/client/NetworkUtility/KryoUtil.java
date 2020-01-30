package ingsw.bullet.client.NetworkUtility;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

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
       //kryo.register(OggettoInviabile.class);
        kryo.register(int.class);
        kryo.register(String.class);
        kryo.register(Boolean.class);
        kryo.register(boolean.class);
        kryo.register(ArrayList.class);
        //FINIRE THIS

    }

}
