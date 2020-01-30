package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
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

    public static ArrayList<String> getGruppi() {
        return gruppi;
    }

    public static void setGruppi(ArrayList<String> gruppi) {
        GestireGruppi.gruppi = gruppi;
    }

    public ArrayList<String> getUtenti() {
        ArrayList<String> s = null;
        if(!elencoUtenti.getChildren().isEmpty()) {
            s = new ArrayList<String>();
            for (Node l:elencoUtenti.getChildren())
                if(l instanceof Button)
                    s.add(((Button) l).getText());
        }
        return s;
    }

    public void setUtenti(ArrayList<String> utenti) {
        elencoUtenti.getChildren().clear();
        for(String u:utenti)
        {
            aggiungiUtente(u);
        }
    }

    protected static ArrayList<String> gruppi;

    public void aggiungiGruppo(String gruppo)
    {
        if(!gruppo.contains(gruppo))
            gruppi.add(gruppo);
        Button l = new Button(gruppo);
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> membri = DBClient.getIstance().getElencoMembri(gruppo);

                elencoGruppi.getChildren().clear();

                for(String s:membri)
                {
                    aggiungiUtente(s);
                }

                nomeGruppo.setText(gruppo);
            }
        });
        elencoGruppi.getChildren().add(l);
    }

    public void rimuoviGruppo(String gruppo)
    {
        gruppi.remove(gruppo);
        elencoGruppi.getChildren().removeIf(new Predicate<Node>() {
            @Override
            public boolean test(Node node) {
                if(node instanceof Button)
                   return ((Button) node).getText().equals(gruppo);
                return false;
            }
        });
    }

    @FXML
    private VBox elencoGruppi;

    @FXML
    private VBox elencoUtenti;

    @FXML
    private Label nomeGruppo;

    public void aggiungiUtente(String utente)
    {
        Button l = new Button(utente);
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!DBClient.getIstance().rimuoviMembro(nomeGruppo.getText(), utente, Profilo.email))
                    new Alert(Alert.AlertType.ERROR, "Errore nel rimuovere membro");
                rimuoviUtente(l);
            }
        });
        elencoGruppi.getChildren().add(l);
    }

    public void rimuoviUtente(Button utente)
    {
        elencoUtenti.getChildren().remove(utente);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gruppi = DBClient.getIstance().getElencoGruppi(Profilo.email);

        if(gruppi!=null)
            if(!gruppi.isEmpty())
                for(String g:gruppi)
                {
                    aggiungiGruppo(g);
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


    @FXML
    void aggiungiMembro(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("email membro...");
        dialog.setContentText("Aggiungi membro gruppo: ");

        Optional<String> resp = dialog.showAndWait();
        if (resp.isPresent()){
            String email = resp.get();
            if(!DBClient.getIstance().esisteUtente(email))
                new Alert(Alert.AlertType.ERROR, "Non esiste questo utente!");
            aggiungiUtente(email);
        }
    }
}
