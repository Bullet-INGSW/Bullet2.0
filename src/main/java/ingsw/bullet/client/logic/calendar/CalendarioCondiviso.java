package ingsw.bullet.client.logic.calendar;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.EntryViewBase;
import com.calendarfx.view.Messages;
import ingsw.bullet.client.logic.controllerFXML.DialogCalendario;
import ingsw.bullet.client.logic.controllerFXML.ElencoPartecipanti;
import ingsw.bullet.client.logic.controllerFXML.EliminaEtichetta;
import javafx.application.Platform;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

public class CalendarioCondiviso extends Calendario {
    public CalendarioCondiviso(String nome) {
        super(nome);
    }

    @Override
    protected void setOnEntryEvent() {
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
                    rimuoviEvento(entry);
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

            MenuItem partecipantiMenu = new MenuItem("Partecipanti");
            partecipantiMenu.setOnAction(actionEvent ->{
                DialogCalendario d = loadDialogCalendario("elencoPartecipanti", 400, 400);

                Evento e = eventi.get(entry);
                if(e instanceof EventoCondiviso)
                {
                    EventoCondiviso ec = (EventoCondiviso)e;
                    if(d instanceof ElencoPartecipanti)
                    {
                        ((ElencoPartecipanti) d).setEvento(ec);
                    }
                }
            });
            contextMenu.getItems().add(partecipantiMenu);

            return contextMenu;
        });
    }
}
