package ru.rsreu.restaurantAutomation.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static final String UTF_8 = "UTF-8";
    private String encoding;

    public void init(FilterConfig config) {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = UTF_8;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(UTF_8);
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
