package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Answer;

import java.io.IOException;

public class AnswerController {

    private AnswerValidator answerValidator;
    private final Gson gson;

    AnswerController(AnswerValidator answerValidator) {
        this.answerValidator = answerValidator;
        this.gson = new Gson();
    }

    public String valuateAnswer(String rawRequest) throws IOException {
        Answer answer = this.gson.fromJson(rawRequest, Answer.class);
        return answerValidator.validateAnswer(answer);
    }
}
