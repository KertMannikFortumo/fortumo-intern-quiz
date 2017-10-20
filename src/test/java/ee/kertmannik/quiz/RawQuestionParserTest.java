package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class RawQuestionParserTest {

    @Test
    public void should_throw_exception_if_question_supplier_respond_is_empty_string() {
        //given
        RawQuestionParser rawQuestionParser = new RawQuestionParser();

        //when
        try {
            List<Question> result = rawQuestionParser.splittingRawQuestions("");
            fail("Should have thrown ArrayIndexOutOfBoundsException because the questions are incorrect.");
        } catch (ArrayIndexOutOfBoundsException excepted) {

        }
    }

    @Test
    public void should_return_parsed_questin_if_input_is_correct(){
        //given
        RawQuestionParser rawQuestionParser = new RawQuestionParser();

        //when
        List<Question> result = rawQuestionParser.splittingRawQuestions("1;Who is a good boy?;general;1;Lars;Oskar");
        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCategory()).isEqualTo("general");
        assertThat(result.get(0).getDifficulty()).isEqualTo(1);
        assertThat(result.get(0).getQuestionId()).isEqualTo("1");
        assertThat(result.get(0).getQuestion()).isEqualTo("Who is a good boy?");
        assertThat(result.get(0).getCorrectAnswers()).isEqualTo(Arrays.asList("Lars", "Oskar"));
    }
}
