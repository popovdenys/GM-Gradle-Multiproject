/*
 * File : ExpeditionsDisplayWebsocket.java
 * Description : expeditions display Websocket
 *
 * Author : Popov Denys
 * Created : 25 Feb, 2018
 *
 * Modified : { date: 25/02/18
 *             ,time: 07:07 PM }
 * Modified by: Popov Denys
 *
 * Last modification : Websocket methods implementation
 */

package po.galaxy.websockets;

import org.json.JSONObject;
import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/expeditionsProccessing")
public class ExpeditionsDisplayWebsocket {

    @OnOpen
    public void open(Session session) {
        ExpeditionsDisplaySessionHandler handler = ExpeditionsDisplaySessionHandlerFactory.getHandler();
        handler.addSessions(session);
    }

    @OnClose
    public void close(Session session) {
        ExpeditionsDisplaySessionHandler handler = ExpeditionsDisplaySessionHandlerFactory.getHandler();
        handler.removeSessions(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println("OnError method triggered");
        e.printStackTrace();
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        JSONObject json = new JSONObject(message);
        Long id = json.getLong("id");
        String status = json.getString("status");

        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();

        galaxiesDAO.updateExpeditionStatus(id, status);

        Expedition expedition = galaxiesDAO.getExpedition(id);
        ExpeditionsDisplaySessionHandler handler = ExpeditionsDisplaySessionHandlerFactory.getHandler();

        handler.amendExpedition(expedition);
    }

}
