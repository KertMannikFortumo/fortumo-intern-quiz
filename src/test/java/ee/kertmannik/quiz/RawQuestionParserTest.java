package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class RawQuestionParserTest {

    private RawQuestionParser rawQuestionParser;

    @Before
    public void initialize() throws Exception {
        this.rawQuestionParser = new RawQuestionParser();
    }

    @Test
    public void should_throw_exception_if_question_supplier_respond_is_empty_string() {
        //given
        String rawData = "";
        //when
        try {
            List<Question> result = rawQuestionParser.splittingRawQuestions(rawData);
            fail("Should have thrown ArrayIndexOutOfBoundsException because the questions are incorrect.");
        } catch (QuizException excepted) {

        }
    }

    @Test
    public void should_return_parsed_questin_if_input_is_correct() {
        //given
        String rawData = "1;Who is a good boy?;general;1;Lars;Oskar";

        //when
        List<Question> result = rawQuestionParser.splittingRawQuestions(rawData);

        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCategory()).isEqualTo("general");
        assertThat(result.get(0).getDifficulty()).isEqualTo(1);
        assertThat(result.get(0).getQuestionId()).isEqualTo("1");
        assertThat(result.get(0).getQuestion()).isEqualTo("Who is a good boy?");
        assertThat(result.get(0).getCorrectAnswers()).isEqualTo(Arrays.asList("Lars", "Oskar"));
    }

    @Test
    public void should_throw_QuizException_if_question_does_not_have_enough_fields() {
        //given
        String rawData = "1;Who is a good boy?;general;1";

        //when
        try {
            List<Question> result = rawQuestionParser.splittingRawQuestions(rawData);
            Assert.fail("Should throw QuizException if invalid rawdata is given as input");
        } catch (QuizException expected) {

        }
    }
}
