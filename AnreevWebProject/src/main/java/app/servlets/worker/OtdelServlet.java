package app.servlets.worker;

import app.model.Otdel;
import app.services.OtdelManipulatingService;
import app.servlets.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/otdel"})
public class OtdelServlet extends HttpServlet {

    OtdelManipulatingService otdelManipulatingService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        otdelManipulatingService = spring_context.getBean(OtdelManipulatingService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        String errorMessage = req.getParameter("errorMessage");
        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
        }

        int otdelId = Integer.parseInt(req.getParameter("otdelID"));

        Otdel otdel = otdelManipulatingService.getOtdel(otdelId);

        req.setAttribute("otdel", otdel);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/OtdelPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Utils.checkCookies(req, resp);

        Otdel otdel = (Otdel) req.getAttribute("otdel");

        req.setAttribute("otdel", otdel);

        req.getRequestDispatcher("/WEB-INF/pages/OtdelPage.jsp").forward(req, resp);
    }


}
