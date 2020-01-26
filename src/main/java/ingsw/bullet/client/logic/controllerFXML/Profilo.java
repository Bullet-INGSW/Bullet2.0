package ingsw.bullet.client.logic.controllerFXML;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
//      create Label throw notifiche (ArrayList<String>) and set Action of Mouse Clicked (delete the Label if it touch)
        if(notifiche != null)
            for(String notifica:notifiche) {
                Label label = new Label(notifica);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        centroNotifiche.getChildren().remove(label);
                    }
                });
                centroNotifiche.getChildren().add(label);
            }
        imageProfile.setFill(image);
        imageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }
}
