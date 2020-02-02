package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.model.Gruppo;
import ingsw.bullet.model.Membro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class GestireGruppi implements Initializable {

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    protected static ArrayList<Gruppo> gruppi;

    @FXML
    private VBox elencoGruppi;

    @FXML
    private VBox elencoMembri;

    @FXML
    private Label nomeGruppo;

    // aggiunge un bottone per ogni gruppo
    public void aggiungiGruppo(Gruppo gruppo) {
        if (!gruppi.contains(gruppo))
            gruppi.add(gruppo);
        Button l = new Button(gruppo.getNome());
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Membro> membri = (ArrayList<Membro>) gruppo.getMembri();
                elencoGruppi.getChildren().clear();

                for (Membro s : membri) {
                    aggiungiBottoneMembro(s);
                }

                nomeGruppo.setText(gruppo.getNome());
            }
        });
        elencoGruppi.getChildren().add(l);
    }

    public void rimuoviGruppo(Gruppo gruppo) {
        gruppi.remove(gruppo);
        elencoGruppi.getChildren().removeIf(new Predicate<Node>() {
            @Override
            public boolean test(Node node) {
                if (node instanceof Button)
                    return ((Button) node).getText().equals(gruppo.getIdGruppo());
                return false;
            }
        });
        DBClient.getIstance().removeGruppo(gruppo.getIdGruppo());
    }

    public void aggiungiBottoneMembro(Membro utente) {
        Button l = new Button(utente.getEmail());
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (!utente.isAdmin())
//                    if (DBClient.getIstance().removeMembro(utente.getIdGruppo()) == null)
//                        new Alert(Alert.AlertType.ERROR, "Errore nel rimuovere membro");
                rimuoviMembro(l);
            }
        });
        elencoMembri.getChildren().add(l);
    }

    public void rimuoviMembro(Button utente) {
        elencoMembri.getChildren().remove(utente);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // gruppi = DBClient.getIstance().findUtenteByEmail(Profilo.email);

        if (gruppi != null)
            if (!gruppi.isEmpty())
                for (Gruppo g : gruppi) {
                    aggiungiGruppo(g);
                }
    }


    @FXML
    void aggiungiMembro(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("email membro...");
        dialog.setContentText("Aggiungi membro gruppo: ");

        Optional<String> resp = dialog.showAndWait();
        if (resp.isPresent()) {
            String email = resp.get();
            if (DBClient.getIstance().findUtenteByEmail(email) != null)
                new Alert(Alert.AlertType.ERROR, "Non esiste questo utente!");
            //    aggiungiUtente(DBClient.getIstance().findMembroById(email);
        }
    }

    @FXML
    void creaNuovoGruppo(ActionEvent event) {
//      manda in creaGruppo
    }

    @FXML
    void indietro(ActionEvent event) {
//      manda in profilo
    }

}
