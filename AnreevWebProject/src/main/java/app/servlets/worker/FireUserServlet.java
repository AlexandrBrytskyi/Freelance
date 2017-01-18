package app.servlets.worker;

import app.services.WorkerManimulationService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/fire"})
public class FireUserServlet extends HttpServlet {

    WorkerManimulationService workerManimulationService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        workerManimulationService = spring_context.getBean(WorkerManimulationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        int userToFireId = Integer.parseInt(req.getParameter("userToFireId"));
        int posadaId = Integer.parseInt(req.getParameter("posada"));
        int otdelId = Integer.parseInt(req.getParameter("otdel"));

        String message;
        if (workerManimulationService.fireUser(token, userToFireId, posadaId)) {
            message = "User with personal number " + userToFireId + " was fired";
        } else {
            message = "Have some problems, cant fire";
        }

        resp.sendRedirect("/otdel?otdelID=" + otdelId + "&errorMessage=" + message);
    }

}
