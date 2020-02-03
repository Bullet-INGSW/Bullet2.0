package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.logic.calendar.CalendarioView;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Calendario;
import ingsw.bullet.model.Membro;
import ingsw.bullet.model.Notifica;
import ingsw.bullet.model.TDL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
        Main.getInstance().replaceSceneContent("sceltaCalendariCondivisi", Main.getInstance().stage, 600, 400);
    }

    @FXML
    void calendarioPersonale(ActionEvent event) {
        Main.getInstance().stage.close();
        Scene scene = new Scene(new CalendarioView(ottieniCalendarioPersonale()));
        ((Stage)emailLabel.getScene().getWindow()).setScene(scene);
    }

    @FXML
    void gestireGruppi(ActionEvent event) {
        Main.getInstance().replaceSceneContent("gestireGruppi", Main.getInstance().stage, 600, 400);
    }

    @FXML
    void tdlCondivise(ActionEvent event) {
        Main.getInstance().replaceSceneContent("sceltaToDoListCondivise", Main.getInstance().stage, 600, 400);
    }

    @FXML
    void tdlPersonale(ActionEvent event) {
//        Main.getInstance().stage.close();
//        Scene scene = new Scene(new TDLView(ottieniTDLPersonale()));
//        Main.getInstance().stage.setScene(scene);
//        Main.getInstance().stage.show();
    }


    @FXML
    void esci(ActionEvent event) {
        logout();
        Main.getInstance().replaceSceneContent("menu",(Stage)emailLabel.getScene().getWindow(), 600, 400);
    }

    public void logout()
    {
        nome = "Nome";
        cognome = "Cognome";
        email = "Email";
        sesso = true;
    }

    Calendario ottieniCalendarioPersonale()
    {
        Calendario calendario = DBClient.getIstance().findCalendarioPersonaleByEmail(Profilo.email);
        if(calendario == null)
        {
            calendario = new Calendario();
            calendario.setNome("Calendario Personale");
            calendario.setEmail(email);
            DBClient.getIstance().insertCalendario(calendario);
            System.out.println("CALENDARIO MA IN PROFILO: " + calendario);
        }
        return calendario;
    }

    TDL ottieniTDLPersonale()
    {
        TDL tdl = DBClient.getIstance().findTDLPersonaleByEmail(Profilo.email);
        if(tdl == null)
        {
            tdl = new TDL();
            tdl.setNome("TDL Personale");
            tdl.setEmail(email);
            DBClient.getIstance().insertTDL(tdl);
        }
        return tdl;
    }
}
