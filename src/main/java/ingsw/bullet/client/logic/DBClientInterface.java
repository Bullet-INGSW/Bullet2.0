package ingsw.bullet.client.logic;

import ingsw.bullet.model.*;

import java.util.ArrayList;
import java.util.List;

public interface DBClientInterface {

    boolean login(String email,String password);
    void chiudi();
    Calendario findCalendarioById(int id);
    Calendario findCalendarioPersonaleByEmail(String email);
    List<Calendario> findCalendariCondivisiByEmail(String email);
    Calendario insertCalendario(Calendario c);
    Calendario updateCalendario(Calendario c);
    boolean removeCalendario(int id);

    Etichetta findEtichettaById(int id);
    Etichetta insertEtichetta(Etichetta c);
    Etichetta updateEtichetta(Etichetta c);
    boolean removeEtichetta(int id);

    Evento findEventoById(int id);
    Evento insertEvento(Evento c);
    Evento updateEvento(Evento c);
    boolean removeEvento(int id);
    List<Evento> findEventoByCalendario(int idCalendario);

    Gruppo findGruppoById(int id);
    Gruppo insertGruppo(Gruppo c);
    Gruppo updateGruppo(Gruppo c);
    boolean removeGruppo(int id);

    Membro findMembro(String email,int idGruppo);
    Membro insertMembro(Membro c);
    Membro updateMembro(Membro c);
    boolean removeMembro(String email,String idGruppo);

    Notifica findNotificaById(int id);
    Notifica insertNotifica(Notifica c);
    Notifica updateNotifica(Notifica c);
    boolean removeNotifica(int id);

    Promemoria findPromemoriaById(int id);
    Promemoria insertPromemoria(Promemoria c);
    Promemoria updatePromemoria(Promemoria c);
    boolean removePromemoria(int id);

    TDL findTDLById(int id);
    TDL findTDLPersonaleByEmail(String email);
    List<TDL> findTDLCondiviseByEmail(String email);
    TDL insertTDL(TDL c);
    TDL updateTDL(TDL c);
    boolean removeTDL(int id);

    Utente findUtenteByEmail(String email);
    Utente insertUtente(Utente c);
    Utente updateUtente(Utente c);
    boolean removeUtente(String email);

    Partecipante findPartecipantePromemoriaById(int id, String email);
    Partecipante insertPartecipantePromemoria(Partecipante c);
    Partecipante updatePartecipantePromemoria(Partecipante c);
    boolean removePartecipantePromemoria(int id, String email);

    Partecipante findPartecipanteEventoById(int id, String email);
    Partecipante insertPartecipanteEvento(Partecipante c);
    Partecipante updatePartecipanteEvento(Partecipante c);
    boolean removePartecipanteEvento(int id, String email);

    List<Gruppo> findGroupByEmail(String email);
}
