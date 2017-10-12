package ee.kertmannik.quiz;

import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionServletTest {
    private ServletTester servletTester;
    private HttpTester response;
    private HttpTester request;


    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.QuestionServlet.class, "/question");
        this.servletTester.addFilter(ee.kertmannik.quiz.IdentifierFilter.class, "/*", 0);
        this.servletTester.start();
    }

    @Test
    public void should_return_statuscode_200_if_header_is_present()throws Exception{
        //given
        this.request.setHeader("x-player-name", "Vahur");
        this.request.setMethod("GET");
        this.request.setURI("/question");
        this.request.setVersion("HTTP/1.0");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertEquals(200, this.response.getStatus());
    }

    @Test
    public void should_return_statuscode_400_if_header_is_not_present()throws Exception{
        //given
        this.request.setMethod("GET");
        this.request.setURI("/question");
        this.request.setVersion("HTTP/1.0");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertEquals(400, this.response.getStatus());
        assertEquals("Please use x-player-name header!", this.response.getContent());
    }
}
