package quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quiz.model.Player;

import java.util.Set;

import static junit.framework.Assert.*;

import static quiz.service.Data.*;
import static quiz.service.PlayerService.answerCorrect;
import static quiz.service.PlayerService.countScore;

class PlayerServiceTest {

    Player p1 = createPlayer();
    static Set<String> correctAnswers = testAnswers;

    @BeforeEach
    public void fillSetWithAnswers(){

        fillSet();
    }


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
    void checkForDuplicatedAnswersTest() {


        //when
        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "a", p1);

        //then
        assertEquals(1, p1.getScore());


    }

    @Test
    void incrementScoreWhenCorrectAnswerTest() {


        //when
        countScore(correctAnswers, "a", p1);
        countScore(correctAnswers, "c", p1);

        //then
        assertEquals(2, p1.getScore());


    }

    @Test
    void doNotIncrementScoreWhenIncorrectAnswerTest() {

        //when
        countScore(correctAnswers, "b", p1);
        countScore(correctAnswers, "d", p1);

        //then
        assertEquals(0, p1.getScore());


    }

    @Test
    void answerCorrectlyRemovedFromSetTest(){

        assertTrue(answerCorrect(correctAnswers, "c"));
    }

    @Test
    void answerCorrectlyNotRemovedFromSetTest(){

        assertFalse(answerCorrect(correctAnswers, "b"));
    }
}
