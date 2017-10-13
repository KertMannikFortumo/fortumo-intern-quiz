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
    private AnswerValidator validatorMock;

    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.AnswerServlet.class, "/answer");
        this.servletTester.addFilter(ee.kertmannik.quiz.IdentifierFilter.class, "/*", 0);
        this.servletTester.addEventListener(new MyServletContextListener() {
            @Override
            protected AnswerValidator createAnswerValidator() {
                validatorMock = mock(AnswerValidator.class);
                return validatorMock;
            }
        });
        this.servletTester.start();
    }

    @Test
    public void should_return_statuscode_200_and_validatedAnswer_correct_if_answer_is_correct() throws Exception {
        //given
        this.request.setHeader("x-player-name", "Mostafa");
        this.request.setMethod("POST");
        this.request.setURI("/answer");
        this.request.setContent("{\"question-id\":\"42\",\"answer\":\"Lars\"}");
        this.request.setVersion("HTTP/1.0");
        given(this.validatorMock.validateAnswer(this.request.getContent())).willReturn("correct");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(200);
        assertThat(this.response.getContent()).isEqualTo("correct");
    }

    @Test
    public void should_return_statuscode_200_and_validatedAnswer_wrong_if_answer_is_false() throws Exception {
        //given
        this.request.setHeader("x-player-name", "Riina");
        this.request.setMethod("POST");
        this.request.setURI("/answer");
        this.request.setContent("{\"question-id\":\"42\",\"answer\":\"Martin Koppel\"}");
        this.request.setVersion("HTTP/1.0");
        given(this.validatorMock.validateAnswer(this.request.getContent())).willReturn("wrong");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(200);
        assertThat(this.response.getContent()).isEqualTo("wrong");
    }
}