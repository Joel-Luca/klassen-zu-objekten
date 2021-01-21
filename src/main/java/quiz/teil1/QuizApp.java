package quiz.teil1;

import java.util.Locale;
import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean onquestion = true;

        int number = 0; // number of questions
        int questioncounter = 1;
        int score = 0;


        while(onquestion) {
            System.out.print("Gib die Anzahl der Fragen ein: ");
            try {
                number = Integer.parseInt(scanner.nextLine());

                onquestion = false;
            }
            catch(Exception e) {
                System.out.println("\nUnknown input!");
            }
        }

        Question[] questions = new Question[number];
        questions = QuestionGenerator.generateQuestions(number, "easy");

        for(Question question : questions) {

            onquestion = true;
            while (onquestion) {
                System.out.println("\n" + questioncounter + ": " + question.getQuestionText() + "\n");
                for (int i = 0; i < question.getAnsers().length; i++) {
                    System.out.println("\t(" + (i + 1) + ")" + question.getAnsers()[i].getAnswerText());
                }

                System.out.print("\nInput: ");
                String inp = scanner.nextLine();

                try {
                    int num = Integer.parseInt(inp);

                    if(num < 0 && num > question.number - 1) {
                        System.out.println("\nUnknown number");
                    }
                    else {
                        int userAnswer = num - 1;

                        if(question.getAnsers()[userAnswer].isCorrect()) {
                            System.out.println("\nCorrect Answer!");
                            score++;
                            System.out.println(score + "/" + number);
                        }
                        else {
                            String correctanswer = "";
                            for(Answer answer : question.getAnsers()) {
                                if(answer.isCorrect()) {
                                    correctanswer = answer.getAnswerText();
                                }
                            }
                            System.out.println("\nWrong Answer, the correct Answer is " + correctanswer + ".");
                        }
                        onquestion = false;
                        questioncounter++;
                    }
                }
                catch (Exception e){
                    System.out.println("\nUnknown input!");
                }
            }
        }

        System.out.println("\nYou reached the end with a score of " + score + "/" + number);

    }
}
