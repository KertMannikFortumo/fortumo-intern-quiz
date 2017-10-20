package ee.kertmannik.quiz;

import com.google.gson.JsonSyntaxException;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class AnswerControllerTest {

    @Test
    public void should_return_error_message_if_request_body_is_not_valid_json() throws Exception {
        //given
        String rawData = "invalid json";
        AnswerController answerController = new AnswerController(mock(AnswerValidator.class));
        //when
        try {
            String result = answerController.valuateAnswer(rawData);
            fail("Should throw QuizException if invalid rawdata is given as input");
        } catch (QuizException expected) {

        }
    }
}