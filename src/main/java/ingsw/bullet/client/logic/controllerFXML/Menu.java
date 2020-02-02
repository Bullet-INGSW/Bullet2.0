package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Notifica;
import ingsw.bullet.model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Menu {

    @FXML
    private TextField loginEmail;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField regEmail;

    @FXML
    private TextField regNome;

    @FXML
    private TextField regCognome;

    @FXML
    private MenuButton regSesso;

    @FXML
    private PasswordField regPassword;

    @FXML
    void loginConferma(ActionEvent event) {
        System.out.print(loginEmail.getText());
        if((loginEmail.getText()==null || loginEmail.getText().equals("")) ||
                (loginPassword.getText()==null || loginPassword.getText().equals("")) )
        {
            new Alert(Alert.AlertType.ERROR, "Inserisci email e password per fare il login").showAndWait();
            return;
        }

        Utente u = DBClient.getIstance().findUtenteByEmail(loginEmail.getText());
        if(u == null)
        {
            new Alert(Alert.AlertType.ERROR, "Non sei iscritto con questa email").showAndWait();
            return;
        }

        if(!u.getPassword().equals(loginPassword.getText()))
        {
            new Alert(Alert.AlertType.ERROR, "Password errata").showAndWait();
            return;
        }

        Profilo.email = u.getEmail();
        Profilo.nome = u.getNome();
        Profilo.cognome = u.getCognome();
        if(u.getSesso() == Utente.Sesso.M)
            Profilo.sesso = true;
        else
            Profilo.sesso = false;
        Profilo.notifiche = (ArrayList<Notifica>)u.getNotifiche();
        Main.getInstance().replaceSceneContent("profilo", Main.getInstance().stage, 600, 400);
    }

    @FXML
    void regConferma(ActionEvent event) {
        if((regEmail.getText()==null || regEmail.getText().equals(""))
                ||
            (regPassword.getText()==null || regPassword.getText().equals(""))
            ||
            (regNome.getText()==null || regNome.getText().equals(""))
            ||
            (regCognome.getText()==null || regCognome.getText().equals(""))
            ||
            (regSesso.getText().equals("---"))
        )
        {
            new Alert(Alert.AlertType.ERROR, "Inserire i dati mancanti per registrarsi").showAndWait();
            return;
        }

        Utente u = DBClient.getIstance().findUtenteByEmail(regEmail.getText());
        if(u != null)
        {
            new Alert(Alert.AlertType.ERROR, "Sei gia' iscritto con questa mail").showAndWait();
            return;
        }

        u = new Utente();
        u.setEmail(regEmail.getText());
        u.setNome(regNome.getText());
        u.setCognome(regCognome.getText());
        u.setPassword(regPassword.getText());
        if(regSesso.getText().equals("Maschio"))
            u.setSesso(Utente.Sesso.M);
        else
            u.setSesso(Utente.Sesso.F);

        DBClient.getIstance().insertUtente(u);

        Main.getInstance().replaceSceneContent("profilo", Main.getInstance().stage, 600, 400);
    }

}
