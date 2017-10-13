package ee.kertmannik.quiz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AnswerValidator {
    public String validateAnswer(String requestBody){
        final Answer parsedJSON = this.parsingJson(requestBody);
        final String id = parsedJSON.getQuestionId();
        final String answer = parsedJSON.getAnswer();
        if ("42".equals(id) && "Lars".equals(answer)){
            return "correct";
        } else {
            return "wrong";
        }
    }

    private Answer parsingJson(String answer) {
        final Gson gson = new Gson();
        return gson.fromJson(answer, Answer.class);
    }
}
