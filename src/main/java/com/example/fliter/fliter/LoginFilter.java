package com.example.fliter.fliter;

import com.example.fliter.utils.LoginInfo;
import com.example.fliter.utils.LoginInfoUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author do
 */
@Component
public class LoginFilter implements Filter {
    private String[] filterExclusionUrls = {"/login"};


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("init filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("do filter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

            if (isFliter(url)) {
                HttpSession session = httpServletRequest.getSession();
                Object username = session.getAttribute("username");
                if (username == null || "".equals(username.toString())) {
                    response.getWriter().append("not login");
                    return;
                } else {
                    System.out.println("login:" + username);
                    LoginInfo loginInfo = new LoginInfo();
                    loginInfo.setLoginName(username.toString());
                    LoginInfoUtils.setUserInfoCurrent(loginInfo);
                    filterChain.doFilter(request, response);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {

        } finally {
            System.out.println("threadLocal remove loginInfo");
            LoginInfoUtils.remove();
        }


    }

    /**
     * 判断该url是否需要过滤
     * @param url
     * @return
     */
    private boolean isFliter(String url) {
        for (String exclusion : filterExclusionUrls) {
            if (exclusion.equals(url)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void destroy() {
        System.out.println("do destroy");
    }
}