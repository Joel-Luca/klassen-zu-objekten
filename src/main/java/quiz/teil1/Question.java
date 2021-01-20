package quiz.teil1;

public class Question {

    int number = 4; // number of answers per question

    private String questionText;
    private Answer[] ansers = new Answer[number];

    public String getQuestionText() {
        return questionText;
    }

    public Answer[] getAnsers() {
        return ansers;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswers(Answer[] ansers) {
        this.ansers = ansers;
    }
}
