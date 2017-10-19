package ee.kertmannik.quiz;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
        try {
            Answer answer = this.gson.fromJson(rawRequest, Answer.class);
            return answerValidator.validateAnswer(answer);
        }catch (JsonSyntaxException exception) {
            throw new QuizException("Invalid json");
        }
    }
}
