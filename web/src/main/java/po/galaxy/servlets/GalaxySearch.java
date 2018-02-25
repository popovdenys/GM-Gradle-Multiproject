/*
 * File : Goo.java
 * Description : search result
 * 
 * Author : Popov Denys
 * Created : 03 Feb, 2018
 *
 * Modified : { date: 24/02/18
 *             ,time: 08:05 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : result page of Galaxies search 
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesData;
import po.galaxy.domain.Galaxy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchResult.html")
public class GalaxySearch extends HttpServlet {

	private static final long serialVersionUID = 4300872895946022649L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getSearchResult(request, response);
	}

	private void getSearchResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String searchCriteria = request.getParameter("searchCriteria");
		List<Galaxy> foundGalaxies = new GalaxiesData().find(GalaxiesData.byName(searchCriteria));
		
		request.setAttribute("foundGalaxies", foundGalaxies);
		request.setAttribute("countOfGalaxies", foundGalaxies.size());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchResult.jsp");

		dispatcher.forward(request, response);
	}
}
