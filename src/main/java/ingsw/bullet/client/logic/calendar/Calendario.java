package ingsw.bullet.client.logic.calendar;

import com.calendarfx.model.*;
import com.calendarfx.view.*;
import ingsw.bullet.client.logic.controllerFXML.DialogCalendario;
import ingsw.bullet.client.logic.controllerFXML.EliminaEtichetta;
import ingsw.bullet.client.logic.controllerFXML.NuoveEtichette;
import ingsw.bullet.client.view.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.MessageFormat;
import java.time.*;
import java.util.HashMap;

// gestisce la creazione di un calendario personale
// gestisce l'evento se un evento/etichetta e' stato aggiunto/a o modificato/a

public class Calendario extends CalendarView {

    protected int countEntry = 0;

    HashMap<Entry<?>, Evento> eventi = new HashMap<>();
    HashMap<Calendar, Etichetta> etichette = new HashMap<>();
    String nome;
    CalendarSource myCalendarSource;

    {
        setOnEntryCalendar();
        setOnEntryEvent();
        myCalendarSource = new CalendarSource("Etichette");
        getCalendarSources().addAll(myCalendarSource);
        setRequestedTime(LocalTime.now());
        Platform.runLater(() -> {
            setToday(LocalDate.now());
            setTime(LocalTime.now());
        });
        setOnActionNewEntry();
        setOnUpdateEntry();
    }

    public Calendario(String nome){
        super();
        this.nome = nome;
    }

    protected void setOnUpdateEntry()
    {
        for(CalendarSource calendarSource: getCalendarSources())
        {
            for(Calendar calendar:calendarSource.getCalendars())
            {
                calendar.addEventHandler(new EventHandler<CalendarEvent>() {
                    @Override
                    public void handle(CalendarEvent event) {
                        System.out.println(event.getEntry());
                        modificaEvento(event.getEntry(), event);
                    }
                });
            }
        }
    }

    protected void setOnActionNewEntry()
    {
        setEntryFactory((param) -> {
            DateControl control = param.getDateControl();
            VirtualGrid grid = control.getVirtualGrid();
            ZonedDateTime time = param.getZonedDateTime();
            DayOfWeek firstDayOfWeek = this.getFirstDayOfWeek();
            ZonedDateTime lowerTime = grid.adjustTime(time, false, firstDayOfWeek);
            ZonedDateTime upperTime = grid.adjustTime(time, true, firstDayOfWeek);
            if (Duration.between(time, lowerTime).abs().minus(Duration.between(time, upperTime).abs()).isNegative()) {
                time = lowerTime;
            } else {
                time = upperTime;
            }

            Entry<Object> entry = new Entry(MessageFormat.format(Messages.getString("DateControl.DEFAULT_ENTRY_TITLE"), countEntry++));
            Interval interval = new Interval(time.toLocalDateTime(), time.toLocalDateTime().plusHours(1L));
            entry.setInterval(interval);
            if (control instanceof AllDayView) {
                entry.setFullDay(true);
            }
            aggiungiEvento(entry);

            return entry;
        });

    }

    protected void setOnEntryEvent()
    {

    }

    protected void setOnEntryCalendar()
    {
        Entry<String> e = new Entry<>("we");
        Platform.runLater(() -> {
            setToday(LocalDate.now());
            setTime(LocalTime.now());
            if (getSearchField().getParent() instanceof GridPane) {
                GridPane gp = (GridPane) getSearchField().getParent();
                for (Node c : gp.getChildren())
                    if (c instanceof HBox)
                    {
                        Node n = ((HBox) c).getChildren().get(1);
                        if (n instanceof Button)
                            ((Button) n).setOnAction(evt -> loadDialogCalendario("aggiungiEtichette", 200, 200));
                        Button delete = new Button("-");
                        ((HBox)c).getChildren().add(1,delete);
                        delete.setOnAction(evt ->loadDialogCalendario("eliminaEtichetta", 400, 200));
                    }

            }
        });
    }

    protected DialogCalendario loadDialogCalendario(String nome, int width, int height)
    {
        FXMLLoader fxmlLoader = Main.getInstance().replaceSceneContent
                (nome, new Stage(), width, height);
        DialogCalendario dialogCalendario = fxmlLoader.<DialogCalendario>getController();
        dialogCalendario.setCalendario(this);
        return dialogCalendario;
    }

    public void aggiungiEvento(Entry<?> entry)
    {
        System.out.println("Aggiungi Evento");
    }

    public void modificaEvento(Entry<?> entry, CalendarEvent event)
    {
        System.out.println("Modifica Evento ");
    }

    public void rimuoviEvento(Entry<?> entry)
    {
        System.out.println("Elimina Evento");
    }

    public void aggiungiEtichetta(String nome, int num)
    {
        Calendar calendar = new Calendar(nome);
        calendar.setStyle(Calendar.Style.getStyle(num));

        myCalendarSource.getCalendars().add(calendar);
        // aggiungi al db
    }

    public void rimuoviEtichetta(Calendar c)
    {
        myCalendarSource.getCalendars().remove(c);
        // rimuovi dal db
    }
}
