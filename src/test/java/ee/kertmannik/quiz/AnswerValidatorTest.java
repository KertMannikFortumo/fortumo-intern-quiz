package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;
import ee.kertmannik.quiz.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AnswerValidatorTest {

    @Test
    public void should_return_wrong_if_answer_for_question_42_is_wrong() {
        //given
        QuestionRepository mock = mock(QuestionRepository.class);
        AnswerValidator answerValidator = new AnswerValidator(mock);
        Answer testAnswer = new Answer("42", "Toomas");
        List<String> answers = Arrays.asList("Lars", "Martin");
        given(mock.getAnswerById("42")).willReturn(answers);

        //when
        String result = answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("wrong").isEqualTo(result);
    }

    @Test
    public void should_return_correct_if_answer_for_question_42_is_right() {
        //given
        QuestionRepository mock = mock(QuestionRepository.class);
        AnswerValidator answerValidator = new AnswerValidator(mock);
        Answer testAnswer = new Answer("42", "Toomas");
        List<String> answers = Arrays.asList("Toomas", "Martin");
        given(mock.getAnswerById("42")).willReturn(answers);

        //when
        String result = answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("correct").isEqualTo(result);
    }
}
