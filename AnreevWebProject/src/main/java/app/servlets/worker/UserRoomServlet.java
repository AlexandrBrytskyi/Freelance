package app.servlets.worker;

import app.model.User;
import app.services.tokens.TokensContext;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserRoomServlet extends HttpServlet {

    TokensContext tokensContext;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        tokensContext = spring_context.getBean(TokensContext.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);
        User user = tokensContext.getTokens().get(token);

        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/pages/UserCabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
