package com.youssef.jpatsk.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ValidationReportFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String validationReport = request.getHeader("x-validation-report");

        if (hasAccess(validationReport)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Missing request headers/Invalid Request headers");
    }

    private boolean hasAccess(String header) {
        return "true".equalsIgnoreCase(header);
    }
}
