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
        List<Question> questions = this.questionRepository.getAllQuestions();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        String question = getQuestion(questions);
        response.getWriter().print(question);
        this.questionGetCounter += 1;
    }

    private String getQuestion(List<Question> questions) {
        Question question;
        if (this.questionGetCounter == questions.size()) {
            question = questions.get(0);
        } else {
            question = questions.get(randomIntegerGenerator(questions.size()));
        }
        Gson gson = new Gson();
        return gson.toJson(question); //return question with the answer
    }

    private int randomIntegerGenerator(int maxNumber) {
        Random rand = new Random();
        return rand.nextInt(maxNumber);
    }
}
