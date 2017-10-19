package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;
import org.junit.Before;
import org.junit.Test;

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
        //given
        Answer testAnswer = new Answer("42", "Lars");

        //when
        String result = answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("correct").isEqualTo(result);
    }
}
