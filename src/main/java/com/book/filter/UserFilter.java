package com.book.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
//        if(session.getAttribute("user") == null){
//            response.sendRedirect("/login");
//        }
        if(session.getAttribute("manager") == null){
            response.sendRedirect("/managerLogin");
        }
//        else if (session.getAttribute("manager")==null){
//            response.sendRedirect("/managerLogin");
//        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
