package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class QuestionServletTest {

    private ServletTester servletTester;
    private HttpTester response;
    private HttpTester request;
    private QuestionRepository repositoryMock;

    @Before
    public void initialize() throws Exception {
        this.servletTester = new ServletTester();
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.servletTester.addServlet(ee.kertmannik.quiz.QuestionServlet.class, "/question");
        this.servletTester.addFilter(IdentificationFilter.class, "/*", 0);
        this.servletTester.addEventListener(new MyServletContextListener() {

            protected QuestionRepository createQuestionRepository() {
                repositoryMock = mock(QuestionRepository.class);
                return repositoryMock;
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
        final List<String> correctAnswers = new ArrayList<>();
        final Question question = new Question("1", "Kert?", "general", 1, correctAnswers);
        final List<Question> questions = new ArrayList<Question>();
        questions.add(question);
        //given(this.repositoryMock.getAllQuestions()).willReturn(questions);

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        System.out.println(this.response.getContent());
        assertThat(this.response.getContent()).isEqualTo(questions);
        assertThat(this.response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_return_statuscode_400_if_header_is_not_present() throws Exception {
        //given
        this.request.setMethod("GET");
        this.request.setURI("/question");
        this.request.setVersion("HTTP/1.0");
        //given(this.repositoryMock.getAllQuestions()).willReturn(new ArrayList<>());

        //when
        this.response.parse(this.servletTester.getResponses(this.request.generate()));

        //then
        assertThat(this.response.getStatus()).isEqualTo(400);
        assertThat(this.response.getContent()).isEqualTo("Please use x-player-name header!");
    }
}
