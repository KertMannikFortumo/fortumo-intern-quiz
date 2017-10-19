package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.CorrectAnswer;
import ee.kertmannik.quiz.model.Question;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyServletContextListener")
public class MyServletContextListener implements ServletContextListener {

    public static final String ANSWER_VALIDATOR = "AnswerValidator";
    public static final String QUESTION_REPOSITORY = "QuestionRepository";
    public static List<Question> questions;
    public static List<CorrectAnswer> answers;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final AnswerValidator answerValidator = this.createAnswerValidator();
        final QuestionRepository questionRepository = this.createQuestionRepository();

        sce.getServletContext().setAttribute(ANSWER_VALIDATOR, answerValidator);
        sce.getServletContext().setAttribute(QUESTION_REPOSITORY, questionRepository);

        try {
            RawQuestionParser rawQuestionParser = new RawQuestionParser();

            questions = questionRepository.getAllQuestions();
            answers = rawQuestionParser.parseAnswersFromQuestions(questions);
        } catch (Exception exception) {
            System.out.println("Problem getting the questions from gist. " + exception);
        }
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
