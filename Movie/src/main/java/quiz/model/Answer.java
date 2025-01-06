package quiz.model;

import java.util.Objects;

public record Answer(String correctAnswer) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(correctAnswer, answer.correctAnswer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
