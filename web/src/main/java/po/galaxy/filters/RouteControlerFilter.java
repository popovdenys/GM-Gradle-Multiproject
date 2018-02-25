/*
 * File : RouteControlerFilter.java
 * Description : Redirect to definit pages
 *
 * Author : Popov Denys
 * Created : 19/02/18
 *
 * Modified : { date: 25/02/18
 *             ,time: 02:25 PM }
 * Modified by: Popov Denys
 *
 * Last modification : added async support
 */

package po.galaxy.filters;

import po.galaxy.servlets.Galaxies;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebFilter(value = "/*", asyncSupported = true)
public class RouteControlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String requestedPath = ((HttpServletRequest)request).getRequestURI();
        String requestedMethod = ((HttpServletRequest)request).getMethod();
        String servletPath = ((HttpServletRequest)request).getServletPath();

        if (requestedPath.endsWith("galaxy.jsp")) {

            String redirectTo = "galaxy.html";

            System.out.println( String.format("Redirect -> %s", redirectTo));

            Galaxies galaxies = new Galaxies();

            try {
                Method method = galaxies.getClass().getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
                method.setAccessible(true);
                method.invoke(galaxies, request, response);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {

            System.out.println( String.format("Let's go to %s", requestedPath));

            chain.doFilter(request, response);
        }

    }
}
