package app.servlets.worker;

import app.model.exceptions.WrongDataException;
import app.services.PersonalDataChangeService;
import app.services.tokens.TokensContext;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/changePass"})
public class ChangePasswordServlet extends HttpServlet {

    private PersonalDataChangeService personalDataChangeService;
    private TokensContext tokensContext;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        personalDataChangeService = spring_context.getBean(PersonalDataChangeService.class);
        tokensContext = spring_context.getBean(TokensContext.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        req.getRequestDispatcher("/WEB-INF/pages/ChangePasswordPage.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = Utils.checkCookies(req, resp);

        String old = req.getParameter("old");

        String newPas = req.getParameter("new");

        String newSubmit = req.getParameter("newSubmit");

        if (!newPas.equals(newSubmit)) {
            req.setAttribute("errorMessage", "Different passwords, check your new password");
            req.getRequestDispatcher("/WEB-INF/pages/ChangePasswordPage.jsp").forward(req, resp);
        } else {
            try {
                personalDataChangeService.changePassWord(token, newPas, old);
                resp.sendRedirect("/user");
            } catch (WrongDataException e) {
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/pages/ChangePasswordPage.jsp").forward(req, resp);
            }
        }

    }
}
