package ingsw.bullet.client.logic;

import ingsw.bullet.server.model.*;

import java.util.ArrayList;

public interface DBClientInterface {

    // se non trova qualcosa restituisce null

    Calendario findCalendarioById(int id);
    Calendario insertCalendario(Calendario c);
    Calendario updateCalendario(Calendario c);
    boolean removeCalendario(int id);

    Etichetta findEtichettaById(int id);
    Etichetta insertEtichetta(Etichetta c);
    Etichetta updateEtichetta(Etichetta c);
    boolean removeEtichetta(int id);

    Evento findEventoById(int id);
    Evento insertEvento(Etichetta c);
    Evento updateEvento(Etichetta c);
    boolean removeEvento(int id);

    Gruppo findGruppoById(int id);
    Gruppo insertGruppo(Gruppo c);
    Gruppo updateGruppo(Gruppo c);
    boolean removeGruppo(int id);

    Membro findMembroById(String email);
    Membro insertMembro(Membro c);
    Membro updateMembro(Membro c);
    boolean removeMembro(int id);

    Notifica findNotificaById(int id);
    Notifica insertNotifica(Notifica c);
    Notifica updateNotifica(Notifica c);
    boolean removeNotifica(int id);

    Promemoria findPromemoriaById(int id);
    Promemoria insertPromemoria(Promemoria c);
    Promemoria updatePromemoria(Promemoria c);
    boolean removePromemoria(int id);

    TDL findTDLById(int id);
    TDL insertTDL(TDL c);
    TDL updateTDL(TDL c);
    boolean removeTDL(int id);

    Utente findUtenteByEmail(String email);
    Utente insertUtente(Utente c);
    Utente updateUtente(Utente c);
    boolean removeUtente(String email);

    Partecipante findPartecipanteByEmail(String email);
    Partecipante insertPartecipante(Partecipante c);
    Partecipante updatePartecipante(Partecipante c);
    boolean removePartecipante(String email);
}
