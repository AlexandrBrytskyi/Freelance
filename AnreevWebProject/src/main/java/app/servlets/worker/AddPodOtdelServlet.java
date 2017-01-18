package app.servlets.worker;

import app.model.Otdel;
import app.services.OtdelManipulatingService;
import app.servlets.Utils;
import com.sun.deploy.net.HttpRequest;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add_podotdel"})
public class AddPodOtdelServlet extends HttpServlet {

    OtdelManipulatingService otdelManipulatingService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        otdelManipulatingService = spring_context.getBean(OtdelManipulatingService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.checkCookies(req, resp);

        int parentOtdelId = Integer.parseInt(req.getParameter("parentOtdelID"));

        req.setAttribute("parentOtdelID", parentOtdelId);

        getServletContext().getRequestDispatcher("/WEB-INF/pages/AddPodOtdelPage.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = Utils.checkCookies(req, resp);
        Utils.setEncodingUTF8(req, resp);

        int parentOtdelId = Integer.parseInt(req.getParameter("parentOtdelID"));
        String otdelName = req.getParameter("otdelName");
        String otdelDesc = req.getParameter("otdelDesc");

        Otdel created;
        try {
            created = otdelManipulatingService.creatPodeOtdel(token, otdelDesc, otdelName, parentOtdelId);
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("otdelName", otdelName);
            req.setAttribute("otdelDesc", otdelDesc);
            req.setAttribute("parentOtdelID", parentOtdelId);
            req.getRequestDispatcher("/WEB-INF/pages/AddPodOtdelPage.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/otdel?otdelID=" + created.getId());
    }

}
