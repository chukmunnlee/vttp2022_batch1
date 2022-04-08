package vttp2022.paf.day15.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;

        // Get the HTTP session
        HttpSession sess = httpReq.getSession();
        String username = (String)sess.getAttribute("username");

        System.out.printf(">>>> url: %s\n", httpReq.getRequestURI().toString());
        System.out.printf(">>>> \t name: %s\n", username);

        if ((null == username) || (username.trim().length() <= 0)) {
            httpResp.sendRedirect("/index.html");
            return;
        }

        chain.doFilter(request, response);
    }
    
}
