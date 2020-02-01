package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.calendar.CalendarioView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DialogCalendario {

    public CalendarioView getCalendarioView() {
        return calendarioView;
    }

    public void setCalendarioView(CalendarioView calendarioView) {
        this.calendarioView = calendarioView;
    }

    CalendarioView calendarioView;

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
