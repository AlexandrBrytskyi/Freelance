package app.servlets.worker;

import app.model.Posada;
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

@WebServlet(urlPatterns = {"/addPosada"})
public class AddPosadaServlet extends HttpServlet {

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

        int otdelid = Integer.parseInt(req.getParameter("otdel"));
        req.setAttribute("otdelId", otdelid);

        req.getRequestDispatcher("/WEB-INF/pages/AddPosadaPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        int otdelId = Integer.parseInt(req.getParameter("otdelId"));
        String posadaName = req.getParameter("posadaName");
        String description = req.getParameter("posadaDescription");
        double salary = Double.parseDouble(req.getParameter("salary"));


        Posada added = posadasManipulatingService.createNewPosada(posadaName, description, otdelId, salary, token);

        String message = "Added posada " + added.getPosadaName() + " with salary " + added.getSalary() + " in otdel #" + otdelId;

        resp.sendRedirect("/otdel?otdelID=" + otdelId + "&errorMessage=" + message);

    }
}
