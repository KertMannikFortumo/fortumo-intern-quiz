package ee.kertmannik.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/answer")
public class AnswerServlet extends HttpServlet {

    private AnswerValidator answerValidator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.answerValidator = (AnswerValidator) config.getServletContext().getAttribute("Injecting AnswerValidator");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String answer = this.getRequestBody(req);
        final String validatedAnswer = this.answerValidator.validateAnswer(answer);
        final int responseCode = HttpServletResponse.SC_OK;

        resp.setContentType("plain/text");
        resp.getWriter().write(validatedAnswer);
        resp.setStatus(responseCode);
        resp.getWriter().close();
    }

    protected String getRequestBody(final HttpServletRequest request) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        final BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
