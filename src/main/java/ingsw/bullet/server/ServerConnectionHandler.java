package ingsw.bullet.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import ingsw.bullet.server.NetworkUtility.KryoUtil;
import ingsw.bullet.server.NetworkUtility.Richiesta;
import ingsw.bullet.model.*;
import ingsw.bullet.server.persistence.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerConnectionHandler {
    public ServerConnectionHandler() {
        Log.set(Log.LEVEL_DEBUG);

        Server server=new Server();
        KryoUtil.registerServerClasses(server);

        server.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                connessioniNonLoggate.add(connection);
            }

            @Override
            public void disconnected(Connection connection) {
                if(connessioniNonLoggate.contains(connection))
                    connessioniNonLoggate.remove(connection);
            }

            @Override
            public void received(Connection connection, Object object) {

                if(connessioniNonLoggate.contains(connection)){
                    if(object instanceof  Richiesta){
                        Richiesta r=(Richiesta)object;
                        String tipo=r.getTipoRichiesta();


                        switch (tipo){
                            case "login":
                                utente=DBManager.getInstance().findUtenteByPrimaryKey(r.getStringa());
                                if(utente.getPassword().equals(r.getStringa2())){
                                    connessioniNonLoggate.remove(connection);
                                    connessioniLoggate.put(connection,r.getStringa());
                                    connection.sendUDP(true);
                                }
                                else{
                                    connection.sendUDP(false);
                                }
                                break;

                            case "insertUtente":
                                utente=r.getUtente();
                                DBManager.getInstance().addUtente(utente);
                                connection.sendUDP(utente);

                            default:
                                System.out.println("Non handled");
                                break;
                        }
                    }

                }

               else{ if(object instanceof Richiesta){
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
                            connection.sendUDP(calendario);
                            break;
                        case "findCalendarioPersonalebyEmail":
                            stringa=r.getStringa();
                            calendario=DBManager.getInstance().findCalendarioPersonaleByEmail(stringa);
                            connection.sendUDP(calendario);
                        case "findCalendariCondivisiByEmail":
                            stringa=r.getStringa();
                            listCalendario=DBManager.getInstance().findCalendariCondivisiByEmail(stringa);
                            connection.sendUDP(listCalendario);
                        case "updateCalendario":
                            calendario=r.getCalendario();
                            DBManager.getInstance().getDAOCalendario().update(calendario);
                            connection.sendUDP(calendario);
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
                            connection.sendUDP(etichetta);
                            break;
                        case "updateEtichetta":
                            etichetta=r.getEtichetta();
                            DBManager.getInstance().getDAOEtichetta().update(etichetta);
                            connection.sendUDP(etichetta);
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
                            connection.sendUDP(evento);
                            break;
                        case "updateEvento":
                            evento=r.getEvento();
                            DBManager.getInstance().getDAOEvento().update(evento);
                            connection.sendUDP(evento);
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
                            connection.sendUDP(gruppo);
                            break;
                        case "updateGruppo":
                            gruppo=r.getGruppo();
                            DBManager.getInstance().getDAOGruppo().update(gruppo);
                            connection.sendUDP(gruppo);
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
                            membro=DBManager.getInstance().findMembroByPrimaryKey(r.getStringa(),Integer.parseInt(r.getStringa2()));
                            connection.sendUDP(gruppo);
                            break;
                        case "insertMembro":
                            membro =r.getMembro();
                            DBManager.getInstance().addMembro(membro);
                            connection.sendUDP(membro);
                            break;
                        case "updateMembro":
                            membro=r.getMembro();
                            DBManager.getInstance().getDAOMembro().update(membro);
                            connection.sendUDP(membro);
                            break;
                        case "removeMembro":
                            membro=DBManager.getInstance().findMembroByPrimaryKey(r.getStringa(),Integer.parseInt(r.getStringa2()));
                            DBManager.getInstance().deleteMembro(membro);
                            connection.sendUDP(true);
                            break;


                        //Notifica
                        case "findNotificaById":
                            notifica=DBManager.getInstance().findNotificaByPrimaryKey(r.getNum());
                            connection.sendUDP(notifica);
                            break;
                        case "insertNotifica":
                            notifica =r.getNotifica();
                            DBManager.getInstance().addNotifica(notifica);
                            connection.sendUDP(notifica);
                            break;
                        case "updateNotifica":
                            notifica=r.getNotifica();
                            DBManager.getInstance().getDAONotifica().update(notifica);
                            connection.sendUDP(notifica);
                            break;
                        case "removeNotifica":
                            num=r.getNum();
                            notifica=DBManager.getInstance().findNotificaByPrimaryKey(num);
                            DBManager.getInstance().deleteNotifica(notifica);
                            connection.sendUDP(true);
                            break;

  //-----------------------COMPLETARE DA QUA IN POI
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
                            partecipante =r.getPartecipante();
                            DBManager.getInstance().addPartecipante
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

                        case "findGroupByEmail":
                            stringa=r.getStringa();
                            List<Gruppo> list=new ArrayList<>();
                            List<Membro> m=DBManager.getInstance().findMembroByUtente(stringa);
                            if(m.isEmpty()){
                             connection.sendUDP(list);
                             break;
                            }
                            for(int i=0;i<m.size();i++){
                                Membro mem=m.get(i);
                                list.add(DBManager.getInstance().findGruppoByPrimaryKey(mem.getIdGruppo()));
                            }

                            connection.sendUDP(list);
                            break;
                    }


                }}
            }
        });

    }



    //VARAIBILI
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
    List<Calendario> listCalendario=null;
    List<Connection> connessioniNonLoggate;
    HashMap<Connection,String> connessioniLoggate;
}
