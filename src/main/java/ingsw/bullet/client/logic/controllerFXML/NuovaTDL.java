package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.model.TDL;

public class NuovaTDL extends NuovoGestoreAttivita {

    @Override
    public void aggiungiGestoreAttivita() {
        TDL c = new TDL();
        c.setNome(nomeGestoreAttivita.getText());
        c.setIdGruppo(gruppoId);
       // DBClient.getIstance().insertTDL(c);

      // Main.getInstance().replaceSceneContent("sceltaTDLCondivise", Main.getInstance().stage, 800, 600);
    }
}
