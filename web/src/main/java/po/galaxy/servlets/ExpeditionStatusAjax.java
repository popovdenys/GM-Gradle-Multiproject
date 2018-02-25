/*
 * File : ExpeditionStatusAjax.java
 * Description : prepare json ajax response
 *
 * Author : Popov Denys
 * Created : 23 Feb, 2018
 *
 * Modified : { date: 24/02/18
 *             ,time: 08:07 PM }
 * Modified by: Popov Denys
 *
 * Last modification : set actual time and current status
 */

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

        json.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        out.write(json.toString());
        out.close();
    }
}