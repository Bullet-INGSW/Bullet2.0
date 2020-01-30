package ingsw.bullet.client.logic.controllerFXML;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import ingsw.bullet.client.logic.calendar.Calendario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class EliminaEtichetta extends DialogCalendario{

    Calendar etichettaScelta = null;

    @FXML
    private Label selezioneEtichetta;

    @FXML
    private FlowPane elencoEtichette;

    CalendarSource calendarSource = null;

    public void setCalendario(Calendario calendario)
    {
        super.setCalendario(calendario);
        calendarSource = calendario.getCalendarSources().get(1);
        for(Calendar c:calendarSource.getCalendars())
        {
            Button b = new Button(c.getName());
            b.setOnAction(evt ->
            {
                selezioneEtichetta.setText(b.getText());
                etichettaScelta = c;
            });
            elencoEtichette.getChildren().add(b);
        }
    }

    @FXML
    void eliminaEtichetta(ActionEvent event) {
        if(etichettaScelta!=null)
            calendario.rimuoviEtichetta(etichettaScelta);
    }
}
