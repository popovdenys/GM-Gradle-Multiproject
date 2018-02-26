/*
 * File : ExpeditionsDisplaySessionHandler.java
 * Description : Websocket for expeditions display
 *
 * Author : Popov Denys
 * Created : 25 Feb, 2018
 *
 * Modified : { date: 25/02/18
 *             ,time: 06:45 PM }
 * Modified by: Popov Denys
 *
 * Last modification : Sessions manipulation and getBasicRemote()
 */

package po.galaxy.websockets;

import org.json.JSONObject;
import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;
import po.galaxy.domain.StatusType;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExpeditionsDisplaySessionHandler {

    private List<Session> sessions = new ArrayList<>();

    public void addSessions(Session session) {
        sessions.add(session);
        sendAllExpeditions(session);
    }

    public void removeSessions(Session session) {
        sessions.remove(session);
    }

    private void sendMessage(JSONObject msg) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(msg.toString());
            } catch (IOException e) {
                System.out.println("remove session");
                removeSessions(session);
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(JSONObject msg, Session session) {
        try {
            session.getBasicRemote().sendText(msg.toString());
        } catch (IOException e) {
            System.out.println("remove single session");
            removeSessions(session);
            e.printStackTrace();
        }
    }

    public void newExpedition(Expedition expedition) {

        System.out.println(String.format("add new expedition # %d", expedition.getId()));

        sendMessage(prepareJsonWithAction(expedition, "add"));
    }

    public void amendExpedition(Expedition expedition) {

        System.out.println(String.format("update expedition # %d", expedition.getId()));

        sendMessage(prepareJsonWithAction(expedition, "remove"));

        sendMessage(prepareJsonWithAction(expedition, "add"));

    }

    private void sendAllExpeditions(Session session) {
        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();
        List<Expedition> expeditions = galaxiesDAO.getExpeditionsList();
        for (Expedition expedition: expeditions) {
            if(!expedition.getStatus().equals(StatusType.TRACKING.get()))
                sendMessage(prepareJsonWithAction(expedition, "add"), session);
        }
    }

    private JSONObject prepareJsonWithAction(Expedition expedition, String action) {
        JSONObject json = new JSONObject();

        json.append("id", expedition.getId());
        if ( action == "add" ) {
            json.append("status", expedition.getStatus());
            json.append("itinerary", expedition);
            json.append("contractor", expedition.getContractor());
            json.append("update", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")).toString());
        }
        json.append("action", action);

        return json;
    }


}
