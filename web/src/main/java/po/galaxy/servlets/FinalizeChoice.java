/*
 * File : FinalizeChoice.java
 * Description : result of Galaxies choice page
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 *
 * Modified : { date: 24/02/18
 *             ,time: 08:05 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : page of redirect from post to get method 
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Galaxy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/youChoice.html")
public class FinalizeChoice extends HttpServlet {

	private static final long serialVersionUID = -1823107474608612079L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doGet");
		
		HttpSession session = request.getSession();
		
		List<? super Galaxy> choosenGalaxies = (List<? super Galaxy>) session.getAttribute("choosenGalaxies");

		Long expeditionId = (Long) session.getAttribute("expeditionId");

		GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();

		Double totalDistance = galaxiesDAO.getExpetionTotalDistance(expeditionId);

		String status = galaxiesDAO.getExpedition(expeditionId).getStatus();

		if (choosenGalaxies != null ) {
			request.setAttribute("totalDistance", totalDistance);
			request.setAttribute("status", status);
			request.setAttribute("id", expeditionId);
			dispatchToResultPage(request, response);
		}
		else  response.sendRedirect("choose.html");

	}

	private void dispatchToResultPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/youChoice.jsp");

		dispatcher.forward(request, response);

	}

}
