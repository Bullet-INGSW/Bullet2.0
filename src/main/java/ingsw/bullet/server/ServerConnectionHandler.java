package ingsw.bullet.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.NetworkUtility.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;
import ingsw.bullet.server.persistence.DBManager;

public class ServerConnectionHandler {
    public ServerConnectionHandler() {
        Log.set(Log.LEVEL_DEBUG);

        Server server=new Server();
        KryoUtil.registerServerClasses(server);

        server.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                //super.connected(connection);
            }

            @Override
            public void disconnected(Connection connection) {
                //super.disconnected(connection);
            }

            @Override
            public void received(Connection connection, Object object) {
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
                if(object instanceof Richiesta){
                    Richiesta r=(Richiesta)object;
                    String tipo=r.getTipoRichiesta();

                    switch (tipo){
                        //calendario
                        case "findCalendarioById":
                            calendario=DBManager.getInstance().findCalendarioByPrimaryKey(r.getNum());
                            connection.sendUDP(calendario);
                            break;

                        case "insertCalendario":
                            calendario =r.getCalendario();
                            DBManager.getInstance().addCalendario(calendario);
                            //DAFAre
                            connection.sendUDP(calendario);
                            break;
                        case "updateCalendario":
                            calendario=r.getCalendario();
                            DBManager.getInstance().getDAOCalendario().update(calendario);
                            connection.sendUDP(DBManager.getInstance().findCalendarioByPrimaryKey(calendario.getIdCalendario()));
                            break;
                        case "removeCalendario":
                            num=r.getNum();
                            calendario=DBManager.getInstance().findCalendarioByPrimaryKey(num);
                            DBManager.getInstance().deleteCalendario(calendario);
                            connection.sendUDP(true);
                            break;

                        //ETICHETTA
                        case "findEtichettaById":
                            etichetta=DBManager.getInstance().findEtichettaByPrimaryKey(r.getNum());
                            connection.sendUDP(etichetta);
                            break;
                        case "insertEtichetta":
                            etichetta =r.getEtichetta();
                            DBManager.getInstance().addEtichetta(etichetta);
                            //DAFAre
                            connection.sendUDP(etichetta);
                            break;
                        case "updateEtichetta":
                            etichetta=r.getEtichetta();
                            DBManager.getInstance().getDAOEtichetta().update(etichetta);
                            connection.sendUDP(DBManager.getInstance().findEtichettaByPrimaryKey(etichetta.getIdEtichetta()));
                            break;
                        case "removeEtichetta":
                            num=r.getNum();
                            etichetta=DBManager.getInstance().findEtichettaByPrimaryKey(num);
                            DBManager.getInstance().deleteEtichetta(etichetta);
                            connection.sendUDP(true);
                            break;

                        //EVENTO
                        case "findEventoById":
                            evento=DBManager.getInstance().findEventoByPrimaryKey(r.getNum());
                            connection.sendUDP(evento);
                            break;
                        case "insertEvento":
                            evento =r.getEvento();
                            DBManager.getInstance().addEvento(evento);
                            //DAFare
                            connection.sendUDP(evento);
                            break;
                        case "updateEvento":
                            evento=r.getEvento();
                            DBManager.getInstance().getDAOEvento().update(evento);
                            connection.sendUDP(DBManager.getInstance().findEventoByPrimaryKey(evento.getIdEvento()));
                            break;
                        case "removeEvento":
                            num=r.getNum();
                            evento=DBManager.getInstance().findEventoByPrimaryKey(num);
                            DBManager.getInstance().deleteEvento(evento);
                            connection.sendUDP(true);
                            break;


                        //Gruppo
                        case "findGruppoById":
                            gruppo=DBManager.getInstance().findGruppoByPrimaryKey(r.getNum());
                            connection.sendUDP(gruppo);
                            break;
                        case "insertGruppo":
                            gruppo =r.getGruppo();
                            DBManager.getInstance().addGruppo(gruppo);
                            //DAFare
                            connection.sendUDP(gruppo);
                            break;
                        case "updateGruppo":
                            gruppo=r.getGruppo();
                            DBManager.getInstance().getDAOGruppo().update(gruppo);
                            connection.sendUDP(DBManager.getInstance().findGruppoByPrimaryKey(gruppo.getIdGruppo()));
                            break;
                        case "removeGruppo":
                            num=r.getNum();
                            gruppo=DBManager.getInstance().findGruppoByPrimaryKey(num);
                            DBManager.getInstance().deleteGruppo(gruppo);
                            connection.sendUDP(true);
                            break;


                        //Membro
                        case "findMembroById":
                            //DA FARE è SBAGLIATO
                            membro=DBManager.getInstance().findMembroByPrimaryKey(r.getStringa(),r.get);
                            connection.sendUDP(gruppo);
                            break;
                        case "insertMembro":
                            membro =r.getMembro();
                            DBManager.getInstance().addMembro(membro);
                            //DAFare
                            connection.sendUDP(membro);
                            break;
                        case "updateMembro":
                            membro=r.getMembro();
                            DBManager.getInstance().getDAOMembro().update(membro);
                            //Pure this è errato
                            connection.sendUDP(DBManager.getInstance().findMembroByPrimaryKey(membro.getEmail()));
                            break;
                        case "removeMembro":
                            num=r.getNum();
                            //DA FARE PURE CHISSA
                            break;


                        //Notifica
                        case "findNotificaById":
                            notifica=DBManager.getInstance().findNotificaByPrimaryKey(r.getNum());
                            connection.sendUDP(notifica);
                            break;
                        case "insertNotifica":
                            notifica =r.getNotifica();
                            DBManager.getInstance().addNotifica(notifica);
                            //DAFare
                            connection.sendUDP(notifica);
                            break;
                        case "updateNotifica":
                            notifica=r.getNotifica();
                            DBManager.getInstance().getDAONotifica().update(notifica);
                            connection.sendUDP(DBManager.getInstance().findNotificaByPrimaryKey(notifica.getIdNotifica()));
                            break;
                        case "removeNotifica":
                            num=r.getNum();
                            notifica=DBManager.getInstance().findNotificaByPrimaryKey(num);
                            DBManager.getInstance().deleteNotifica(notifica);
                            connection.sendUDP(true);
                            break;

                        //Promemoria
                        case "findPromemoriaById":
                            promemoria=DBManager.getInstance().findPromemoriaByPrimaryKey(r.getNum());
                            connection.sendUDP(promemoria);
                            break;
                        case "insertPromemoria":
                            promemoria =r.getPromemoria();
                            DBManager.getInstance().addPromemoria(promemoria);
                            //DAFare
                            connection.sendUDP(promemoria);
                            break;
                        case "updatePromemoria":
                            promemoria=r.getPromemoria();
                            DBManager.getInstance().getDAOPromemoria().update(promemoria);
                            connection.sendUDP(DBManager.getInstance().findNotificaByPrimaryKey(promemoria.getIdPromemoria()));
                            break;
                        case "removePromemoria":
                            num=r.getNum();
                            promemoria=DBManager.getInstance().findPromemoriaByPrimaryKey(num);
                            DBManager.getInstance().deletePromemoria(promemoria);
                            connection.sendUDP(true);
                            break;


                        //TDL
                        case "findTDLById":
                            tdl=DBManager.getInstance().findTDLByPrimaryKey(r.getNum());
                            connection.sendUDP(tdl);
                            break;
                        case "insertTDL":
                            tdl =r.getTDL();
                            DBManager.getInstance().addTDL(tdl);
                            //DAFare
                            connection.sendUDP(tdl);
                            break;
                        case "updateTDL":
                            tdl=r.getTDL();
                            DBManager.getInstance().getDAOTDL().update(tdl);
                            connection.sendUDP(DBManager.getInstance().findTDLByPrimaryKey(tdl.getIdTDL()));
                            break;
                        case "removeTDL":
                            num=r.getNum();
                           tdl=DBManager.getInstance().findTDLByPrimaryKey(num);
                            DBManager.getInstance().deleteTDL(tdl);
                            connection.sendUDP(true);
                            break;

                        //Utente
                        case "findUtenteById":
                            utente=DBManager.getInstance().findUtenteByPrimaryKey(r.getStringa());
                            connection.sendUDP(utente);
                            break;
                        case "insertUtente":
                            utente =r.getUtente();
                            DBManager.getInstance().addUtente(utente);
                            //DAFare
                            connection.sendUDP(utente);
                            break;
                        case "updateUtente":
                            utente=r.getUtente();
                            DBManager.getInstance().getDAOUtente().update(utente);
                            connection.sendUDP(DBManager.getInstance().findUtenteByPrimaryKey(utente.getEmail()));
                            break;
                        case "removeUtente":
                            stringa=r.getStringa();
                            utente=DBManager.getInstance().findUtenteByPrimaryKey(stringa);
                            DBManager.getInstance().deleteUtente(utente);
                            connection.sendUDP(true);
                            break;

                            //DA FARE
                        //Partecipante
                        case "findPartecipanteById":
                            partecipante=DBManager.getInstance().findPartecipanteByPrimaryKey(r.getStringa());
                            connection.sendUDP(partecipante);
                            break;
                        case "insertPartecipante":
                            utente =r.getUtente();
                            DBManager.getInstance().addUtente(utente);
                            //DAFare
                            connection.sendUDP(utente);
                            break;
                        case "updatePartecipante":
                            utente=r.getUtente();
                            DBManager.getInstance().getDAOUtente().update(utente);
                            connection.sendUDP(DBManager.getInstance().findUtenteByPrimaryKey(utente.getEmail()));
                            break;
                        case "removePartecipante":
                            stringa=r.getStringa();
                            utente=DBManager.getInstance().findUtenteByPrimaryKey(stringa);
                            DBManager.getInstance().deleteUtente(utente);
                            connection.sendUDP(true);
                            break;
                    }


                }
            }
        });

    }


}
