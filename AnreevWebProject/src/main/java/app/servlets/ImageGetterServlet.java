package app.servlets;


import app.model.User;
import app.services.tokens.TokensContext;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/getAvatar")
public class ImageGetterServlet extends HttpServlet {

    private TokensContext tokensContext;


    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext spring_context = (ApplicationContext) getServletContext().getAttribute("spring_context");
        tokensContext = spring_context.getBean(TokensContext.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toke = Utils.checkCookies(req, resp);

        User user = tokensContext.getTokens().get(toke);
        resp.setContentType("image/jpeg");
        try (ByteArrayInputStream is = new ByteArrayInputStream(user.getAvatar());
             OutputStream os = resp.getOutputStream()) {
            int nRead;
            byte[] byteBuffer = new byte[16384];

            while ((nRead = is.read(byteBuffer)) != -1) {
                os.write(byteBuffer, 0, nRead);
            }
        }
    }

}
