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
import po.galaxy.domain.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
		else printResult(response, choosenGalaxies);
			
	}

	private void printResult(HttpServletResponse response, List<? super Galaxy> choosenGalaxies) throws IOException {
		response.setContentType("text/html");
		
		int[] choosenCount = {0};
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body><h2>Choosen Galaxies</h2>");

		out.println("<ul>");
				
		choosenGalaxies.stream().peek(g->++choosenCount[0]).forEach(Pattern.infoListNote(out));

		out.println("</ul>");

		Pattern.totalResultInfo(out, choosenCount[0]);
		
		Pattern.routesFooter(out);
		
		out.println("</body></html>");

		out.close();
	}



}
