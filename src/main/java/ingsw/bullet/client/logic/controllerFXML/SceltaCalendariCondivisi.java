package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.logic.calendar.CalendarioView;
import ingsw.bullet.client.logic.calendar.CalendarioViewCondiviso;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Calendario;
import ingsw.bullet.model.GestoreAttivita;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SceltaCalendariCondivisi extends SceltaGestoreAttivita {
    @Override
    public void mandaAGestoreAttivita(GestoreAttivita gestoreAttivita) {
        if(gestoreAttivita instanceof Calendario)
        {
            Main.getInstance().stage.close();
            Scene scene = new Scene(new CalendarioViewCondiviso((Calendario)gestoreAttivita));
            ((Stage)gestoreAttivitaCondivise.getScene().getWindow()).setScene(scene);
        }
    }

    @Override
    public void nuovoGestoreAttivita(ActionEvent event) {
        Main.getInstance().replaceSceneContent("nuovoCalendario",
                (Stage)gestoreAttivitaCondivise.getScene().getWindow(), 800, 600);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Calendario> c = DBClient.getIstance().findCalendariCondivisiByEmail(Profilo.email);;

        if(c != null)
        for(Calendario calendario:c)
        {
            if(nomiGestoriAttivita == null)
                nomiGestoriAttivita = new ArrayList<>();
            nomiGestoriAttivita.add(calendario);
        }
        super.initialize(url, resourceBundle);
    }
}
