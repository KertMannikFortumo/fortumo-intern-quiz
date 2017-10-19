package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Question;
import ee.kertmannik.quiz.model.QuestionWithoutAnswer;

import java.util.List;

public class QuestionRepository {

    public List<Question> getAllQuestions() {
        return this.getQuestionsFromGist();
    }

    private List<Question> getQuestionsFromGist() {
        final GetRequestFromGist getRequestFromGist = new GetRequestFromGist();
        final RawQuestionParser rawQuestionParser = new RawQuestionParser();
        final String rawData = getRequestFromGist.questionsRequest();
        return rawQuestionParser.splittingRawQuestions(rawData);
    }

    public String getOneQuestion(int questionGetCounter) {
        final List<Question> questions = MyServletContextListener.questions;
        Question question;

        if (questionGetCounter >= questions.size()) {
            question = questions.get(0);
        } else {
            question = questions.get(questionGetCounter);
        }
        Gson gson = new Gson();
        return gson.toJson(this.sendQuestionWithoutAnswer(question));
    }

    private QuestionWithoutAnswer sendQuestionWithoutAnswer(Question question) {
        QuestionWithoutAnswer sendableQuestion = null;
        sendableQuestion.setCategory(question.getCategory());
        sendableQuestion.setDifficulty(question.getDifficulty());
        sendableQuestion.setQuestion(question.getQuestion());
        sendableQuestion.setQuestionId(question.getQuestionId());
        return sendableQuestion;
    }
}
