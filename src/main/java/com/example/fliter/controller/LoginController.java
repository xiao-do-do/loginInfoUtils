package com.example.fliter.controller;

import com.example.fliter.utils.LoginInfo;
import com.example.fliter.utils.LoginInfoUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author do
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(String username, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (username != null && !"".equals(username)) {
            session.setAttribute("username", username);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setLoginName(username);
            LoginInfoUtils.setUserInfoCurrent(loginInfo);
        } else {
            return "no param username";
        }

        return "login:"+username;
    }
}
