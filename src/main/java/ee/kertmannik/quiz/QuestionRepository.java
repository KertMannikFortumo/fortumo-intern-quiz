package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;

import java.util.List;

public class QuestionRepository {

    private List<Question> questions;
    private int counter;

    QuestionRepository(List<Question> questions) {
        this.questions = questions;
    }

    public Question getNextQuestion() {
        if (!this.questions.isEmpty()) {
            if (counter >= this.questions.size()) {
                counter = 0;
            }
            return this.questions.get(counter++);
        } else {
            throw new QuizException();
        }
    }
}
