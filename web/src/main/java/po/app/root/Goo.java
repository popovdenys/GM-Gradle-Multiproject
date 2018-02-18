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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index.html")
public class Goo extends HttpServlet {

	private static final long serialVersionUID = 3180054384041117662L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<h1>Design Patterns & Paradigms</h1>");
		out.print("<ul>");
		out.println("<li><a href=\"galaxy.html\">Post-Redirect-Get</a></li>");
		out.print("</ul>");
		out.close();
	}

}
