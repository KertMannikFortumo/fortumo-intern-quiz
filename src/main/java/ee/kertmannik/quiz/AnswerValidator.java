package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;
import ee.kertmannik.quiz.model.CorrectAnswer;

import java.util.List;

public class AnswerValidator {

    public String validateAnswer(Answer answer) {
        final String answerId = answer.getQuestionId();
        final String answerText = answer.getAnswer();
        final List<CorrectAnswer> correctAnswerList = MyServletContextListener.answers;

        for (CorrectAnswer correctAnswer : correctAnswerList) {
            if (correctAnswer.getQuestionId().equals(answerId)) {
                if (correctAnswer.getCorrectAnswer().contains(answerText)) {
                    return "correct";
                } else {
                    return "wrong";
                }
            }
        }
        return "Invalid Id";
    }
}
