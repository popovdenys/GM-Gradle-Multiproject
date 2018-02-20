/**
 * File : RequestModifiedSearch.java
 * Description : wrapper for Search filter
 *
 * Author : Popov Denys
 * Created : 19/02/18
 *
 * Modified : { date: 19/02/18
 *             ,time: 10:45 PM }
 * Modified by: Popov Denys
 *
 * Last modification : modify param value
 */

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
