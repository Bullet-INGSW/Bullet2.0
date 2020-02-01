package ingsw.bullet.client.logic.controllerFXML;


import ingsw.bullet.model.Evento;

public class ElencoPartecipanti extends DialogCalendario{

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    Evento evento;
}
