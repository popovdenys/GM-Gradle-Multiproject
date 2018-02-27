/*
 * File : UpdateExpeditionStatus.java
 * Description : Update expedition status
 *
 * Author : Popov Denys
 * Created : 23 Feb, 2018
 *
 * Modified : { date: 24/02/18
 *             ,time: 08:17 PM }
 * Modified by: Popov Denys
 *
 * Last modification : List of expeditions
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;
import po.galaxy.domain.StatusType;
import po.galaxy.websockets.ExpeditionsDisplaySessionHandler;
import po.galaxy.websockets.ExpeditionsDisplaySessionHandlerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateExpeditionStatus.html")
public class UpdateExpeditionStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();

        List<Expedition> expeditions = galaxiesDAO.getExpeditionsList();

        request.setAttribute("expeditions", expeditions);

        List<String> status = new ArrayList<>();
        status.add(StatusType.PROJECT.get());
        status.add(StatusType.PREPARE.get());
        status.add(StatusType.VERIFY.get());
        status.add(StatusType.START.get());
        status.add(StatusType.TRACKING.get());

        request.setAttribute("status", status);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/updateExpeditionStatus.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();
        Long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        galaxiesDAO.updateExpeditionStatus(id, status);

        Expedition expedition = galaxiesDAO.getExpedition(id);
        ExpeditionsDisplaySessionHandler handler = ExpeditionsDisplaySessionHandlerFactory.getHandler();

        handler.amendExpedition(expedition);

        doGet(request, response);

    }
}
