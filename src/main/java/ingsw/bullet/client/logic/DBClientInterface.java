package ingsw.bullet.client.logic;

import ingsw.bullet.server.model.*;

import java.util.ArrayList;

public interface DBClientInterface {

    // se non trova qualcosa restituisce null

    Calendario findCalendarioById(int id);
    Calendario insertCalendario(Calendario c);
    Calendario updateCalendario(Calendario c);

    Etichetta findEtichettaById(int id);
    Etichetta insertEtichetta(Etichetta c);
    Etichetta updateEtichetta(Etichetta c);

    Evento findEventoById(int id);
    Evento insertEvento(Etichetta c);
    Evento updateEvento(Etichetta c);

    Gruppo findGruppoById(int id);
    Gruppo insertGruppo(Gruppo c);
    Gruppo updateGruppo(Gruppo c);

    Membro findMembroById(String email);
    Membro insertMembro(Membro c);
    Membro updateMembro(Membro c);

    Notifica findNotificaById(int id);
    Notifica insertNotifica(Notifica c);
    Notifica updateNotifica(Notifica c);

    Promemoria findPromemoriaById(int id);
    Promemoria insertPromemoria(Promemoria c);
    Promemoria updatePromemoria(Promemoria c);

    TDL findTDLById(int id);
    TDL insertTDL(TDL c);
    TDL updateTDL(TDL c);

    Utente findUtenteById(String email);
    Utente insertUtente(Utente c);
    Utente updateUtente(Utente c);

    Partecipante findPromemoriaById(String email);
    Partecipante insertPromemoria(Partecipante c);
    Partecipante updatePromemoria(Partecipante c);
}
