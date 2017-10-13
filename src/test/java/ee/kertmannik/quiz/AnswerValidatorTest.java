package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Answer;
import javafx.scene.AmbientLight;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AnswerValidatorTest {

    @Test
    public void should_return_wrong_if_answer_for_question_42_is_wrong(){
        //given
        AnswerValidator answerValidator = new AnswerValidator();
        Answer testAnswer = new Answer("42", "Toomas");

        //when
        String result = answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("wrong").isEqualTo(result);
    }

    @Test
    public void should_return_correct_if_answer_for_question_42_is_right(){
        //given
        AnswerValidator answerValidator = new AnswerValidator();
        Answer testAnswer = new Answer("42", "Lars");

        //when
        String result = answerValidator.validateAnswer(testAnswer);

        //then
        assertThat("correct").isEqualTo(result);

    }

}