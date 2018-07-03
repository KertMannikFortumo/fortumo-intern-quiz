package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyServletContextListener")
public class MyServletContextListener implements ServletContextListener {

    public static final String ANSWER_CONTROLLER = "AnswerController";
    public static final String QUESTION_CONTROLLER = "QuestionController";
    public static final String GIST_URL =
            "https://gist.githubusercontent.com/kertmannik/2a69e0d7b3ad9ca1a648893f8f52b7b8/raw/d6455a0ea7a3da65733468a9b47be950c9f8e29a/questions.txt";
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        QuestionSupplier questionSupplier = new QuestionSupplier(GIST_URL);
        String rawData = questionSupplier.questionsRequest();

        RawQuestionParser rawQuestionParser = new RawQuestionParser();
        List<Question> questions = rawQuestionParser.splittingRawQuestions(rawData);
        QuestionRepository questionRepository = new QuestionRepository(questions);
        AnswerValidator answerValidator = new AnswerValidator(questionRepository);

        QuestionController questionController = this.getQuestionController(questionRepository);

        AnswerController answerController = this.getAnswerController(answerValidator);
        sce.getServletContext().setAttribute(QUESTION_CONTROLLER, questionController);
        sce.getServletContext().setAttribute(ANSWER_CONTROLLER, answerController);
    }

    protected AnswerController getAnswerController(AnswerValidator answerValidator) {
        return new AnswerController(answerValidator);
    }

    protected QuestionController getQuestionController(QuestionRepository questionRepository) {
        return new QuestionController(questionRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
