package filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class LoggingFilter implements Filter {
    private ServletContext servletContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.servletContext.log("Logging");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> param = request.getParameterNames();
        while (param.hasMoreElements()){
            String username = param.nextElement();
            String name = servletRequest.getParameter("name");
            this.servletContext.log(request.getRemoteAddr() + "::Request Params::{"+username+"="+name+"}");
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                this.servletContext.log(request.getRemoteAddr());
                this.servletContext.log(cookie.getName());
                this.servletContext.log(cookie.getValue());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
