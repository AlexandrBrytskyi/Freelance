package app.servlets.worker;

import app.model.Posada;
import app.services.WorkerManimulationService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/hire"})
public class HireUserServlet extends HttpServlet {

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


        int userToHireId = Integer.parseInt(req.getParameter("userToHireId"));
        int otdel = Integer.parseInt(req.getParameter("otdelId"));


        req.getSession().setAttribute("userToHireId", userToHireId);
        req.getSession().setAttribute("otdel", otdel);
        List<Posada> workOfOtdel = workerManimulationService.getWorkOfOtdel(otdel);
        workOfOtdel = workOfOtdel.stream().filter(posada -> (posada.getUserWorking() == null)).collect(Collectors.toList());
        req.setAttribute("jobs", workOfOtdel);

        req.getRequestDispatcher("/WEB-INF/pages/HireUserPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        int otdel = (int) req.getSession().getAttribute("otdel");
        int user = (int) req.getSession().getAttribute("userToHireId");
        int job = Integer.parseInt(req.getParameter("job"));

        workerManimulationService.addPosadaToExistingUser(token, job, user);

        resp.sendRedirect("/otdel?otdelID=" + otdel);

    }
}
