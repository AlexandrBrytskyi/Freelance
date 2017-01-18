package app.servlets.worker;

import app.model.Otdel;
import app.services.OtdelManipulatingService;
import app.services.PersonalDataChangeService;
import app.services.tokens.TokensContext;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/changeInfo"})
public class ChangePersonalInfoServlet extends HttpServlet {

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

        req.setAttribute("user", tokensContext.getTokens().get(token));

        getServletContext().getRequestDispatcher("/WEB-INF/pages/ChangePersonalInfoPage.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = Utils.checkCookies(req, resp);

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String passport = req.getParameter("passport");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        personalDataChangeService.changePersonalInfo(token, name, surname, phone, address, passport);

        resp.sendRedirect("/user");
    }
}
