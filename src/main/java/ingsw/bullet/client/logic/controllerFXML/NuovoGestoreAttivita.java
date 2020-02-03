package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Gruppo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class NuovoGestoreAttivita implements Initializable {

    @FXML
    protected TextField nomeGestoreAttivita;

    @FXML
    protected TextField nomeGruppo;

    @FXML
    protected AnchorPane gruppi;

    protected int gruppoId = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Gruppo> gruppi = (ArrayList<Gruppo>)DBClient.getIstance().findGroupByEmail(Profilo.email);
        for(Gruppo gruppo:gruppi)
        {
            Button b = new Button(gruppo.getNome());
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nomeGruppo.setText(gruppo.getNome());
                    gruppoId = gruppo.getIdGruppo();
                }
            });
        }
    }

    public abstract void aggiungiGestoreAttivita();

    @FXML
    void conferma(ActionEvent event) {
        if(nomeGruppo.getText() == null || nomeGruppo.getText().equals(""))
        {
            new Alert(Alert.AlertType.ERROR, "Devi inserire il nome del gruppo").showAndWait();
            return;
        }

        if(nomeGestoreAttivita.getText() == null || nomeGestoreAttivita.getText().equals(""))
        {
            new Alert(Alert.AlertType.ERROR, "Devi inserire il nome").showAndWait();
            return;
        }

        aggiungiGestoreAttivita();
    }

    @FXML
    void indietro(ActionEvent event) {
        Main.getInstance().replaceSceneContent("sceltaCalendariCondivisi", (Stage)nomeGestoreAttivita.getScene().getWindow(), 800, 600);
    }
}
