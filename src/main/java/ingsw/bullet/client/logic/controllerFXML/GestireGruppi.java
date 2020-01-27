package ingsw.bullet.client.logic.controllerFXML;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
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
        elencoGruppi.getChildren().add(l);
        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rimuoviUtente(l);
            }
        });
    }

    public void rimuoviUtente(Button utente)
    {
        elencoUtenti.getChildren().remove(utente);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(gruppi!=null)
            if(!gruppi.isEmpty())
                for(String g:gruppi)
                {
                    aggiungiGruppo(g);
                }
    }

}
