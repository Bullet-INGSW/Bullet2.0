package ingsw.bullet.client.ConnectionObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Richiesta {


    public enum TipoRichiesta{aggiungiMembro,esisteUtente,creaGruppo, getElencoGruppi,
        getElencoMembri,setMembroGruppo,getUtente,insertUtente,rimuoviMembro,login,
        getNotifiche,setNotifiche,aggiungiNotifica,aggiungiNotificheAlGruppo, getElencoCalendariCondivisi,getElencoTDLCondivise,
        utentiConNomiRegex,esisteGruppo


    };
    TipoRichiesta tipo=null;
    HashMap<String,String> valori=null;
    ArrayList<String> array=null;

    public  Richiesta(TipoRichiesta tipo,  HashMap<String,String> valori){
        this.tipo=tipo;
        this.valori=valori;
    }

    public  Richiesta(TipoRichiesta tipo,  HashMap<String,String> valori, ArrayList<String>membri){
        this.tipo=tipo;
        this.valori=valori;
        this.array=membri;
    }

}
