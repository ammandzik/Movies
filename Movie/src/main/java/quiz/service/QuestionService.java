package quiz.service;

import quiz.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static movie.service.FileService.jsonFileToObjectList;

class QuestionService {

    private static final Random RANDOM = new Random();
    private static List<Question> cachedQuestions;

    private QuestionService() {

    }

    public static List<Question> addQuestionsToPool(String filePath) {

        try {

            List<Question> questions = getQuestions(filePath);

            List<Integer> randomNumbers = new ArrayList<>();

            List<Question> questionsPool = new ArrayList<>(3);


            for (int i = 0; i < 3; i++) {

                int randomNumber = RANDOM.nextInt(questions.toArray().length);

                if (randomNumbers.contains(randomNumber)) {

                    i--;

                } else {

                    var randomQuestion = questions.get(randomNumber);
                    questionsPool.add(randomQuestion);
                    randomNumbers.add(randomNumber);
                }


            }
            return questionsPool;

        } catch (Exception e) {

            System.err.println("Error adding questions to pool: " + e.getMessage());

            return List.of();
        }


    }

    private static List<Question> getQuestions(String filePath) {

        if (cachedQuestions == null) {

            cachedQuestions = safeJsonFileToObjectList(filePath);
        }


        return cachedQuestions;

    }

    private static List<Question> safeJsonFileToObjectList(String filePath) {

        try {

            return jsonFileToObjectList(filePath, Question.class);

        } catch (Exception e) {

            System.err.println("Error loading questions! " + e.getMessage());

            return List.of();
        }

    }
}
