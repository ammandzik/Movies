package quiz;

import org.junit.jupiter.api.Test;
import quiz.model.Player;

import java.util.List;
import java.util.Set;

import static junit.framework.Assert.*;
import static quiz.service.PlayerService.answerCorrect;
import static quiz.service.PlayerService.countScore;

class PlayerServiceTest {

    Player p1 = DataTest.createPlayer();
    Set<String> correctAnswers = DataTest.testAnswers;

    @Test
     void checkForCorrectAnswersTest() {


        assertTrue(answerCorrect(correctAnswers, "a"));
        assertTrue(answerCorrect(correctAnswers, "c"));


    }

    @Test
     void checkForIncorrectAnswersTest() {

        assertFalse(answerCorrect(correctAnswers, "b"));
        assertFalse(answerCorrect(correctAnswers, "d"));



    }

    @Test
     void incrementScoreWhenCorrectAnswerTest() {

        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "c", p1);

        assertTrue(p1.getScore() == 2);


    }

    @Test
    void doNotIncrementScoreWhenIncorrectAnswerTest() {

        countScore(correctAnswers, "b", p1);
        countScore(correctAnswers, "d", p1);

        assertTrue(p1.getScore() == 0);


    }
}
