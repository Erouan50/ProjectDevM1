package org.youfood.filter;

import org.youfood.controller.UserController;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@WebFilter(urlPatterns = {"/auth/*"})
public class ManagerFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (request.getServletPath().endsWith("login.xhtml")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (session != null) {
            UserController userController = (UserController) session.getAttribute("userController");
            if (userController != null) {
                if (userController.getUser() != null) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    @Override
    public void destroy() {

    }
}
