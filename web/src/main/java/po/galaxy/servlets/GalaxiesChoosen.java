/*
 * File : GalaxiesChoosen.java
 * Description : result of Galaxies choice page
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : 01 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : result of user's check 
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesData;
import po.galaxy.domain.Galaxy;

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
		
		System.out.print("doPost->");
		
		HttpSession session = request.getSession();
		
		List<Galaxy> galaxies = new GalaxiesData().getGalaxiesList();
		
		List<? super Galaxy> galaxiesOfChoice = galaxies.stream()
												.filter(g-> {
													String status = request.getParameter("galaxy-" + g.getId());
													return (status != null && status.equals("on")) ? true : false;
												})
												.collect(Collectors.toList());

		session.setAttribute("choosenGalaxies", galaxiesOfChoice);
		
		response.sendRedirect(response.encodeURL("youChoice.html"));
		
	}

}
