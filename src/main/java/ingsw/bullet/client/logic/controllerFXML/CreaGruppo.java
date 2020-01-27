package ingsw.bullet.client.logic.controllerFXML;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


public class CreaGruppo {

    @FXML
    private TextField nomeGruppo;

    @FXML
    private TextField membroDaAggiungere;

    @FXML
    private VBox elencoMembri;

    @FXML
    void inserisciMembro(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER) && membroDaAggiungere.getText() != null)
        {
//            se c'e' nella base di dati!!!
            boolean iCanAdd = true;
            for(Node n:elencoMembri.getChildren())
            {
                if(n instanceof Button)
                {
                    if(((Button) n).getText().equals(membroDaAggiungere.getText())) {
                        iCanAdd = false;
                        break;
                    }
                }
            }
            if(iCanAdd) {
                Button l = new Button(membroDaAggiungere.getText());
                elencoMembri.getChildren().add(l);
                l.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        elencoMembri.getChildren().remove(l);
                    }
                });
            }
        }

    }

    public TextField getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(TextField nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
}
