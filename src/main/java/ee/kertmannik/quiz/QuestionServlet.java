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
        System.out.println(questions);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        Question question = getQuestion(questions);
        Gson gson = new Gson();
        String result = gson.toJson(question);
        response.getWriter().print(result);
        this.questionGetCounter += 1;

    }

    private Question getQuestion(List<Question> questions) {
        if (this.questionGetCounter == questions.size()) {
            return questions.get(0);
        } else {
            return questions.get(randomIntegerGenerator(questions.size()));
        }
    }

    private int randomIntegerGenerator(int maxNumber) {
        Random rand = new Random();
        return rand.nextInt(maxNumber);
    }
}
