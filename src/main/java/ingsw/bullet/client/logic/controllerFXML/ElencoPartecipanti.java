package ingsw.bullet.client.logic.controllerFXML;

import ingsw.bullet.client.logic.calendar.Evento;
import ingsw.bullet.client.logic.calendar.EventoCondiviso;

public class ElencoPartecipanti extends DialogCalendario{

    public EventoCondiviso getEvento() {
        return evento;
    }

    public void setEvento(EventoCondiviso evento) {
        this.evento = evento;
    }

    EventoCondiviso evento;
}
