/*
 * File : Galaxies.java
 * Description : welcome Galaxies' page
 * 
 * Author : Popov Denys
 * Created : 30 Jan, 2018
 *
 * Modified : { date: 30/01/18
 *             ,time: 08:05 PM }
 * Modified by: Popov Denys
 * 
 * Last modification : welcome list of Galaxies
 */

package po.galaxy.servlets;

import po.galaxy.db.GalaxiesDAO;
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

@WebServlet("/galaxy.html")
public class Galaxies extends HttpServlet {

	private static final long serialVersionUID = 2694378448176264111L;

	public void init() {}

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Galaxy> galaxies = new GalaxiesData().getGalaxiesList();
		//galaxies.stream().forEach(Pattern.infoListNote(out));

        new GalaxiesDAO();

        request.setAttribute("galaxies", galaxies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/galaxy.jsp");

        dispatcher.forward(request, response);
	}

}
