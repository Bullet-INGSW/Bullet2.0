package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.model.GestoreAttivita;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SceltaTDLCondivise extends SceltaGestoreAttivita {

    @Override
    public void mandaAGestoreAttivita(GestoreAttivita gestoreAttivita){
//        if(gestoreAttivita instanceof TDL)
//        {
//            Main.getInstance().stage.close();
//            Scene scene = new Scene(new ingsw.bullet.client.logic.tdl.TDL((TDL)gestoreAttivita));
//            Main.getInstance().stage.setScene(scene);
//            Main.getInstance().stage.show();
//        }
    }

    @Override
    public void nuovoGestoreAttivita(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        nomiGestoriAttivita = new ArrayList<>(DBClient.getIstance().findTDLCondiviseByEmail(Profilo.email));
        super.initialize(url, resourceBundle);
    }
}
