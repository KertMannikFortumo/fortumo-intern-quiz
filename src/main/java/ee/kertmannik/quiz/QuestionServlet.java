package ee.kertmannik.quiz;

import java.io.IOException;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.questionRepository = (QuestionRepository) config.getServletContext().getAttribute(
                QUESTION_REPOSITORY);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        final String responseBody = this.questionRepository.getAllQuestions();
        final String json = responseBody;
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().print(json);
    }
}
