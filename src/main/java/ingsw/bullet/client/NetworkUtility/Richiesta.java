package ingsw.bullet.client.NetworkUtility;

import java.util.ArrayList;
import java.util.HashMap;

public class Richiesta {


    public enum TipoRichiesta{aggiungiMembro,esisteUtente,creaGruppo, getElencoGruppi,
        getElencoMembri,setMembriGruppo,getUtente,insertUtente,rimuoviMembro,login,
        getNotifiche,setNotifiche,aggiungiNotifica,aggiungiNotificheAlGruppo, getElencoCalendariCondivisi,getElencoTDLCondivise,
        utentiConNomiRegex,esisteGruppo


    };

    public TipoRichiesta getTipo() {
        return tipo;
    }

    public void setTipo(TipoRichiesta tipo) {
        this.tipo = tipo;
    }

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
