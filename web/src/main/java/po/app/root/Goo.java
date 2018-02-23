/*
 * File : Goo.java
 *
 * Author : Popov Denys
 * Created : 03 Feb, 2018
 * 
 * Modified : 03 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : welcome page 
 */

package po.app.root;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index.html")
public class Goo extends HttpServlet {

	private static final long serialVersionUID = 3180054384041117662L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/index.jsp");

		dispatcher.forward(request, response);
	}

}
