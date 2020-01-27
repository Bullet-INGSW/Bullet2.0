package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;

public class SceltaTDLCondivise extends SceltaGestoreAttivita {

    @Override
    public void mandaAGestoreAttivita(String nome) {
//     manda a to do list
        nomiGestoriAttivita = DBClient.getIstance().getElencoTDLCondivise(Profilo.email);
    }
}
