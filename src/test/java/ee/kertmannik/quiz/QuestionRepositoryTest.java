package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionRepositoryTest {

    @Test
    public void should_return_json_string() {
        //given
        QuestionRepository questionDatabase = new QuestionRepository();

        //when
        List<Question> result = questionDatabase.getAllQuestions();

        //then
        assertThat(result).isEqualTo(
                "[{\"id\":\"42\",\"question\":\"Kes on tubli poiss?\",\"category\":\"general\",\"difficulty\":1}]");
    }
}