package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;

import java.util.List;

public class AnswerValidator {

    private final QuestionRepository questionRepository;

    AnswerValidator(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String validateAnswer(Answer answer) {
        final String questionId = answer.getQuestionId();
        final String answerText = answer.getAnswer();

        List<String> correctAnswers = questionRepository.getAnswersById(questionId);
        if (correctAnswers.contains(answerText)) {
            return "correct";
        } else {
            return "wrong";
        }
    }
}
