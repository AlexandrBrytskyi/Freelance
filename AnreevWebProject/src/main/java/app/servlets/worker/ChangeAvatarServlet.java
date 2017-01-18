package app.servlets.worker;

import app.model.exceptions.WrongDataException;
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
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/changePhoto"})
@MultipartConfig
public class ChangeAvatarServlet extends HttpServlet {

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

        req.getRequestDispatcher("/WEB-INF/pages/ChangePhotoPage.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = Utils.checkCookies(req, resp);

        Part imagePart = req.getPart("new");
        byte[] avatar = null;

        try (BufferedInputStream is = new BufferedInputStream(imagePart.getInputStream());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] byteBuffer = new byte[16384];

            while ((nRead = is.read(byteBuffer)) != -1) {
                buffer.write(byteBuffer, 0, nRead);
            }
            avatar = buffer.toByteArray();
        }

        personalDataChangeService.changePhoto(token, avatar);

        resp.sendRedirect("/user");
    }
}
