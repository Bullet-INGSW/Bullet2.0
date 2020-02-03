package ingsw.bullet.client.logic.calendar;

import com.calendarfx.model.*;
import com.calendarfx.view.*;
import ingsw.bullet.client.logic.DBClient;
import ingsw.bullet.client.logic.controllerFXML.DialogCalendario;
import ingsw.bullet.client.view.Main;
import ingsw.bullet.model.Calendario;
import ingsw.bullet.model.Etichetta;
import ingsw.bullet.model.Evento;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.time.*;
import java.util.HashMap;

// gestisce la creazione di un calendario personale
// gestisce l'evento se un evento/etichetta e' stato aggiunto/a o modificato/a

public class CalendarioView extends CalendarView {

    protected int countEntry = 0;

    HashMap<Entry<?>, Evento> eventi = new HashMap<>();
    HashMap<Calendar, Etichetta> etichette = new HashMap<>();

    HashMap<Calendar.Style, Integer> coloriMap =new HashMap<>(){ {
        put(Calendar.Style.STYLE1 ,0);
        put(Calendar.Style.STYLE2, 1);
        put(Calendar.Style.STYLE3, 2);
        put(Calendar.Style.STYLE4, 3);
        put(Calendar.Style.STYLE5, 4);
        put(Calendar.Style.STYLE6, 5);
        put(Calendar.Style.STYLE7, 6);
    }};

    Calendario calendario;
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


    public CalendarioView(Calendario calendario){
        super();
        this.calendario = calendario;

        Etichetta calendario1 = new Etichetta();
        calendario1.setNome(getCalendarSources().get(0).getCalendars().get(0).getName());
        calendario1.setColore(coloriMap.get(Calendar.Style.STYLE1));
        calendario1.setIdEtichetta(1);
        etichette.put(getCalendarSources().get(0).getCalendars().get(0),calendario1);

        for(Etichetta e:calendario.getEtichette())
        {
            Calendar c = new Calendar(e.getNome());
            c.setStyle(Calendar.Style.getStyle(e.getColore()));
            myCalendarSource.getCalendars().add(c);
            etichette.put(c, e);
        }

        for(Evento e:calendario.getEventi())
        {
            Entry<String> entry = createEntryByEvento(e);
            eventi.put(entry, e);
        }
    }

    protected Entry<String> createEntryByEvento(@NotNull Evento evento)
    {
        Entry<String> entry = new Entry<>(evento.getNome());

        if(evento.getDescrizione() != null)
            entry.setLocation(evento.getDescrizione());

        entry.setId(""+evento.getIdEvento());
        if(countEntry < evento.getIdEvento())
            countEntry = evento.getIdEvento() + 1;

        entry.setInterval(evento.getDataInizio(), evento.getDataFine());

        Calendar c = getCalendarSources().get(0).getCalendars().get(0);

        for(Etichetta etichetta:etichette.values())
            if(etichetta.getIdEtichetta() == evento.getIdEvento()) {
                for (Calendar calendar : etichette.keySet())
                    c = calendar;
                break;
            }

        entry.setCalendar(c);
        c.addEntry(entry);

        entry.setFullDay(evento.isFullDay());

        if(evento.getPeriodicita()) {
            entry.setRecurrenceRule(evento.getRecurrenceRule());
        }
        return entry;
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
            if(!controlliPermessi())
                return null;
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
            System.out.println(getCalendarSources().get(0).getCalendars().get(0));
            aggiungiEvento(entry, getCalendarSources().get(0).getCalendars().get(0), false);

            return entry;
        });

    }

    protected void setOnEntryEvent()
    {

    }

    protected void setOnEntryCalendar()
    {
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

                        Button indietro = new Button("Indietro");
                        ((HBox)c).getChildren().add(2,indietro);
                        indietro.setOnAction(evt -> Main.getInstance().replaceSceneContent("profilo", Main.getInstance().stage, 600, 400));
                    }

            }
        });
    }

    protected DialogCalendario loadDialogCalendario(String nome, int width, int height)
    {
        FXMLLoader fxmlLoader = Main.getInstance().replaceSceneContent
                (nome, new Stage(), width, height);
        DialogCalendario dialogCalendario = fxmlLoader.<DialogCalendario>getController();
        dialogCalendario.setCalendarioView(this);
        return dialogCalendario;
    }

    public void aggiungiEvento(Entry<?> entry, Calendar calendar, boolean addOnCalendar)
    {
        Evento e = new Evento();
        e.setIdEtichetta(etichette.get(calendar).getIdEtichetta());
        System.out.println("CALENDARIO ID: " + calendario.getIdCalendario());
        e.setIdCalendario(calendario.getIdCalendario());
        e.setNome(entry.getTitle());
        e.setDescrizione(entry.getLocation());
        e.setDataInizio(entry.getStartAsLocalDateTime());
        e.setDataFine(entry.getEndAsLocalDateTime());
        e.setFullDay(entry.isFullDay());

        e = DBClient.getIstance().insertEvento(e);
        entry.setId(""+e.getIdEvento());

        if(e != null)
        {
            if(addOnCalendar && calendar != null)
                calendar.addEntry(entry);
            eventi.put(entry,e);
        }
    }

    public void modificaEvento(Entry<?> entry, CalendarEvent event)
    {
        if(!controlliPermessi())
            return;

        System.out.println(event);
        if(event.getEventType() != CalendarEvent.ENTRY_CALENDAR_CHANGED){
            Evento e = eventi.get(entry);
            e.setNome(entry.getTitle());
            e.setDescrizione(entry.getLocation());
            e.setDataInizio(entry.getStartAsLocalDateTime());
            e.setDataFine(entry.getEndAsLocalDateTime());
            e.setFullDay(entry.isFullDay());
            DBClient.getIstance().updateEvento(e);
        }
        System.out.println("Modifica Evento ");
    }

    public void rimuoviEvento(Entry<?> entry, boolean removeOnCalendar)
    {
        if(!controlliPermessi())
            return;
        DBClient.getIstance().removeEvento(eventi.get(entry).getIdEvento());
        eventi.remove(entry);

        if(removeOnCalendar)
            entry.getCalendar().removeEntry(entry);
    }

    public void aggiungiEtichetta(String nome, int styleNum)
    {
        if(!controlliPermessi())
            return;
        Calendar calendar = new Calendar(nome);
        calendar.setStyle(Calendar.Style.getStyle(styleNum));


        Etichetta etichetta = new Etichetta();
        etichetta.setNome(calendar.getName());
        etichetta.setColore(coloriMap.get(Calendar.Style.valueOf(calendar.getStyle())));

        etichetta = DBClient.getIstance().insertEtichetta(etichetta);
        if(etichetta != null)
        {
            myCalendarSource.getCalendars().add(calendar);
            etichette.put(calendar,etichetta);
        }
    }

    public void rimuoviEtichetta(Calendar c)
    {
        if(!controlliPermessi())
            return;

        myCalendarSource.getCalendars().remove(c);
        DBClient.getIstance().removeEtichetta(etichette.get(c).getIdEtichetta());
        etichette.remove(c);
    }

    public boolean controlliPermessi()
    {
        return true;
    }
}
