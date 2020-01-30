package ingsw.bullet.client.logic.calendar;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.EntryViewBase;
import com.calendarfx.view.Messages;
import ingsw.bullet.client.logic.controllerFXML.NuoveEtichette;
import ingsw.bullet.client.view.LoaderFXMLStage;
import ingsw.bullet.client.view.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

// gestisce la creazione di un calendario personale
// gestisce l'evento se un evento/etichetta e' stato aggiunto/a o modificato/a

public class Calendario extends CalendarView {

    ArrayList<Evento> eventi = new ArrayList<>();
    ArrayList<Etichetta> etichette = new ArrayList<>();
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
    }

    public Calendario(String nome){
        super();
        this.nome = nome;
    }

    public Calendario(ArrayList<Evento> eventi, ArrayList<Etichetta> etichette, String nome) {
        super();
        this.eventi = eventi;
        this.etichette = etichette;
        this.nome = nome;
    }

    protected void setOnEntryEvent()
    {
        setEntryContextMenuCallback(entryContextMenuParameter ->
        {
            EntryViewBase<?> entryView = entryContextMenuParameter.getEntryView();
            Entry<?> entry = entryView.getEntry();
            DateControl dateControl = entryView.getDateControl();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem informationItem = new MenuItem(Messages.getString("DateControl.MENU_ITEM_INFORMATION"));
            informationItem.setOnAction((evt) ->
            {
                Callback<EntryDetailsParameter, Boolean> detailsCallback = dateControl.getEntryDetailsCallback();
                if (detailsCallback != null)
                {
                    ContextMenuEvent ctxEvent = entryContextMenuParameter.getContextMenuEvent();
                    DateControl.EntryDetailsParameter entryDetailsParam = new DateControl.EntryDetailsParameter(ctxEvent, dateControl, entryView.getEntry(), dateControl, ctxEvent.getScreenX(), ctxEvent.getScreenY());
                    detailsCallback.call(entryDetailsParam);
                }
            });
            contextMenu.getItems().add(informationItem);
            String stylesheet = CalendarView.class.getResource("calendar.css").toExternalForm();
            Menu calendarMenu = new Menu(Messages.getString("DateControl.MENU_CALENDAR"));
            Iterator var8 = dateControl.getCalendars().iterator();

            while(var8.hasNext()) {
                Calendar calendar = (Calendar)var8.next();
                RadioMenuItem calendarItem = new RadioMenuItem(calendar.getName());
                calendarItem.setOnAction((evt) -> {
                    entry.setCalendar(calendar);
                });
                calendarItem.setDisable(calendar.isReadOnly());
                calendarItem.setSelected(calendar.equals(entryContextMenuParameter.getCalendar()));
                calendarMenu.getItems().add(calendarItem);
                StackPane graphic = new StackPane();
                graphic.getStylesheets().add(stylesheet);
                Rectangle icon = new Rectangle(10.0D, 10.0D);
                icon.setArcHeight(2.0D);
                icon.setArcWidth(2.0D);
                icon.getStyleClass().setAll(new String[]{calendar.getStyle() + "-icon"});
                graphic.getChildren().add(icon);
                calendarItem.setGraphic(graphic);
            }

            calendarMenu.setDisable(entryContextMenuParameter.getCalendar().isReadOnly());
            contextMenu.getItems().add(calendarMenu);
            if ((Boolean)dateControl.getEntryEditPolicy().call(new DateControl.EntryEditParameter(dateControl, entry, DateControl.EditOperation.DELETE))) {
                MenuItem delete = new MenuItem(Messages.getString("DateControl.MENU_ITEM_DELETE"));
                contextMenu.getItems().add(delete);
                delete.setDisable(entryContextMenuParameter.getCalendar().isReadOnly());
                delete.setOnAction((evt) -> {
                    Calendar calendar = entry.getCalendar();
                    if (!calendar.isReadOnly()) {
                        if (entry.isRecurrence()) {
                            entry.getRecurrenceSourceEntry().removeFromCalendar();
                        } else {
                            entry.removeFromCalendar();
                        }
                    }

                });
            }

            contextMenu.getItems().add(new MenuItem("non solo"));

            return contextMenu;
        });
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
                            ((Button) n).setOnAction(evt ->
                                    {
                                        FXMLLoader fxmlLoader = Main.getInstance().replaceSceneContent
                                                ("aggiungiEtichette",new Stage(), 200, 200);
                                        NuoveEtichette nuoveEtichette = fxmlLoader.<NuoveEtichette>getController();
                                        nuoveEtichette.setCalendario(this);
                                    }
                            );
                    }

            }
        });
    }

    public void aggiungiEvento()
    {

    }

    public void modificaEvento()
    {

    }

    public void rimuoviEvento()
    {

    }

    public void aggiungiEtichetta(String nome, int num)
    {
        Calendar calendar = new Calendar(nome);
        calendar.setStyle(Calendar.Style.getStyle(num));

        myCalendarSource.getCalendars().add(calendar);
        // aggiungi al db
    }

    public void rimuoviEtichetta()
    {

    }
}
