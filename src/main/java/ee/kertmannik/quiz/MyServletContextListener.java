package ee.kertmannik.quiz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyServletContextListener")
public class MyServletContextListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final AnswerValidator answerValidator = this.createAnswerValidator();
        final QuestionRepository questionRepository = this.createQuestionRepository();

        sce.getServletContext().setAttribute("Injecting AnswerValidator", answerValidator);
        sce.getServletContext().setAttribute("Injecting QuestionRepository", questionRepository);

    }

    protected AnswerValidator createAnswerValidator() {
        return new AnswerValidator();
    }
    protected QuestionRepository createQuestionRepository() {
        return new QuestionRepository();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
