package quiz.service;

import org.junit.jupiter.api.Test;
import quiz.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static quiz.service.QuestionService.addQuestionsToPool;

class QuestionServiceTest {

    private static final String FILE_PATH = "src/test/resources/questions.json";

    @Test
    void addQuestionToPoolTest() {
        // when
        List<Question> questionList = assertDoesNotThrow(() -> addQuestionsToPool(FILE_PATH),
                "addQuestionsToPool should not throw an exception when processing a valid file");

        // then
        assertNotNull(questionList, "Question list should not be null");
        assertFalse(questionList.isEmpty(), "Question list should not be empty");

        assertEquals(3, questionList.size(), "Question pool should contain exactly 10 questions");

        for (Question question : questionList) {
            assertNotNull(question.getQuestionText(), "Question text should not be null");
            assertFalse(question.getAnswers().isEmpty(), "Each question should have at least one answer");
        }
    }
}