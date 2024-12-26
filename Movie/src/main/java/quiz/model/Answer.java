package quiz.model;

import java.util.Objects;

public class Answer {

    private String correctAnswer;

    private Answer() {

    }

    public Answer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(correctAnswer, answer.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correctAnswer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
