/*
 * File : ExpeditionsAsyncTask.java
 * Description : Async get expeditions list
 *
 * Author : Popov Denys
 * Created : 25/02/18
 *
 * Modified : { date: 25/02/18
 *             ,time: 02:26 PM }
 * Modified by: Popov Denys
 *
 * Last modification : added asyncContext
 */

package po.galaxy.tasks;

import po.galaxy.db.GalaxiesDAO;
import po.galaxy.db.GalaxiesDaoFactory;
import po.galaxy.domain.Expedition;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExpeditionsAsyncTask implements Runnable {

    private AsyncContext asyncContext;

    public void setAsyncContext(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {

        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        try(PrintWriter out = response.getWriter()) {

            response.setContentType("text/html");

            Long size = Long.parseLong(request.getParameter("size"));

            System.out.println("Looking for expedition with # " + size);

            GalaxiesDAO galaxiesDAO = GalaxiesDaoFactory.getGalaxiesDAO();

            while(galaxiesDAO.getExpeditionsList().size() < size) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Expedition expedition = galaxiesDAO.getExpedition(size);
            System.out.println(expedition);
            out.println("<p><b>Next expedition to:</b>" + expedition.toString() + "</p>");
            out.println(String.format("Contractor is <i>%s</i>", expedition.getContractor()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            asyncContext.complete();
        }
    }
}
