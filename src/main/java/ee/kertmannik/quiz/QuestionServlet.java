package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Question;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {

    public static final String QUESTION_REPOSITORY = MyServletContextListener.QUESTION_REPOSITORY;
    private QuestionRepository questionRepository;
    private int questionGetCounter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.questionRepository = (QuestionRepository) config.getServletContext().getAttribute(
                QUESTION_REPOSITORY);
        this.questionGetCounter = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String question = this.questionRepository.getOneQuestion(this.questionGetCounter);
        try {
            this.sendQuestion(response, question);
        } catch (Exception e) {
            System.out.println("Error!");
            //this.sendErrorMessage(response);
        }
        this.questionGetCounter += 1;
    }

    private void sendQuestion(HttpServletResponse response, String question) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().print(question);
    }
}
