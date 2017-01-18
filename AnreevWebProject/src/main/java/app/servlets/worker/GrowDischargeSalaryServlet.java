package app.servlets.worker;


import app.model.User;
import app.services.PosadasManipulatingService;
import app.services.WorkerManimulationService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/growdischarge"})
public class GrowDischargeSalaryServlet extends HttpServlet {


    WorkerManimulationService workerManimulationService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        workerManimulationService = spring_context.getBean(WorkerManimulationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.checkCookies(req, resp);

        int userId = Integer.parseInt(req.getParameter("userTochangeId"));
        int posadaId = Integer.parseInt(req.getParameter("posada"));
        boolean grow = req.getParameter("grow").equals("grow");

        req.getSession().setAttribute("userChaningID", userId);
        req.getSession().setAttribute("posada", posadaId);
        req.getSession().setAttribute("grow", grow);
        int otdel = Integer.parseInt(req.getParameter("otdel"));
        req.getSession().setAttribute("otdel", otdel);

        req.getRequestDispatcher("/WEB-INF/pages/SummaDenegPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        double money = Double.parseDouble(req.getParameter("money"));
        int user = (int) req.getSession().getAttribute("userChaningID");
        int posada = (int) req.getSession().getAttribute("posada");
        boolean grow = (boolean) req.getSession().getAttribute("grow");
        String description = req.getParameter("description");
        int odtel = (int) req.getSession().getAttribute("otdel");

        if (grow) {
            workerManimulationService.growSalary(token, user, posada, description, money);
        } else {
            workerManimulationService.dischargeSalary(token, user, posada, description, money);
        }

        req.getRequestDispatcher("/WEB-INF/pages/AddUserPage.jsp");

        String message = "salary was " + (grow ? "growed" : "discharged") + " on " + money + " because of " + description;

        resp.sendRedirect("/otdel?otdelID=" + odtel + "&errorMessage=" + message);

    }
}
