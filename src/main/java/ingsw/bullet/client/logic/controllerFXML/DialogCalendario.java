package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.calendar.Calendario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DialogCalendario {

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    Calendario calendario;

    protected void chiudi(ActionEvent event)
    {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void annulla(ActionEvent event) {
        chiudi(event);
    }
}
