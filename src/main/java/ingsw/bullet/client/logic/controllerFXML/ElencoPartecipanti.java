package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.model.Partecipante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ingsw.bullet.model.Evento;


public class ElencoPartecipanti extends DialogCalendario{

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    Partecipante partecipante;
    Evento evento;

    @FXML
    private VBox elencoPartecipanti;

    protected void setPartecipazione(Partecipante p, boolean partecipa)
    {
        p.setPresente(partecipa);
        DBClient.getIstance().updatePartecipanteEvento(p);
    }

    @FXML
    void nonPartecipa(ActionEvent event) {
        setPartecipazione(partecipante, false);
    }

    @FXML
    void partecipa(ActionEvent event) {
        setPartecipazione(partecipante,true);
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
        for(Partecipante p: evento.getPartecipanti())
        {
            aggiungiPartecipante(p);
            if(p.getEmail().equals(Profilo.email))
                partecipante = p;
        }
    }
}
