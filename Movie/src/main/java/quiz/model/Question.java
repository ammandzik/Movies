package quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a question in the quiz. Each question contains a question number,
 * the text of the question, and a list of possible answers.
 */
public class Question {

    private int questionNumber;
    private String questionText;
    private ArrayList<Answer> answers = new ArrayList<>();

    int getQuestionNumber() {
        return questionNumber;
    }

    void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionNumber == question.questionNumber && Objects.equals(questionText, question.questionText) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionNumber, questionText, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionNumber=" + questionNumber +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}