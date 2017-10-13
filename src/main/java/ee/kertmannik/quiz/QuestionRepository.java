package ee.kertmannik.quiz;

import ee.kertmannik.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {


    public List<Question> getAllQuestions() {
        return getQuestionsFromGist();
    }

    private List<Question> getQuestionsFromGist() {
        GetRequestToGist getRequestToGist = new GetRequestToGist();
        final String rawData = getRequestToGist.questionsRequest();
        System.out.println(rawData);
        List<Question> questions = splittingRawQuestions(rawData);
        return questions;
    }

    private List<Question> splittingRawQuestions(String rawData) {
        String[] rawQuestions = rawData.split("\n");
        List<Question> questions = new ArrayList<Question>();
        for (String rawQuestion : rawQuestions) {
            String[] splittedQuestion = rawQuestion.split(";");
            List<String> correctAnswers = new ArrayList<>();
            for (int i = 4; i < splittedQuestion.length; i++) {
                correctAnswers.add(splittedQuestion[i]);
            }
            Question question = new Question(
                    splittedQuestion[0], splittedQuestion[1],
                    splittedQuestion[2], Integer.parseInt(splittedQuestion[3]),
                    correctAnswers);
            questions.add(question);
        }
        return questions;
    }
}
