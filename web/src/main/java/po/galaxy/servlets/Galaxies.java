/*
 * File : Galaxies.java
 * Description : welcome Galaxies' page
 * 
 * Author : Popov Denys
 * Created : 30 Jan, 2018
 * 
 * Modified : 30 Jan, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : welcome list of Galaxies
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesData;
import po.galaxy.domain.Galaxy;
import po.galaxy.domain.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/galaxy.html")
public class Galaxies extends HttpServlet {

	private static final long serialVersionUID = 2694378448176264111L;

	public void init() {
		System.out.println("From init");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		List<Galaxy> galaxies = new GalaxiesData().getAllGalaxies();

		out.println("<html><body><h2>Known Galaxies</h2>");
		out.println("<ul>");
		galaxies.stream().forEach(Pattern.infoListNote(out));
		out.println("</ul>");
		
		out.println();

		out.println("<a href=\"search.html\">Find your route</a> | ");
		out.println("<a href=\"choose.html\">Make your choice</a>");

		out.println("</body></html>");
		
		out.close();

	}

}
