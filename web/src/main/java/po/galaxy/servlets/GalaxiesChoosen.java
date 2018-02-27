/*
 * File : GalaxiesChoosen.java
 * Description : result of Galaxies choice page
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 *
 * Modified : { date: 01/02/18
 *             ,time: 08:05 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : result of user's check 
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;
import po.galaxy.domain.Galaxy;
import po.galaxy.domain.StatusType;
import po.galaxy.websockets.ExpeditionsDisplaySessionHandler;
import po.galaxy.websockets.ExpeditionsDisplaySessionHandlerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/choosenRoute.html")
public class GalaxiesChoosen extends HttpServlet {

	private static final long serialVersionUID = 3189278280033985769L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();
		
		System.out.print("doPost->");
		

		List<Galaxy> galaxies = galaxiesDAO.getGalaxiesList();

		Expedition expedition = galaxiesDAO.setExpedition( request.getParameter("contractor") );

		List<? super Galaxy> galaxiesOfChoice = galaxies.stream()
				.filter(g-> {
					String status = request.getParameter("galaxy-" + g.getId());
					return (status != null && status.equals("on")) ? true : false;
				})
				.peek(galaxy->
						expedition.setItinerary(
								galaxiesDAO.addToExpedition(expedition.getId(), galaxy)
						)
				)
				.collect(Collectors.toList());

		System.out.println(String.format("A new expedition has been choosen with the status : %s", StatusType.PROJECT.get()));
		ExpeditionsDisplaySessionHandler handler = ExpeditionsDisplaySessionHandlerFactory.getHandler();
		handler.newExpedition(expedition);

		HttpSession session = request.getSession();
		session.setAttribute("choosenGalaxies", galaxiesOfChoice);
		session.setAttribute("expeditionId", expedition.getId());
		
		response.sendRedirect(response.encodeURL("youChoice.html"));

	}

}
