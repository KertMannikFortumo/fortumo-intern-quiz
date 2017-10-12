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
        this.servletTester.addServlet(QuestionServlet.class, "/question");
        this.servletTester.start();
    }
    @Test
    public void should_return_statuscode_200_if_header_is_present()throws Exception{
        //given
        request.setHeader("x-player-name", "Vahur");
        request.setMethod("GET");
        request.setURI("/question");

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertEquals(200, this.response.getStatus());
    }

}