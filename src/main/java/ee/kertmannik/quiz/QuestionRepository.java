package ee.kertmannik.quiz;

import com.google.gson.Gson;
import ee.kertmannik.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {


    private List<Question> getAllQuestions() {
        return getQuestionsFromGist();
    }

    private List<Question> getQuestionsFromGist() {
        final GetRequestFromGist getRequestFromGist = new GetRequestFromGist();
        final String rawData = getRequestFromGist.questionsRequest();
        return splittingRawQuestions(rawData);
    }

    private List<Question> splittingRawQuestions(String rawData) {
        String[] rawQuestions = rawData.split("\n");
        List<Question> questions = new ArrayList<Question>();
        for (String rawQuestion : rawQuestions) {
            try {
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
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new ArrayIndexOutOfBoundsException("Incorrect question.");
            }
        }
        return questions;
    }

    public String getOneQuestion(int questionGetCounter) {
        List<Question> questions = getAllQuestions();
        Question question;
        if (questionGetCounter >= questions.size()) {
            question = questions.get(0);
        } else {
            question = questions.get(questionGetCounter);
        }
        Gson gson = new Gson();
        return gson.toJson(question); //return question with the answer
    }
}
