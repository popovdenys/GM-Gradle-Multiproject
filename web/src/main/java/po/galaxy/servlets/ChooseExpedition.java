/*
 * File : GalaxiesOfChoice.java
 * Description : check Galaxies interface
 * 
 * Author : Popov Denys
 * Created : 01/02/18
 *
 * Modified : { date: 01/02/18
 *             ,time: 08:07 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : checkboxes interface 
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

@WebServlet("/chooseExpedition.html")
public class ChooseExpedition extends HttpServlet {

	private static final long serialVersionUID = 2173022719737730840L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Galaxy> galaxies = new GalaxiesData().getGalaxiesList();
		
		request.setAttribute("galaxiesOfChoice", galaxies);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/chooseExpedition.jsp");

		dispatcher.forward(request, response);
		
	}
	
}
