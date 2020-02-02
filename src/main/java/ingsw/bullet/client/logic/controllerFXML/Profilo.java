package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.model.Notifica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Profilo extends ProfiloBase {

    @FXML
    private Circle imageProfile;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label cognomeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label sessoLabel;

    @FXML
    private VBox centroNotifiche;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        set values of profile --- nome cognome email sesso
        nomeLabel.setText(Profilo.nome);
        cognomeLabel.setText(Profilo.cognome);
        emailLabel.setText(Profilo.email);
        if(Profilo.sesso)
            sessoLabel.setText("Maschio");
        else
            sessoLabel.setText("Femmina");

        if(notifiche != null)
            for(Notifica notifica:notifiche) {
                Button button = new Button(notifica.getDescrizione());
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        centroNotifiche.getChildren().remove(button);
                        removeNotifica(notifica);
                    }
                });
                centroNotifiche.getChildren().add(button);
            }
        imageProfile.setFill(image);
        imageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }

    protected void removeNotifica(Notifica notifica)
    {
        DBClient.getIstance().removeNotifica(notifica.getIdNotifica());
        notifiche.remove(notifica);
    }

    @FXML
    void calendariCondivisi(ActionEvent event) {

    }

    @FXML
    void calendarioPersonale(ActionEvent event) {

    }

    @FXML
    void gestireGruppi(ActionEvent event) {

    }

    @FXML
    void tdlCondivise(ActionEvent event) {

    }

    @FXML
    void tdlPersonale(ActionEvent event) {

    }
}
