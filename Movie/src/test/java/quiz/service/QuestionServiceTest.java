package quiz.service;

import org.junit.jupiter.api.Test;
import quiz.model.Question;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static quiz.service.QuestionService.addQuestionsToPool;

class QuestionServiceTest {

    private static final String FILE_PATH = "src/test/resources/questions.json";

    @Test
    void addQuestionToPoolTest() {

        //given

        List<Question> questionList = assertDoesNotThrow(() -> addQuestionsToPool(FILE_PATH));

        //then

        assertFalse(questionList.isEmpty());
    }
}
