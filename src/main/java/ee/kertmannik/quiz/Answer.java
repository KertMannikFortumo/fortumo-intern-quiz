package ee.kertmannik.quiz;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("question-id")
    private String questionId;
    private String answer;

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestionId() {
        return this.questionId;
    }
}
