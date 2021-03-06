package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class RawQuestionParser {

    public List<Question> splittingRawQuestions(String rawData) {
        String[] rawQuestions = rawData.split("\\n");
        List<Question> questions = new ArrayList<Question>();
        for (String rawQuestion : rawQuestions) {
            try {
                String[] splittedQuestion = rawQuestion.split(";");
                if (splittedQuestion.length<5) {
                    throw new QuizException("Not enough fields in question " + rawQuestion);
                }
                List<String> correctAnswers = new ArrayList<>();
                for (int i = 4; i < splittedQuestion.length; i++) {
                    correctAnswers.add(splittedQuestion[i]);
                }
                Question question = new Question(
                        splittedQuestion[0], splittedQuestion[1],
                        splittedQuestion[2], Integer.parseInt(splittedQuestion[3]),
                        correctAnswers);
                questions.add(question);
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new QuizException("Incorrect raw Data.");
            }
        }
        return questions;
    }
}
