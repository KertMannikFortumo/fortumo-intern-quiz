package ee.kertmannik.quiz;

import com.sun.deploy.net.HttpRequest;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AnswerServletTest {
    private ServletTester servletTester;
    private HttpTester request;
    private HttpTester response;

    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.AnswerServlet.class, "/answer");
        this.servletTester.addFilter(ee.kertmannik.quiz.IdentifierFilter.class, "/*", 0);
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

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(200);
        assertThat(this.response.getContent()).isEqualTo("wrong");
    }
}