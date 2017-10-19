package ee.kertmannik.quiz.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CorrectAnswer {

    private String questionId;
    private List<String> answers;

    public CorrectAnswer(String questionId, List<String> answers){
        this.questionId = questionId;
        this.answers = answers;
    }

    public List<String> getCorrectAnswer() {
        return this.answers;
    }

    public String getQuestionId() {
        return this.questionId;
    }
}
