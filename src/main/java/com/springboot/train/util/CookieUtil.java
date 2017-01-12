package com.springboot.train.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookieUtil
 */
public class CookieUtil {
	private final static Logger logger=LoggerFactory.getLogger(CookieUtil.class);

    private static Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setDomain(".kyfw.12306.cn");
        cookie.setMaxAge(86400 * 10000);//10000 DAYS
        return cookie;

    }

    /**
     * 设置COOKIE
     *
     * @param resp
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse resp, String cookieName, String cookieValue) {

        resp.addCookie(createCookie(cookieName, cookieValue));
        resp.setHeader("Access-Control-Allow-Origin", "https://kyfw.12306.cn"); //请求源
//        resp.setHeader("Access-Control-Allow-Methods","POST"); //请求方式POST, GET, OPTIONS
        resp.setHeader("Access-Control-Max-Age", "3600"); //有效期
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type","*"); //请求头类型
        resp.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
    }

    /**
     * 获取COOKIE
     *
     * @param request
     * @param cookieName
     * @return String
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        if (request == null) return null;
        if (cookieName == null) return null;
        if (cookieName.length() == 0) return null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
        {}
        else {
            logger.debug("cookie size:" + cookies.length);
            for (Cookie c : cookies) {
                logger.debug("cookie : " + c.getName() + " / " + c.getValue());
                if (c.getName().equalsIgnoreCase(cookieName)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
