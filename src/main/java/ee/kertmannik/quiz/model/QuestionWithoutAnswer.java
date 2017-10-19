package ee.kertmannik.quiz.model;

public class QuestionWithoutAnswer {

    private String questionId;
    private String question;
    private String category;
    private int difficulty;

    public QuestionWithoutAnswer(String questionId, String question, String category, int difficulty) {
        this.questionId = questionId;
        this.question = question;
        this.category = category;
        this.difficulty = difficulty;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionId() {
        return questionId;
    }
}
