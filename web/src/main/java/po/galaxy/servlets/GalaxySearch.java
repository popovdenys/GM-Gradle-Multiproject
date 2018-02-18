/*
 * File : Goo.java
 * Description : search result
 * 
 * Author : Popov Denys
 * Created : 03 Feb, 2018
 * 
 * Modified : 03 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : result page of Galaxies search 
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

@WebServlet("/searchResult.html")
public class GalaxySearch extends HttpServlet {

	private static final long serialVersionUID = 4300872895946022649L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getSearchResult(request, response);
	}

	private void getSearchResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		
		String searchCriteria = request.getParameter("searchCriteria");
		List<Galaxy> foundGalaxies = new GalaxiesData().find(GalaxiesData.byName(searchCriteria));
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body><h3>Found route</h3>");
		out.println("<ul>");
		
		foundGalaxies.stream().forEach(Pattern.infoListNote(out));
		
		Pattern.totalResultInfo(out, foundGalaxies.size());
		
		Pattern.routesFooter(out);
		
		out.println("</ul></body></html>");
		out.close();
	}
}
