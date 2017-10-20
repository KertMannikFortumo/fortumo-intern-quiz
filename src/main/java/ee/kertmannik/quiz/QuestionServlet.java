package ee.kertmannik.quiz;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ee.kertmannik.quiz.MyServletContextListener.QUESTION_CONTROLLER;

@WebServlet(urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {

    private QuestionController questionController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.questionController = (QuestionController) config.getServletContext().getAttribute(
                QUESTION_CONTROLLER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String responseBody = questionController.getNextQuestionJson();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().print(responseBody);
    }
}
