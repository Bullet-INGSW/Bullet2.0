package ingsw.bullet.client.logic;

import java.util.ArrayList;

public interface DBClientInterface {

    // crea Gruppo

    // restituisce true se esiste o false se non esiste
    boolean esisteUtente(String email);

    /* restituisce 1 se crea il gruppo senza problemi, 0 se ha problemi, in dettaglio se il nome del gruppo è gia' usato
        restituisce -1 */
    int creaGruppo(String amministratore, String nome, ArrayList<String> membri);

    // dato un utente restituisce l'elenco dei gruppi a lui associati (sia membro che amministratore)
    ArrayList<String> getElencoGruppi(String email);

    // restituisce l'elenco dei membri associati a quel gruppo
    ArrayList<String> getElencoMembri(String gruppo);

    // se a buon fine true oppure false
    boolean setMembriGruppo(String gruppo, ArrayList<String> elencoMembri);

    // restituisce nei parametri i dati dell'utente --- Maschio 1, Femmina 0
    boolean getUtente(String nome, String cognome, String email, boolean sesso);

    // inserisce un nuovo utente --- Maschio 1, Femmina 0
    boolean insertUtente(String nome, String cognome, String email, boolean sesso, String password);

    // rimuove utente dal gruppo
    boolean rimuoviMembro(String gruppo, String email, String amministratore);

    // verifica se un utente e' iscritto con questi dati
    boolean login(String email, String password);

    // restituisce l'elenco delle notifiche associate all'utente
    ArrayList<String> getNotifiche(String utente);

    // set le notifiche dell'utente
    boolean setNotifiche(String utente, ArrayList<String> notifiche);

    // aggiunge una notifica all'utente
    boolean aggiungiNotiche(String utente, String notifica);

    // aggiunge a tutti i membri del gruppo una notifica
    boolean aggiungiNotificheAlGruppo(String gruppo, String notifica);

    // restituisce l'elenco dei calendari condivisi associati all'utente
    ArrayList<String> getElencoCalendariCondivisi(String utente);

    // restituisce l'elenco dei calendari condivisi associati all'utente
    ArrayList<String> getElencoTDLCondivise(String utente);

    // restituisce gli utenti che match con la regexEmail dai hai capito D:
    ArrayList<String> utentiConNomiRegex(String regexEmail);

    boolean esisteGruppo(String gruppo);

}
