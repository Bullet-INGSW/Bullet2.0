package ingsw.bullet.client.logic.controllerFXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AggiungiPromemoria {

    @FXML
    private TextField nomePromemoria;

    @FXML
    private TextArea descrizionePromemoria;

    @FXML
    private RadioButton scadenzaPromemoria;

    @FXML
    private Button etichettaPromemoria;

    @FXML
    private Button indietro;

    @FXML
    private Button conferma;

    @FXML
    void aggiungiScadenza(ActionEvent event) {

    }

    @FXML
    void associaEtichetta(ActionEvent event) {

    }

    @FXML
    void salvaPromemoria(ActionEvent event) {

    }

    @FXML
    void tornaIndietro(ActionEvent event) {
        //torno alla TDL personale
    }

}

