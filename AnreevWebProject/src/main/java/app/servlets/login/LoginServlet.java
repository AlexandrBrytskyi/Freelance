package app.servlets.login;


import app.model.exceptions.UserMustRegisterException;
import app.model.exceptions.WrongDataException;
import app.services.RegistrationLoginService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/login", "/"})
public class LoginServlet extends HttpServlet {

    private RegistrationLoginService registrationLoginService;


    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        registrationLoginService = spring_context.getBean(RegistrationLoginService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginInput");
        String passWord = req.getParameter("passwordInput");

        System.out.println("login " + login + ", password " + passWord);

        try {
            String token = registrationLoginService.login(Integer.valueOf(login), passWord);
            resp.addCookie(new Cookie("token", token));
            resp.sendRedirect("/user");
        } catch (NumberFormatException e) {
            /*forward on the same page but show message*/
            req.setAttribute("errorMessage", "Wrong login");
            req.getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp").forward(req, resp);
        } catch (WrongDataException e) {
            /*forward on the same page but show message*/
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/LoginPage.jsp").forward(req, resp);
        } catch (UserMustRegisterException e) {
            /*forward on register page
            with token*/
            String token = e.getToken();
            resp.addCookie(new Cookie("token", token));

            resp.sendRedirect("/register");
            return;
        }
    }
}
