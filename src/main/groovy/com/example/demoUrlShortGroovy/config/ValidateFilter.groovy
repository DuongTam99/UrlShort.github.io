package com.example.demoUrlShortGroovy.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory


import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse;

class ValidateFilter implements Filter{

    //private final static Logger LOG = LoggerFactory.getLogger(ValidateFilter.class);

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig)
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        LOG.info("Starting Transaction for req :{}", req.getRequestURI());
     //   println request.toString();
        println("co di qua")

        chain.doFilter(request,response)


    }

    @Override
    void destroy() {
        super.destroy()
    }
}
