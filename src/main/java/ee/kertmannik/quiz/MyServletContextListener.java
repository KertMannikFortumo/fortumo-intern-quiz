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

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String gistUrl =
                "https://gist.githubusercontent.com/KertMannikFortumo/6b17dca9c9ae8ff089d3c50aa7a03329/raw/01cbbd75ed39d917d008881ee6db8f140663a17a/gistfile1.txt";
        QuestionSupplier questionSupplier = new QuestionSupplier(gistUrl);
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
