package ee.kertmannik.quiz;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})
public class IdentificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpServletRequest req = (HttpServletRequest) request;
        final String candidateName = req.getHeader("x-player-name");
        if (candidateName != null && !candidateName.isEmpty()) {
            chain.doFilter(request, response);
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Please use x-player-name header!");
        }
    }

    @Override
    public void destroy() {

    }
}
