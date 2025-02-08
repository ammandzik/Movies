package quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quiz.model.Player;

import java.util.Set;

import static quiz.service.Data.*;
import static quiz.service.PlayerService.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    Player p1;
    Set<String> correctAnswers;

    @BeforeEach
    public void setUp() {
        p1 = createPlayer();
        correctAnswers = testAnswers;
        fillSet();
    }

    @Test
    void checkForCorrectAnswersTest() {
        // when & then
        assertTrue(isAnswerCorrect(correctAnswers, "a"), "Answer 'a' should be correct");
        assertTrue(isAnswerCorrect(correctAnswers, "c"), "Answer 'c' should be correct");
    }

    @Test
    void checkForIncorrectAnswersTest() {
        // when & then
        assertFalse(isAnswerCorrect(correctAnswers, "b"), "Answer 'b' should be incorrect");
        assertFalse(isAnswerCorrect(correctAnswers, "d"), "Answer 'd' should be incorrect");
    }

    @Test
    void checkForDuplicatedAnswersTest() {
        // when
        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "a", p1);

        // then
        assertEquals(1, p1.getScore(), "Score should not increment for duplicated answers");
    }

    @Test
    void incrementScoreWhenCorrectAnswerTest() {
        // when
        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "c", p1);

        // then
        assertEquals(2, p1.getScore(), "Score should increment for each correct answer");
    }

    @Test
    void doNotIncrementScoreWhenIncorrectAnswerTest() {
        // when
        countScore(correctAnswers, "b", p1);
        countScore(correctAnswers, "d", p1);

        // then
        assertEquals(0, p1.getScore(), "Score should not increment for incorrect answers");
    }

    @Test
    void answerCorrectlyRemovedFromSetTest() {
        // when
        boolean isCorrect = isAnswerCorrect(correctAnswers, "c");

        // then
        assertTrue(isCorrect, "Correct answer 'c' should be removed from the set");
        assertFalse(correctAnswers.contains("c"), "Set should no longer contain the answer 'c'");

    }

    @Test
    void answerCorrectlyNotRemovedFromSetTest() {
        // when
        boolean isCorrect = isAnswerCorrect(correctAnswers, "b");

        // then
        assertFalse(isCorrect, "Incorrect answer 'b' should not be marked as correct");

    }


}