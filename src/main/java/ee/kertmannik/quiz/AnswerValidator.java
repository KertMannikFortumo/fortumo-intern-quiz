package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;

import java.util.List;

public class AnswerValidator {

    private final QuestionRepository questionRepository;

    AnswerValidator(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String validateAnswer(Answer answer) {
        final String answerId = answer.getQuestionId();
        final String answerText = answer.getAnswer();

        List<String> correctAnswers = questionRepository.getAnswerById(answerId);
        if (correctAnswers.contains(answerText)) {
            return "correct";
        } else {
            return "wrong";
        }
    }
}
