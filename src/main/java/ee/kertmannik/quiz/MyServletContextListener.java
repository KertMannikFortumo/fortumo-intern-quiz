package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyServletContextListener")
public class MyServletContextListener implements ServletContextListener {

    public static final String ANSWER_VALIDATOR = "AnswerValidator";
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
        QuestionController questionController = new QuestionController(questionRepository);
        sce.getServletContext().setAttribute(QUESTION_CONTROLLER, questionController);

        final AnswerValidator answerValidator = this.createAnswerValidator();

        sce.getServletContext().setAttribute(ANSWER_VALIDATOR, answerValidator);
    }

    protected AnswerValidator createAnswerValidator() {
        return new AnswerValidator();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
