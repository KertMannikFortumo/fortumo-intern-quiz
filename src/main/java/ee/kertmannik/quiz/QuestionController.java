package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Question;

public class QuestionController {

    private final QuestionRepository questionRepository;
    private final Gson gson;

    QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.gson = new Gson();
    }
    public String getNextQuestionJson(){
        final Question question = questionRepository.getNextQuestion();
        return gson.toJson(question);
    }
}
