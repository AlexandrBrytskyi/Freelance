package app.servlets.worker;

import app.model.User;
import app.services.PosadasManipulatingService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {

    PosadasManipulatingService posadasManipulatingService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        posadasManipulatingService = spring_context.getBean(PosadasManipulatingService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.checkCookies(req, resp);

        int otdel = Integer.parseInt(req.getParameter("otdelId"));
        System.out.println("id of otdel " + otdel);
        req.getSession().setAttribute("otdel", otdel);

        req.getRequestDispatcher("/WEB-INF/pages/AddUserPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        int otdelId = (int) req.getSession().getAttribute("otdel");
        boolean canCreateOtdel = req.getParameter("canCreateOtdel") != null;
        req.setAttribute("otdelID", otdelId);

        req.getRequestDispatcher("/WEB-INF/pages/AddUserPage.jsp");

        User added = posadasManipulatingService.addNewUser(token, otdelId, canCreateOtdel);

        String message = "Added user with number " + added.getUserPersonalNum();

        resp.sendRedirect("/otdel?otdelID=" + added.getOtdel().getId() + "&errorMessage=" + message);

    }
}
