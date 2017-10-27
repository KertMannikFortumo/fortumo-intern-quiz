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
        try {
            List<String> correctAnswers = questionRepository.getAnswersById(questionId);
            for(String correctAnswer : correctAnswers){
                if (correctAnswer.toUpperCase().equals(answerText.toUpperCase())){
                    return "correct";
                }
            }
            return "wrong";
        } catch (QuizException exception) {
            return "invalid Id";
        }
    }
}
