package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.CorrectAnswer;
import ee.kertmannik.quiz.model.Question;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class QuestionRepositoryTest {

    @Test
    public void should_throw_QuizException_if_list_is_empty() {
        //given
        List<Question> questions = Collections.emptyList();
        QuestionRepository questionRepository = new QuestionRepository(questions);

        //when
        try {
            Question result = questionRepository.getNextQuestion();
            fail("Should have thrown quiz exception because the list is empty.");
        } catch (QuizException excepted) {

        }
    }

    @Test
    public void should_return_the_question_if_the_list_has_only_one_element(){
        //given
        List<Question> questions = new ArrayList<>();
        QuestionRepository questionRepository = new QuestionRepository(questions);
        Question question = new Question("1", "Hello?", "general", 1, null);
        questions.add(question);

        //when
        Question result = questionRepository.getNextQuestion();

        //then
        assertThat(result).isEqualTo(question);
    }



    @Test
    public void should_return_the_second_question_if_the_list_has_multiple_elements(){
        //given
        List<Question> questions = new ArrayList<>();
        QuestionRepository questionRepository = new QuestionRepository(questions);
        Question question = new Question("1", "Hello?", "general", 1, null);
        Question secondQuestion = new Question("2", "Hello?", "general", 1, null);
        questions.add(question); questions.add(secondQuestion);

        //when
        Question result = questionRepository.getNextQuestion();
        Question secondResult = questionRepository.getNextQuestion();
        //then
        assertThat(secondResult).isEqualTo(secondQuestion);
    }

    @Test
    public void should_return_the_first_question_if_the_last_question_was_returned(){
        //given
        List<Question> questions = new ArrayList<>();
        QuestionRepository questionRepository = new QuestionRepository(questions);
        Question question = new Question("1", "Hello?", "general", 1, null);
        Question secondQuestion = new Question("2", "Hello?", "general", 1, null);
        questions.add(question); questions.add(secondQuestion);

        //when
        Question result = questionRepository.getNextQuestion();
        Question secondResult = questionRepository.getNextQuestion();
        Question thirdResult = questionRepository.getNextQuestion();
        //then
        assertThat(thirdResult).isEqualTo(question);
    }

    @Test
    public void should_return_the_second_question_if_the_counter_was_reset_and_two_new_second_requests_were_made(){
        //given
        List<Question> questions = new ArrayList<>();
        QuestionRepository questionRepository = new QuestionRepository(questions);
        Question question = new Question("1", "Hello?", "general", 1, null);
        Question secondQuestion = new Question("2", "Hello?", "general", 1, null);
        questions.add(question);
        questions.add(secondQuestion);

        //when
        Question result = questionRepository.getNextQuestion();
        Question secondResult = questionRepository.getNextQuestion();
        Question thirdResult = questionRepository.getNextQuestion();
        Question forthResult = questionRepository.getNextQuestion();
        //then
        assertThat(forthResult).isEqualTo(secondQuestion);
    }
}