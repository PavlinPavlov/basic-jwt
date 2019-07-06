package com.jwt.security;


import com.jwt.pojo.JwtUser;
import com.jwt.services.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/secure/*" })
public class JwtFilter implements Filter
{

    @Autowired
    private JwtService jwtTokenService;

    @Value("${jwt.auth.header}")
    String tokenHeader;

    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String token = httpRequest.getHeader(tokenHeader);

        if (null == token) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            JwtUser jwtUser = jwtTokenService.getUser(token);
            httpRequest.setAttribute("jwtUser", jwtUser);
        } catch(JwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }
}
