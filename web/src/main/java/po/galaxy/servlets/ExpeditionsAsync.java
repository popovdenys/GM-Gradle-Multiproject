/*
 * File : ExpeditionsAsync.java
 * Description : create async Thread
 *
 * Author : Popov Denys
 * Created : 25/01/18
 *
 * Modified : { date: 25/02/18
 *             ,time: 02:27 AM }
 * Modified by: Popov Denys
 *
 * Last modification : get expeditions list
 */

package po.galaxy.servlets;

import po.galaxy.tasks.ExpeditionsAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/expeditionsServlet", asyncSupported = true)
public class ExpeditionsAsync extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync(request, response);
        ExpeditionsAsyncTask task = new ExpeditionsAsyncTask();
        task.setAsyncContext(asyncContext);
        asyncContext.start(task);
    }
}
