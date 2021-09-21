package com.example.fliter.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author do
 */
public final class LoginInfoUtils {
    private static final ThreadLocal<LoginInfo> USER_INFO_CURRENT = new ThreadLocal();

    private LoginInfoUtils() {
    }

    public static Long getUserId() {
        return getUserId((LoginInfo) USER_INFO_CURRENT.get());
    }


    public static String getLoginName() {
        return getLoginName((LoginInfo) USER_INFO_CURRENT.get());
    }

    public static Long getUserId(LoginInfo loginInfo) {
        return null == loginInfo ? null : loginInfo.getUserId();
    }

    public static String getLoginName(LoginInfo loginInfo) {
        return null == loginInfo ? null : loginInfo.getLoginName();
    }

    public static String getLoginName(HttpServletRequest request) {
        return null == request ? null : request.getHeader("username");
    }

    public static LoginInfo getLoginInfo() {
        LoginInfo loginInfo = USER_INFO_CURRENT.get();
        if (null == loginInfo) {
            loginInfo = new LoginInfo();
        }
        return loginInfo;
    }


    public static void remove() {
        USER_INFO_CURRENT.remove();
    }


    public static void setUserInfoCurrent(LoginInfo loginInfo) {
        USER_INFO_CURRENT.set(loginInfo);
    }
}
