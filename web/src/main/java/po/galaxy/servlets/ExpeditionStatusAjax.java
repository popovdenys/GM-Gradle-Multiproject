package po.galaxy.servlets;

import org.json.JSONObject;
import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/updatedExpeditionStatus")
public class ExpeditionStatusAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();
        String status = galaxiesDAO.getExpedition(id).getStatus();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject json = new JSONObject();
        json.put("status", status);

        json.put("time", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        out.write(json.toString());
        out.close();
    }
}
