package ee.kertmannik.quiz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionDatabaseTest {

    @Test
    public void should_return_json_string() {
        //given
        QuestionDatabase questionDatabase = new QuestionDatabase();
        //when
        String result = questionDatabase.jsonAnswers();
        //then
        assertEquals("[{\"id\":\"42\",\"question\":\"Kes on tubli poiss?\",\"category\":\"general\",\"difficulty\":1}]", result);
    }
}