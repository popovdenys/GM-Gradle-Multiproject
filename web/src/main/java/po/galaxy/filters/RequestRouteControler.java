package po.galaxy.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestRouteControler extends HttpServletRequestWrapper {

    private String redirectTo;

    public RequestRouteControler(HttpServletRequest request) {
        super(request);
    }

    public void setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
    }



    @Override
    public String getRequestURI() {

        return redirectTo;
    }
}
