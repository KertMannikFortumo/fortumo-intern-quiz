package ee.kertmannik.quiz;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDatabaseTest {

    @Test
    public void should_return_json_string() {
        //given
        QuestionRepository questionDatabase = new QuestionRepository();

        //when
        String result = questionDatabase.getAllQuestions();

        //then
        assertThat(result).isEqualTo(
                "[{\"id\":\"42\",\"question\":\"Kes on tubli poiss?\",\"category\":\"general\",\"difficulty\":1}]");
    }
}