package com.seecen.sc1709.emp.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/19.
 */
public class WebUtil {

    public static final String BASE_URL = "/WEB-INF/view/";
    /**
     * 转发
     *
     * @param request
     * @param response
     * @param url
     */
    public static void go(HttpServletRequest request,
                          HttpServletResponse response, String url)
            throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
    }
    /**
     * 重定向
     * @param response
     * @param url
     * @throws IOException
     */
    public static void go(HttpServletResponse response, String url)
            throws IOException {
        response.sendRedirect(url);
    }
}
