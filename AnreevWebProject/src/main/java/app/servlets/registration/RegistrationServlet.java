package app.servlets.registration;


import app.services.RegistrationLoginService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {"/register"})
@MultipartConfig
public class RegistrationServlet extends HttpServlet {


    private RegistrationLoginService registrationLoginService;


    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        registrationLoginService = spring_context.getBean(RegistrationLoginService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.checkCookies(req, resp);


        req.getRequestDispatcher("/WEB-INF/pages/RegisterPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.setEncodingUTF8(req, resp);

        String token = Utils.checkCookies(req, resp);

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String passport = req.getParameter("passport");
        String password = req.getParameter("password");

        Part imagePart = req.getPart("avatar");
        byte[] avatar = null;

        try (BufferedInputStream is = new BufferedInputStream(imagePart.getInputStream());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] byteBuffer = new byte[16384];

            while ((nRead = is.read(byteBuffer)) != -1) {
                buffer.write(byteBuffer, 0, nRead);
            }
            avatar = buffer.toByteArray();
        }


        token = registrationLoginService.register(token, name, surname, phone, address, passport, avatar, password);
        resp.addCookie(new Cookie("token", token));

        resp.sendRedirect("/user");

    }


}
