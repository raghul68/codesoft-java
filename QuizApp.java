 import java.util.*;
import java.util.concurrent.TimeUnit;

// Question class to store question, options, and the correct answer
class Question {
    String questionText;
    String[] options;
    String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer);
    }

    @Override
    public String toString() {
        StringBuilder questionDisplay = new StringBuilder(questionText + "\n");
        for (int i = 0; i < options.length; i++) {
            questionDisplay.append((i + 1) + ". " + options[i] + "\n");
        }
        return questionDisplay.toString();
    }
}

// Timer class to handle countdown for each question
class TimerTaskThread extends TimerTask {
    private int seconds;
    private boolean timeUp;

    public TimerTaskThread(int seconds) {
        this.seconds = seconds;
        this.timeUp = false;
    }

    @Override
    public void run() {
        while (seconds > 0 && !timeUp) {
            System.out.println("Time remaining: " + seconds + " seconds");
            try {
                TimeUnit.SECONDS.sleep(1);
                seconds--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (seconds == 0) {
            timeUp = true;
            System.out.println("\nTime's up!");
        }
    }

    public void stopTimer() {
        this.timeUp = true;
    }

    public boolean isTimeUp() {
        return timeUp;
    }
}

// Quiz class to handle the quiz logic
class QuizApplication {

    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;
    }

    public void addQuestion(String questionText, String[] options, String correctAnswer) {
        questions.add(new Question(questionText, options, correctAnswer));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Question question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ":");
            System.out.println(question);

            // Start timer for the question (set to 30 seconds per question)
            TimerTaskThread timer = new TimerTaskThread(30);
            Timer t = new Timer();
            t.schedule(timer, 0, 1000); // Schedule to run every second

            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            timer.stopTimer(); // Stop the timer as soon as the answer is submitted

            if (timer.isTimeUp()) {
                System.out.println("Time's up! You didn't answer in time.");
            } else {
                // Check if the answer is correct
                if (question.isCorrect(answer)) {
                    score++;
                }
            }

            // Print the score so far
            System.out.println("Your score: " + score);
            System.out.println();
        }

        // Show the final result after the quiz is finished
        showResults();
    }

    public void showResults() {
        System.out.println("Quiz Finished!");
        System.out.println("Your final score: " + score + " out of " + questions.size());

        // Show correct/incorrect answers summary
        System.out.println("\nSummary:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String result = q.isCorrect(q.correctAnswer) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + result);
        }
    }
}

// Main class to run the application
public class QuizApp {

    public static void main(String[] args) {
        // Create an instance of the quiz application
        QuizApplication quiz = new QuizApplication();

        // Add questions to the quiz
        quiz.addQuestion("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Rome"}, "Paris");
        quiz.addQuestion("Which programming language is this quiz written in?", new String[]{"Java", "Python", "C++", "Ruby"}, "Java");
        quiz.addQuestion("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Jupiter");
       
        // Start the quiz
        quiz.startQuiz();
    }
}


