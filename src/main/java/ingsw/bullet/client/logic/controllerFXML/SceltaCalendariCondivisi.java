package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;

import java.net.URL;
import java.util.ResourceBundle;

public class SceltaCalendariCondivisi extends SceltaGestoreAttivita {
    @Override
    public void mandaAGestoreAttivita(String nome) {
//        manda a CalendarioView !!! che vuole un calendario
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nomiGestoriAttivita = DBClient.getIstance().getElencoCalendariCondivisi(Profilo.email);
        super.initialize(url, resourceBundle);
    }
}
