package ingsw.bullet.client.logic;

import ingsw.bullet.server.model.*;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class DBClient implements DBClientInterface {

    protected static DBClient dbClient;

    public static DBClient getIstance()
    {
        if(dbClient == null)
            dbClient = new DBClient();
        return dbClient;
    }

    @Override
    public Calendario findCalendarioById(int id) {return null;}
    @Override
    public Calendario insertCalendario(Calendario c) {return null;}
    @Override
    public Calendario updateCalendario(Calendario c) {return null;}
    @Override
    public boolean removeCalendario(int id) {return false;}

    @Override
    public Etichetta findEtichettaById(int id) {return null;}
    @Override
    public Etichetta insertEtichetta(Etichetta c) {return null;}
    @Override
    public Etichetta updateEtichetta(Etichetta c) {return null;}
    @Override
    public boolean removeEtichetta(int id) {return false;}

    @Override
    public Evento findEventoById(int id) {return null;}
    @Override
    public Evento insertEvento(Etichetta c) {return null;}
    @Override
    public Evento updateEvento(Etichetta c) {return null;}
    @Override
    public boolean removeEvento(int id) {return false;}

    @Override
    public Gruppo findGruppoById(int id) {return null;}
    @Override
    public Gruppo insertGruppo(Gruppo c) {return null;}
    @Override
    public Gruppo updateGruppo(Gruppo c) {return null;}
    @Override
    public boolean removeGruppo(int id) {return false;}

    @Override
    public Membro findMembroById(String email) {return null;}
    @Override
    public Membro insertMembro(Membro c) {return null;}
    @Override
    public Membro updateMembro(Membro c) {return null;}
    @Override
    public boolean removeMembro(int id) {return false;}

    @Override
    public Notifica findNotificaById(int id) {return null;}
    @Override
    public Notifica insertNotifica(Notifica c) {return null;}
    @Override
    public Notifica updateNotifica(Notifica c) {return null;}

    @Override
    public Promemoria findPromemoriaById(int id) {return null;}
    @Override
    public Promemoria insertPromemoria(Promemoria c) {return null;}
    @Override
    public Promemoria updatePromemoria(Promemoria c) {return null; }
    @Override
    public boolean removePromemoria(int id) {return false;}

    @Override
    public TDL findTDLById(int id) {return null;}

    @Override
    public TDL insertTDL(TDL c) {return null;}

    @Override
    public TDL updateTDL(TDL c) {
        return null;
    }

    @Override
    public boolean removeTDL(int id) {
        return false;
    }

    @Override
    public Utente findUtenteByEmail(String email) {
        return null;
    }

    @Override
    public Utente insertUtente(Utente c) {
        return null;
    }

    @Override
    public Utente updateUtente(Utente c) {
        return null;
    }

    @Override
    public boolean removeUtente(String email) {
        return false;
    }

    @Override
    public Partecipante findPartecipanteByEmail(String email) {
        return null;
    }

    @Override
    public Partecipante insertPartecipante(Partecipante c) {
        return null;
    }

    @Override
    public Partecipante updatePartecipante(Partecipante c) {
        return null;
    }

    @Override
    public boolean removePartecipante(String email) {
        return false;
    }
}
