package ingsw.bullet.client.logic.controllerFXML;

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
    void indietro(ActionEvent event) {
//      torna a profilo
    }

    public static void setNomeGestoriCondivisi(ArrayList<String> nomeGestoriCondivisi) {
        SceltaGestoreAttivita.nomiGestoriAttivita = nomeGestoriCondivisi;
    }


    @FXML
    protected FlowPane gestoreAttivitaCondivise;

    public static ArrayList<String> getNomeGestoriCondivisi() {
        return nomiGestoriAttivita;
    }

    protected static ArrayList<String> nomiGestoriAttivita = null;

    public static void aggiungiGestoreAttivita(String gestoreAttivita)
    {
        if(nomiGestoriAttivita == null)
            nomiGestoriAttivita = new ArrayList<String>();

        nomiGestoriAttivita.add(gestoreAttivita);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(nomiGestoriAttivita != null)
            for(String n:nomiGestoriAttivita)
            {
                Button b = new Button(n);
                b.setPrefSize(90,90);
                gestoreAttivitaCondivise.getChildren().add(b);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(n);
                        mandaAGestoreAttivita(n);
                    }
                });
            }
    }

    public abstract void mandaAGestoreAttivita(String nome);
}
