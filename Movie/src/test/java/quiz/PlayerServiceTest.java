package quiz;

import org.junit.jupiter.api.Test;
import quiz.model.Player;

import java.util.Set;

import static junit.framework.Assert.*;
import static quiz.DataTest.*;
import static quiz.service.PlayerService.answerCorrect;
import static quiz.service.PlayerService.countScore;

class PlayerServiceTest {

    Player p1 = createPlayer();
    static Set<String> correctAnswers = testAnswers;


    @Test
    void checkForCorrectAnswersTest() {

        fillSet();

        assertTrue(answerCorrect(correctAnswers, "a"));
        assertTrue(answerCorrect(correctAnswers, "c"));


    }

    @Test
    void checkForIncorrectAnswersTest() {

        fillSet();

        assertFalse(answerCorrect(correctAnswers, "b"));
        assertFalse(answerCorrect(correctAnswers, "d"));


    }

    @Test
    void checkForDuplicatedAnswersTest() {

        fillSet();

        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "a", p1);

        assertEquals(1, p1.getScore());


    }

    @Test
    void incrementScoreWhenCorrectAnswerTest() {

        fillSet();

        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "c", p1);

        assertEquals(2, p1.getScore());


    }

    @Test
    void doNotIncrementScoreWhenIncorrectAnswerTest() {

        countScore(correctAnswers, "b", p1);
        countScore(correctAnswers, "d", p1);

        assertEquals(0, p1.getScore());


    }
}
