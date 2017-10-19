package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;
import ee.kertmannik.quiz.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerValidatorTest {

    AnswerValidator answerValidator;

    @Before
    public void initialize() throws Exception {
        this.answerValidator = new AnswerValidator();
    }

    @Test
    public void should_return_wrong_if_answer_for_question_42_is_wrong() {
        //given
        Answer testAnswer = new Answer("42", "Toomas");

        //when
        String result = this.answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("wrong").isEqualTo(result);
    }

    @Test
    public void should_return_correct_if_answer_for_question_42_is_right() {
        List<Question> questions = new ArrayList<>();
        QuestionRepository questionRepository = new QuestionRepository(questions);
        Question question = new Question("1", "Hello?", "general", 1, null);
        questions.add(question);

        //when
        Question result = questionRepository.getNextQuestion();

        //then
        assertThat(result).isEqualTo(question);
        //given
        Answer testAnswer = new Answer("42", "Lars");

        //when

        //then
        assertThat("correct").isEqualTo(result);
    }
}
