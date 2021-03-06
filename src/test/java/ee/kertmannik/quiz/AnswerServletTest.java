package ee.kertmannik.quiz;

import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AnswerServletTest {

    private ServletTester servletTester;
    private HttpTester request;
    private HttpTester response;
    private AnswerController controllerMock;

    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.AnswerServlet.class, "/answer");
        this.servletTester.addFilter(IdentificationFilter.class, "/*", 0);
        this.servletTester.addEventListener(new MyServletContextListener() {
            @Override
            protected AnswerController getAnswerController(AnswerValidator answerValidator) {
                controllerMock = mock(AnswerController.class);
                return controllerMock;
            }
        });
        this.servletTester.start();
    }

    @Test
    public void should_return_statuscode_200_and_result_from_answer_validator() throws Exception {
        //given
        this.request.setHeader("x-player-name", "Mostafa");
        this.request.setMethod("POST");
        this.request.setURI("/answer");
        this.request.setContent("{\"question-id\":\"42\",\"answer\":\"anything\"}");
        this.request.setVersion("HTTP/1.0");
        given(this.controllerMock.valuateAnswer(this.request.getContent())).willReturn("any_answer");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getContent()).isEqualTo("any_answer");
        assertThat(this.response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_return_statuscode_400_and_error_message_if_request_body_is_not_valid_answer() throws Exception {
        //given
        this.request.setHeader("x-player-name", "Mostafa");
        this.request.setMethod("POST");
        this.request.setURI("/answer");
        this.request.setContent("not_valid_json");
        this.request.setVersion("HTTP/1.0");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(400);
        assertThat(this.response.getContent()).isEqualTo("Could not parse request body.");
    }
}