package app.servlets;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Utils {

    public static void setEncodingUTF8(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }

    public static String checkCookies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accessKey = null;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("token")) accessKey = cookie.getValue();
        }

        if (accessKey == null) resp.sendRedirect("/login");
        return accessKey;
    }
}
