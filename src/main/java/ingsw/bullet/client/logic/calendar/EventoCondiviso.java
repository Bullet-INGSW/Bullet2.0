package ingsw.bullet.client.logic.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoCondiviso extends Evento{

    ArrayList<Partecipante> partecipanti;

    public EventoCondiviso(int id, String titolo, Etichetta etichetta, LocalDateTime dataInizio,
                           LocalDateTime dataFine, boolean fullDay, ArrayList<Partecipante> partecipanti) {
        super(id, titolo, etichetta, dataInizio, dataFine, fullDay);
    }


}
