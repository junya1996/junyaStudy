package com.junya.spring.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author Junya
 * Date 2020-08-14 13:57
 * Description:
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
