package ingsw.bullet.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.NetworkUtility.Errore;
import ingsw.bullet.server.NetworkUtility.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;
import ingsw.bullet.server.persistence.DBManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerConnectionHandler {
    public ServerConnectionHandler() {
        Log.set(Log.LEVEL_DEBUG);
        connessioniLoggate = new HashMap<>();
        connessioniNonLoggate = new ArrayList<>();

        server = new Server();
        KryoUtil.registerServerClasses(server);
        try {
            server.bind(KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                connessioniNonLoggate.add(connection);
            }

            @Override
            public void disconnected(Connection connection) {
                if (connessioniNonLoggate.contains(connection))
                    connessioniNonLoggate.remove(connection);
            }

            @Override
            public void received(Connection connection, Object object) {


                if (object instanceof Richiesta) {
                    Richiesta r = (Richiesta) object;
                    String tipo = r.getTipoRichiesta();
                    System.out.println("Richiesta: " + tipo);
                    switch (tipo) {


                        //calendario
                        case "findCalendarioById":
                            calendario = DBManager.getInstance().findCalendarioByPrimaryKey(r.getNum());
                            if (calendario == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(calendario);
                            break;
                        case "insertCalendario":
                            calendario = r.getCalendario();
                            DBManager.getInstance().addCalendario(calendario);
                            connection.sendUDP(calendario);
                            break;
                        case "findCalendarioPersonaleByEmail":
                            stringa = r.getStringa();

                            List<Calendario> cal = DBManager.getInstance().findCalendarioByUtente(stringa);
                            if(cal==null || cal.isEmpty()){
                                connection.sendUDP(new Errore());
                            }
                            else {
                                calendario = cal.get(0);

                                listEvento = DBManager.getInstance().findEventoByCalendario(calendario.getIdCalendario());
                                if (listEvento == null)
                                    calendario.setEventi(new ArrayList<>());
                                else
                                    calendario.setEventi(listEvento);

                                System.out.println("il calendario ha eventi " + calendario.getEventi().size());
                                connection.sendTCP(calendario);
                            }
                            break;
                        case "findCalendariCondivisiByEmail":
                           /* stringa = r.getStringa();
                            listCalendario = DBManager.getInstance().findCalendarioByMembro(stringa);
                            if (listCalendario == null || listCalendario.isEmpty()) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(listCalendario);

                            */
                            stringa = r.getStringa();
                            List<Calendario> cale = DBManager.getInstance().findCalendarioByMembro(stringa);
                            if(cale==null || cale.isEmpty()){
                                listCalendario=new ArrayList<>();
                                connection.sendUDP(listCalendario);
                            }
                            else {
                                for(int i=0;i<cale.size();i++){
                                    cale.get(i).setEventi(DBManager.getInstance().findEventoByCalendario(cale.get(i).getIdCalendario()));
                                }
                                connection.sendUDP(cale);
                            }

                            break;
                        case "updateCalendario":
                            calendario = r.getCalendario();
                            DBManager.getInstance().getDAOCalendario().update(calendario);
                            connection.sendUDP(calendario);
                            break;
                        case "removeCalendario":
                            num = r.getNum();
                            calendario = DBManager.getInstance().findCalendarioByPrimaryKey(num);
                            DBManager.getInstance().deleteCalendario(calendario);
                            connection.sendUDP(true);
                            break;

                        //ETICHETTA
                        case "findEtichettaById":
                            etichetta = DBManager.getInstance().findEtichettaByPrimaryKey(r.getNum());
                            if (etichetta == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(etichetta);
                            break;
                        case "insertEtichetta":
                            etichetta = r.getEtichetta();
                            DBManager.getInstance().addEtichetta(etichetta);
                            connection.sendUDP(etichetta);
                            break;
                        case "updateEtichetta":
                            etichetta = r.getEtichetta();
                            DBManager.getInstance().getDAOEtichetta().update(etichetta);
                            connection.sendUDP(etichetta);
                            break;
                        case "removeEtichetta":
                            num = r.getNum();
                            etichetta = DBManager.getInstance().findEtichettaByPrimaryKey(num);
                            DBManager.getInstance().deleteEtichetta(etichetta);
                            connection.sendUDP(true);
                            break;

                        //EVENTO
                        case "findEventoById":
                            evento = DBManager.getInstance().findEventoByPrimaryKey(r.getNum());
                            if (evento == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(evento);
                            break;
                        case "insertEvento":
                            evento = r.getEvento();
                            DBManager.getInstance().addEvento(evento);
                            connection.sendUDP(evento);
                            break;
                        case "updateEvento":
                            evento = r.getEvento();
                            DBManager.getInstance().getDAOEvento().update(evento);
                            connection.sendUDP(evento);
                            break;
                        case "removeEvento":
                            num = r.getNum();
                            evento = DBManager.getInstance().findEventoByPrimaryKey(num);
                            DBManager.getInstance().deleteEvento(evento);
                            connection.sendUDP(true);
                            break;
                        case "findEventoByCalendario":
                            num=r.getNum();
                            listEvento=DBManager.getInstance().findEventoByCalendario(num);
                            System.out.println(listEvento.size());
                            if(listEvento==null)
                                connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(listEvento);
                            break;


                        //Gruppo
                        case "findGruppoById":
                            gruppo = DBManager.getInstance().findGruppoByPrimaryKey(r.getNum());
                            if (gruppo == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(gruppo);
                            break;
                        case "insertGruppo":
                            gruppo = r.getGruppo();
                            DBManager.getInstance().addGruppo(gruppo);
                            connection.sendUDP(gruppo);
                            break;
                        case "updateGruppo":
                            gruppo = r.getGruppo();
                            DBManager.getInstance().getDAOGruppo().update(gruppo);
                            connection.sendUDP(gruppo);
                            break;
                        case "removeGruppo":
                            num = r.getNum();
                            gruppo = DBManager.getInstance().findGruppoByPrimaryKey(num);
                            DBManager.getInstance().deleteGruppo(gruppo);
                            connection.sendUDP(true);
                            break;
                        case "findGroupByEmail":
                            stringa=r.getStringa();
                            List<Gruppo> listGruppo=DBManager.getInstance().findGruppoByMembro(stringa);
                            if(listGruppo==null)
                                listGruppo=new ArrayList<>();
                            if(!listGruppo.isEmpty()){
                                for(int i=0;i<listGruppo.size();i++){

                                    //Membri
                                    listGruppo.get(i).setMembri(DBManager.getInstance().findMembroByGruppo(listGruppo.get(i).getIdGruppo()));
                                    if(listGruppo.get(i).getMembri()==null)
                                        listGruppo.get(i).setMembri(new ArrayList<>());
                                    //calendari
                                    listGruppo.get(i).setCalendari(DBManager.getInstance().findCalendarioByGruppo(listGruppo.get(i).getIdGruppo()));
                                    if(listGruppo.get(i).getCalendari()==null)
                                        listGruppo.get(i).setCalendari(new ArrayList<>());
                                    //tdl
                                    listGruppo.get(i).setTDL(DBManager.getInstance().findTDLByGruppo(listGruppo.get(i).getIdGruppo()));
                                    if(listGruppo.get(i).getCalendari()==null)
                                        listGruppo.get(i).setCalendari(new ArrayList<>());

                                    listGruppo.get(i).setAmministratori(new ArrayList<>());

                                }

                            }
                            connection.sendUDP(listGruppo);
                            break;

                        //Membro
                        case "findMembroById":
                            //DA FARE è SBAGLIATO
                            membro = DBManager.getInstance().findMembroByPrimaryKey(r.getStringa(), Integer.parseInt(r.getStringa2()));
                            if (membro == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(membro);
                            break;
                        case "insertMembro":
                            membro = r.getMembro();
                            DBManager.getInstance().addMembro(membro);
                            connection.sendUDP(membro);
                            break;
                        case "updateMembro":
                            membro = r.getMembro();
                            DBManager.getInstance().getDAOMembro().update(membro);
                            connection.sendUDP(membro);
                            break;
                        case "removeMembro":
                            membro = DBManager.getInstance().findMembroByPrimaryKey(r.getStringa(), Integer.parseInt(r.getStringa2()));
                            DBManager.getInstance().deleteMembro(membro);
                            connection.sendUDP(true);
                            break;


                        //Notifica
                        case "findNotificaById":
                            notifica = DBManager.getInstance().findNotificaByPrimaryKey(r.getNum());
                            if (notifica == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(notifica);
                            break;
                        case "insertNotifica":
                            notifica = r.getNotifica();
                            DBManager.getInstance().addNotifica(notifica);
                            connection.sendUDP(notifica);
                            break;
                        case "updateNotifica":
                            notifica = r.getNotifica();
                            DBManager.getInstance().getDAONotifica().update(notifica);
                            connection.sendUDP(notifica);
                            break;
                        case "removeNotifica":
                            num = r.getNum();
                            notifica = DBManager.getInstance().findNotificaByPrimaryKey(num);
                            DBManager.getInstance().deleteNotifica(notifica);
                            connection.sendUDP(true);
                            break;

                        //Promemoria
                        case "findPromemoriaById":
                            promemoria = DBManager.getInstance().findPromemoriaByPrimaryKey(r.getNum());
                            if (promemoria == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(promemoria);
                            break;
                        case "insertPromemoria":
                            promemoria = r.getPromemoria();
                            DBManager.getInstance().addPromemoria(promemoria);
                            connection.sendUDP(promemoria);
                            break;
                        case "updatePromemoria":
                            promemoria = r.getPromemoria();
                            DBManager.getInstance().getDAOPromemoria().update(promemoria);
                            connection.sendUDP(promemoria);
                            break;
                        case "removePromemoria":
                            num = r.getNum();
                            promemoria = DBManager.getInstance().findPromemoriaByPrimaryKey(num);
                            DBManager.getInstance().deletePromemoria(promemoria);
                            connection.sendUDP(true);
                            break;


                        //TDL
                        case "findTDLById":
                            tdl = DBManager.getInstance().findTDLByPrimaryKey(r.getNum());
                            if (tdl == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(tdl);
                            break;
                        case "insertTDL":
                            tdl = r.getTDL();
                            DBManager.getInstance().addTDL(tdl);
                            //DAFare
                            connection.sendUDP(tdl);
                            break;
                        case "updateTDL":
                            tdl = r.getTDL();
                            DBManager.getInstance().getDAOTDL().update(tdl);
                            connection.sendUDP(DBManager.getInstance().findTDLByPrimaryKey(tdl.getIdTDL()));
                            break;
                        case "removeTDL":
                            num = r.getNum();
                            tdl = DBManager.getInstance().findTDLByPrimaryKey(num);
                            DBManager.getInstance().deleteTDL(tdl);
                            connection.sendUDP(true);
                            break;

                        case "findTDLPersonaleByEmail":
                            stringa = r.getStringa();

                            //DAIMPLEMENTARE
                            connection.sendUDP(false);
                            break;

                        case "findTDLCondiviseByEmail":
                            stringa = r.getStringa();
                            listTDL = DBManager.getInstance().findTDLByUtente(stringa);
                            if (listTDL == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(tdl);
                            break;

                        //Utente
                        case "findUtenteByEmail":
                            utente = DBManager.getInstance().findUtenteByPrimaryKey(r.getStringa());
                            if (utente != null)
                                connection.sendUDP(utente);
                            else
                                connection.sendUDP(new Errore());
                            break;
                        case "insertUtente":
                            utente = r.getUtente();
                            DBManager.getInstance().addUtente(utente);
                            connection.sendUDP(utente);
                            break;
                        case "updateUtente":
                            utente = r.getUtente();
                            DBManager.getInstance().getDAOUtente().update(utente);
                            connection.sendUDP(utente);
                            break;
                        case "removeUtente":
                            stringa = r.getStringa();
                            utente = DBManager.getInstance().findUtenteByPrimaryKey(stringa);
                            DBManager.getInstance().deleteUtente(utente);
                            connection.sendUDP(true);
                            break;

                        //PartecipantePromemoria
                        case "findPartecipantePromemoriaById":
                            num = r.getNum();
                            stringa = r.getStringa();
                            partecipante = DBManager.getInstance().findPartecipantePromemoriaByPrimaryKey(stringa, num);
                            if (partecipante == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(partecipante);
                            break;
                        case "insertPartecipantePromemoria":
                            partecipante = r.getPartecipante();
                            DBManager.getInstance().addPartecipantePromemoria(partecipante);
                            connection.sendUDP(partecipante);
                            break;
                        case "updatePartecipantePromemoria":
                            partecipante = r.getPartecipante();
                            DBManager.getInstance().getDAOPartecipantePromemoria().update(partecipante);
                            connection.sendUDP(partecipante);
                            break;
                        case "removePartecipantePromemoria":
                            num = r.getNum();
                            stringa = r.getStringa();
                            partecipante = DBManager.getInstance().findPartecipantePromemoriaByPrimaryKey(stringa, num);
                            DBManager.getInstance().deletePartecipantePromemoria(partecipante);
                            connection.sendUDP(true);
                            break;


                        //PartecipanteEvento
                        case "findPartecipanteEventoById":
                            num = r.getNum();
                            stringa = r.getStringa();
                            partecipante = DBManager.getInstance().findPartecipanteEventoByPrimaryKey(stringa, num);
                            if (partecipante == null) connection.sendUDP(new Errore());
                            else
                                connection.sendUDP(partecipante);
                            break;
                        case "insertPartecipanteEvento":
                            partecipante = r.getPartecipante();
                            DBManager.getInstance().addPartecipanteEvento(partecipante);
                            connection.sendUDP(partecipante);
                            break;
                        case "updatePartecipanteEvento":
                            partecipante = r.getPartecipante();
                            DBManager.getInstance().getDAOPartecipanteEvento().update(partecipante);
                            connection.sendUDP(partecipante);
                            break;
                        case "removePartecipanteEvento":
                            num = r.getNum();
                            stringa = r.getStringa();
                            partecipante = DBManager.getInstance().findPartecipanteEventoByPrimaryKey(stringa, num);
                            DBManager.getInstance().deletePartecipanteEvento(partecipante);
                            connection.sendUDP(true);
                            break;

                        default:
                            System.out.println("Caso non handeled " + tipo);
                            break;
                    }


                }
            }

        });

    }


    //VARAIBILI
    Server server;
    Calendario calendario;
    Etichetta etichetta;
    Evento evento;
    Membro membro;
    Gruppo gruppo;
    Promemoria promemoria;
    Partecipante partecipante;
    Notifica notifica;
    Utente utente;
    TDL tdl;
    int num;
    boolean bool;
    String stringa;
    List<Calendario> listCalendario = null;
    List<TDL> listTDL = null;
    List<Evento> listEvento=null;
    List<Connection> connessioniNonLoggate;
    HashMap<Connection, String> connessioniLoggate;
}
