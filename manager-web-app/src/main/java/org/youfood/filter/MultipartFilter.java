package org.youfood.filter;

import org.youfood.http.multipart.MultipartRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "location", value = "/upload")
})
public class MultipartFilter implements Filter {

    private static final String INIT_PARAM_LOCATION = "location";
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/";

    private String location;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.location = filterConfig.getInitParameter(INIT_PARAM_LOCATION);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (iSMultipartRequest(httpServletRequest)) {
            servletRequest = new MultipartRequest(httpServletRequest, location);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public static final boolean iSMultipartRequest(HttpServletRequest request) {
        return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod()) && request.getContentType() != null && request.getContentType().toLowerCase().startsWith(CONTENT_TYPE_MULTIPART);
    }
}
