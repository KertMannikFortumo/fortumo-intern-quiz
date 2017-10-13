package ee.kertmannik.quiz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyServletContextListener")
public class MyServletContextListener  implements ServletContextListener {

    public static final String ANSWER_VALIDATOR = "AnswerValidator";
    public static final String QUESTION_REPOSITORY = "QuestionRepository";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final AnswerValidator answerValidator = this.createAnswerValidator();
        final QuestionRepository questionRepository = this.createQuestionRepository();

        sce.getServletContext().setAttribute(ANSWER_VALIDATOR, answerValidator);
        sce.getServletContext().setAttribute(QUESTION_REPOSITORY, questionRepository);

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
