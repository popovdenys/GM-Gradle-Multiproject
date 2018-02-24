package po.galaxy.servlets;

import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;
import po.galaxy.domain.StatusType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/processexpedition.html")
public class updateExpeditionStatus extends HttpServlet {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateExpeditionStatus.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();
        Long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        galaxiesDAO.updateExpeditionStatus(id, status);
        doGet(request, response);
    }
}
