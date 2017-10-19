package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class QuestionServletTest {

    private ServletTester servletTester;
    private HttpTester response;
    private HttpTester request;
    private QuestionController controllerMock;

    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.QuestionServlet.class, "/question");
        this.servletTester.addFilter(IdentificationFilter.class, "/*", 0);
        this.servletTester.addEventListener(new MyServletContextListener() {

            @Override
            protected QuestionController getQuestionController(QuestionRepository questionRepository) {
                controllerMock = mock(QuestionController.class);
                return controllerMock;
            }
        });
        this.servletTester.start();
    }

    @Test
    public void should_return_statuscode_200_and_question_if_header_is_present() throws Exception {
        //given
        this.request.setHeader("x-player-name", "Vahur");
        this.request.setMethod("GET");
        this.request.setURI("/question");
        this.request.setVersion("HTTP/1.0");
        given(this.controllerMock.getNextQuestionJson()).willReturn("Any Question Json");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getContent()).isEqualTo("Any Question Json");
        assertThat(this.response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_return_statuscode_400_if_header_is_not_present() throws Exception {
        //given
        this.request.setMethod("GET");
        this.request.setURI("/question");
        this.request.setVersion("HTTP/1.0");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(400);
        assertThat(this.response.getContent()).isEqualTo("Please use x-player-name header!");
    }
}
