package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Gruppo;
import ingsw.bullet.model.Membro;
import ingsw.bullet.model.Utente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


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

            if(DBClient.getIstance().findUtenteByEmail(membroDaAggiungere.getText()) != null) {
                new Alert(Alert.AlertType.ERROR, "Non è presente questo utente").showAndWait();
                return;
            }
            for(Node n:elencoMembri.getChildren())
                if(n instanceof Button)
                    if(((Button) n).getText().equals(membroDaAggiungere.getText()))
                        return;

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

    public TextField getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(TextField nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
    @FXML
    void conferma(ActionEvent event) {
        if(nomeGruppo.getText() != null) {

                ArrayList<String> membri = getElencoMembri();
                if(membri.isEmpty())
                    new Alert(Alert.AlertType.ERROR, "Non hai aggiunto nessun membro");

                Gruppo g = new Gruppo();
                g.setNome(nomeGruppo.getText());
                g = DBClient.getIstance().insertGruppo(g); //creaGruppo(Profilo.email, nomeGruppo.getText(),getElencoMembri());


                Utente utente = DBClient.getIstance().findUtenteByEmail(Profilo.email);
                Membro amministratore = new Membro();
                amministratore.setEmail(utente.getEmail());
                amministratore.setAdmin(true);
                amministratore.setIdGruppo(g.getIdGruppo());
                DBClient.getIstance().insertMembro(amministratore);

                for(String email:getElencoMembri())
                {
                    Membro membro = new Membro();
                    membro.setEmail(email);
                    membro.setAdmin(true);
                    membro.setIdGruppo(g.getIdGruppo());
                    DBClient.getIstance().insertMembro(membro);
                }

//              va in gestire gruppi
            Main.getInstance().replaceSceneContent("gestireGruppi", Main.getInstance().stage, 600, 400);
        }
        else
            new Alert(Alert.AlertType.ERROR, "Inserire Nome Gruppo");
    }

    @FXML
    void annulla(ActionEvent event) {

    }

    ArrayList<String> getElencoMembri()
    {
        ArrayList<String> s = null;
        for(Node n:elencoMembri.getChildren())
        {
            s = new ArrayList<>();
            if(n instanceof Label)
                s.add(((Label) n).getText());
        }
        return s;
    }
}
