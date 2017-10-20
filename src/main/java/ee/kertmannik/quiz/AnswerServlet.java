package ee.kertmannik.quiz;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ee.kertmannik.quiz.MyServletContextListener.ANSWER_CONTROLLER;

@WebServlet(urlPatterns = "/answer")
public class AnswerServlet extends HttpServlet {

    private AnswerController answerController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.answerController = (AnswerController) config.getServletContext().getAttribute(ANSWER_CONTROLLER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String rawRequestBody = this.getRequestBody(req);
        try {
            final String result = answerController.valuateAnswer(rawRequestBody);
            this.sendResponse(resp, result, HttpServletResponse.SC_OK);
        } catch (Exception exception) {
            this.sendErrorMessage(resp);
        }
    }

    private void sendErrorMessage(HttpServletResponse resp) throws IOException {
        this.sendResponse(resp, "Could not parse request body.", HttpServletResponse.SC_BAD_REQUEST);
    }

    private void sendResponse(HttpServletResponse resp, String responseBody, int statusCode) throws IOException {
        resp.setStatus(statusCode);
        resp.setContentType("plain/text");
        resp.getWriter().write(responseBody);
        resp.getWriter().close();
    }

    private String getRequestBody(final HttpServletRequest request) throws IOException {
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
