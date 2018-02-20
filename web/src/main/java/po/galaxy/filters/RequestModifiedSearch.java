package po.galaxy.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestModifiedSearch extends HttpServletRequestWrapper {

    private String correctedSearch;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public RequestModifiedSearch(HttpServletRequest request) {
        super(request);
    }

    public void setCorrectedSearch(String correctedSearch) {
        this.correctedSearch = correctedSearch;
    }

    @Override
    public String getParameter(String name) {
        if(name.equals("searchCriteria")) {
            return correctedSearch;
        } else
            return super.getParameter(name);
    }

}
