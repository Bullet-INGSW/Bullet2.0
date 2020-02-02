package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Calendario;

public class NuovoCalendario extends NuovoGestoreAttivita{
    @Override
    public void aggiungiGestoreAttivita() {
        Calendario c = new Calendario();
        c.setNome(nomeGestoreAttivita.getText());
        c.setIdGruppo(gruppoId);
        DBClient.getIstance().insertCalendario(c);

        Main.getInstance().replaceSceneContent("sceltaCalendariCondivisi", Main.getInstance().stage, 800, 600);
    }
}
