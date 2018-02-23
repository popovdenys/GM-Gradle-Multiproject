/*
 * File : FinalizeChoice.java
 * Description : result of Galaxies choice page
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : 01 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : page of redirect from post to get method 
 */

package po.galaxy.servlets;

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

		if (choosenGalaxies == null ) response.sendRedirect("choose.html");
		else dispatchToResultPage(request, response);
			
	}

	private void dispatchToResultPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/youChoice.jsp");

		dispatcher.forward(request, response);

	}

}
