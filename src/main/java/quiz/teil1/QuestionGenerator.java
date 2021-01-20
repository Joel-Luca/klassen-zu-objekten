package quiz.teil1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.text.StringEscapeUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class QuestionGenerator {

    private static final String BASE_API_URL = "https://opentdb.com/api.php";

    /**
     * Generates questions with use of an external JSON-API.
     * @param numberOfQuestionsToGenerate If more than 50 questions requested, the result will be cut at max 50 questions. A passed value <= 0 will result in an empty result array.
     * @param difficulty "easy", "medium" or "hard" can be passed as values.
     * @return an array with the requested number of question objects.
     * @throws IllegalArgumentException if an unknown difficulty level is passed
     */
    public static Question[] generateQuestions(int numberOfQuestionsToGenerate, String difficulty) {
        String sURL = BASE_API_URL + "?amount=" + numberOfQuestionsToGenerate + "&difficulty=" + difficulty;
        if (!isValidDifficultyLevel(difficulty)) {
            throw new IllegalArgumentException("Difficulty level not found.");
        }

        try {
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();
            JsonObject root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
            JsonArray questions = root.get("results").getAsJsonArray();
            return generateQuestions(questions);

        } catch (Exception e) {
            throw new RuntimeException((e));
        }

    }

    private static boolean isValidDifficultyLevel(String difficultyLevel) {
        String level = difficultyLevel.toLowerCase();
        return level.equals("easy") || level.equals("medium") || level.equals("hard");
    }

    private static String getQuestionText(JsonElement jsonQuestion) {
        return StringEscapeUtils.unescapeHtml4(jsonQuestion.getAsJsonObject().get("question").getAsString());
    }

    private static JsonArray getIncorrectAnswers(JsonElement jsonQuestion) {
        return jsonQuestion.getAsJsonObject().get("incorrect_answers").getAsJsonArray();
    }

    private static String getIncorrectAnswerText(JsonElement answer) {
        return StringEscapeUtils.unescapeHtml4(answer.getAsString());
    }

    private static String getCorrectAnswerText(JsonElement jsonQuestion) {
            return StringEscapeUtils.unescapeHtml4(jsonQuestion.getAsJsonObject().get("correct_answer").getAsString());
    }

    private static Answer[] randomizeAnswers(Answer[] answers){
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i < answers.length; i++) {
            int randomPosition = rgen.nextInt(answers.length);
            Answer temp = answers[i];
            answers[i] = answers[randomPosition];
            answers[randomPosition] = temp;
        }

        return answers;
    }

    private static Question[] generateQuestions(JsonArray jsonQuestions) {
        int numberOfQuestions = jsonQuestions.size();
        Question[] result = new Question[numberOfQuestions];
        int questionIndex = 0;

        for (JsonElement jsonQuestion : jsonQuestions) {
            Question question = new Question();
            question.setQuestionText(getQuestionText(jsonQuestion));
            JsonArray incorrectAnswers = getIncorrectAnswers(jsonQuestion);
            Answer[] answers = new Answer[incorrectAnswers.size() + 1]; // +1 because we also want the correct answer
            Answer correctAnswer = new Answer();
            correctAnswer.setCorrect(true);
            correctAnswer.setAnswerText(getCorrectAnswerText(jsonQuestion));
            answers[0] = correctAnswer;

            int answerIndex = 1;
            for (JsonElement incorrectJsonAnswer : incorrectAnswers) {
                Answer wrongAnswer = new Answer();
                wrongAnswer.setAnswerText(getIncorrectAnswerText(incorrectJsonAnswer));
                answers[answerIndex] = wrongAnswer;
                answerIndex++;
            }

            question.setAnswers(randomizeAnswers(answers));
            result[questionIndex] = question;
            questionIndex++;
        }

        return result;
    }
}
