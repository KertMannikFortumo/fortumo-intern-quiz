package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Answer;

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

    public static final String ANSWER_VALIDATOR = MyServletContextListener.ANSWER_VALIDATOR;

    private Gson gson = new Gson();
    private AnswerValidator answerValidator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.answerValidator = (AnswerValidator) config.getServletContext().getAttribute(ANSWER_VALIDATOR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String rawRequestBody = this.getRequestBody(req);
        final Answer answer = this.gson.fromJson(rawRequestBody, Answer.class);
        final String result = this.answerValidator.validateAnswer(answer);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("plain/text");
        resp.getWriter().write(result);
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
