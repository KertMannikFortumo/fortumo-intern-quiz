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

    private QuestionRepository questionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.questionRepository = (QuestionRepository) config.getServletContext().getAttribute("Injecting QuestionRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        String json = questionRepository.jsonAnswers();
        response.getWriter().print(json);
    }
}
