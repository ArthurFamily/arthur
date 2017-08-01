package com.cloud.arthur.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chenzhen on 2017/7/25.
 */
/*@WebFilter(filterName = "loginFilter", urlPatterns = "*//*")*/
public class LoginFilter implements Filter {

    private String encoding;
    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = "utf-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpSession session = httpServletRequest.getSession();
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            String url = httpServletRequest.getRequestURL().toString();

            if (StringUtils.isNotEmpty((String) session.getAttribute("account"))
                    || url.endsWith("/login.html") || url.endsWith("/webLogin") || url.endsWith("/help.html")
                    || url.endsWith("/main.html")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendRedirect("/login.html");
            }
        } catch (Exception e) {
            LOG.error("doFilter：" + e.getMessage() + "\n" + e.getStackTrace());
        }
    }

    @Override
    public void destroy() {}
}
