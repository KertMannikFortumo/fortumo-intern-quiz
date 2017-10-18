package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionRepositoryTest {

    @Test
    public void should_return_answers_from_gist_file() {
        //given
        QuestionRepository questionRepository = new QuestionRepository();

        //when
        List<Question> result = questionRepository.getAllQuestions();

        //then
        assertThat(result).isEqualTo(
                "");
    }
}