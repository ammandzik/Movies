package quiz.service;

import quiz.model.Question;

import java.util.*;

import static movie.service.FileService.jsonFileToObjectList;

/**
 * Service class for managing questions in the quiz.
 * Handles operations like loading questions and creating a random question pool.
 */
class QuestionService {

    private static final Random RANDOM = new Random();
    private static List<Question> cachedQuestions;

    private QuestionService() {}

    /**
     * Creates a pool of 10 random questions from the list of questions loaded from the file.
     *
     * @param filePath the file path to the questions JSON file.
     * @return a list of 10 random questions.
     */
    public static List<Question> addQuestionsToPool(String filePath) {
        try {
            List<Question> questions = getQuestions(filePath);

            if (questions.size() < 3) {
                System.err.println("Not enough questions available to create a pool of 10.");
                return List.of();
            }

            Set<Integer> randomIndices = new HashSet<>();
            List<Question> questionsPool = new ArrayList<>(10);

            while (randomIndices.size() < 3) {
                int randomIndex = RANDOM.nextInt(questions.size());
                if (randomIndices.add(randomIndex)) {
                    questionsPool.add(questions.get(randomIndex));
                }
            }

            return questionsPool;

        } catch (Exception e) {
            System.err.println("Error adding questions to pool: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Retrieves all questions from the cache or loads them from the file.
     *
     * @param filePath the file path to the questions JSON file.
     * @return a list of all questions.
     */
    private static List<Question> getQuestions(String filePath) {
        if (cachedQuestions == null) {
            cachedQuestions = safeJsonFileToObjectList(filePath);
        }
        return cachedQuestions;
    }

    /**
     * Safely loads questions from a JSON file and handles any exceptions.
     *
     * @param filePath the file path to the questions JSON file.
     * @return a list of questions or an empty list in case of errors.
     */
    private static List<Question> safeJsonFileToObjectList(String filePath) {
        try {
            return jsonFileToObjectList(filePath, Question.class);
        } catch (Exception e) {
            System.err.println("Error loading questions: " + e.getMessage());
            return List.of();
        }
    }
}