package po.galaxy.filters;

import po.galaxy.db.GalaxiesData;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static po.galaxy.db.GalaxiesData.randomId;
import static po.galaxy.db.GalaxiesData.randomIdTester;

@WebFilter(value={"/searchResult.html"})
public class ModifiedSearch implements Filter {
    @Override
    public void doFilter(
            ServletRequest request
            , ServletResponse response
            , FilterChain chain) throws IOException, ServletException {

        String searchCriteria = request.getParameter("searchCriteria");

        if(searchCriteria.toLowerCase().contains("home")){

            System.out.println("find Home");

            GalaxiesData avalableGalaxies = new GalaxiesData();

            RequestModifiedSearch wrapper = new RequestModifiedSearch((HttpServletRequest) request);

            try {
                wrapper.setCorrectedSearch(avalableGalaxies.find(
                        GalaxiesData.byId(randomIdTester(randomId())
                                .entrySet().iterator().next().getKey()))
                        .get(0).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            chain.doFilter(wrapper, response);

        } else

            chain.doFilter(request, response);

    }
}
