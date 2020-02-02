package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.GestoreAttivita;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class SceltaGestoreAttivita implements Initializable {

    @FXML
    protected FlowPane gestoreAttivitaCondivise;

    protected static ArrayList<GestoreAttivita> nomiGestoriAttivita = null;


    @FXML
    void indietro(ActionEvent event) {
        Main.getInstance().replaceSceneContent("profilo", Main.getInstance().stage, 600, 400);
    }

    @FXML
    public abstract void nuovoGestoreAttivita(ActionEvent event);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(nomiGestoriAttivita != null)
            for(GestoreAttivita n:nomiGestoriAttivita)
            {
                Button b = new Button(n.getNome());
                b.setPrefSize(90,90);
                gestoreAttivitaCondivise.getChildren().add(b);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(n.getNome());
                        mandaAGestoreAttivita(n);
                    }
                });
            }
    }

    public void aggiungiGestoreAttivita(GestoreAttivita gestoreAttivita)
    {
        if(nomiGestoriAttivita == null)
            nomiGestoriAttivita = new ArrayList<GestoreAttivita>();

        nomiGestoriAttivita.add(gestoreAttivita);
    }

    public abstract void mandaAGestoreAttivita(GestoreAttivita gestoreAttivita);
}
