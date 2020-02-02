package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.model.Partecipante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ingsw.bullet.model.Evento;


// set di evento prima
public class ElencoPartecipanti extends DialogCalendario{

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    Evento evento;

    @FXML
    private VBox elencoPartecipanti;

    @FXML
    void nonPartecipa(ActionEvent event) {
        Partecipante p = new Partecipante();
        p.setPresente(false);
        DBClient.getIstance().updatePartecipante(p);
    }

    @FXML
    void partecipa(ActionEvent event) {
// get partecipante by mail e evento id
        Partecipante p = new Partecipante();
        p.setPresente(true);
        DBClient.getIstance().updatePartecipante(p);
    }

    public void aggiungiPartecipante(Partecipante partecipante)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(partecipante.getEmail());
        if(partecipante.isPresente())
            stringBuilder.append(" presente");
        else
            stringBuilder.append(" non presente");

        elencoPartecipanti.getChildren().add(new Label(stringBuilder.toString()));
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento)
    {
        this.evento = evento;
    }
}
