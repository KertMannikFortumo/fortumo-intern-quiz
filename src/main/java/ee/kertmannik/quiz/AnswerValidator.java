package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;

public class AnswerValidator {

    public String validateAnswer(Answer answer) {
        final String id = answer.getQuestionId();
        final String answerText = answer.getAnswer();
        if ("42".equals(id) && "Lars".equals(answerText)) {
            return "correct";
        } else {
            return "wrong";
        }
    }
}
