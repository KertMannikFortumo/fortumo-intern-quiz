package ee.kertmannik.quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "question")
public class QuestionServlet extends HttpServlet {

    QuestionDatabase questionDatabase = new QuestionDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        String json = questionDatabase.jsonAnswers();
        response.getWriter().println(json);
    }
}
