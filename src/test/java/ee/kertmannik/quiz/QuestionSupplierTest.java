package ee.kertmannik.quiz;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionSupplierTest {

    @Test
    public void Should_return_response_Body_as_String() throws Exception {
        //given
        MockWebServer server = new MockWebServer();
        server.start();
        HttpUrl baseUrl = server.url("/v1/chat/");
        QuestionSupplier questionSupplier = new QuestionSupplier(baseUrl.toString());
        server.enqueue(new MockResponse().setBody("Lars is the best!!!"));
        //when
        String result = questionSupplier.questionsRequest();
        //then
        assertThat(result).isEqualTo("Lars is the best!!!");
    }
}
