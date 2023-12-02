import java.util.*;

class Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Timer {
    private int timeLimit; // in seconds
    private boolean timeExpired;

    public Timer(int timeLimit) {
        this.timeLimit = timeLimit;
        this.timeExpired = false;
    }

    public void start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeExpired = true;
            }
        };

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, timeLimit * 1000);
    }

    public boolean isTimeExpired() {
        return timeExpired;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            displayQuestion(question);

            Timer timer = new Timer(10); // 10 seconds time limit for each question
            timer.start();

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();

            if (!timer.isTimeExpired() && userAnswer - 1 == question.getCorrectOptionIndex()) {
                System.out.println("Correct!\n");
                score++;
            } else if (timer.isTimeExpired()) {
                System.out.println("Time's up! Moving to the next question.\n");
            } else {
                System.out.println("Incorrect. The correct answer is: " + (question.getCorrectOptionIndex() + 1) + "\n");
            }
        }

        System.out.println("Quiz completed! Your final score: " + score);
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestion());

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Sample quiz questions
        List<Question> quizQuestions = new ArrayList<>();
        quizQuestions.add(new Question("What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 2));
        quizQuestions.add(new Question("Which planet is known as the Red Planet?",
                Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"), 1));
        quizQuestions.add(new Question("What is the largest mammal in the world?",
                Arrays.asList("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1));

        // Create a quiz with the sample questions
        Quiz quiz = new Quiz(quizQuestions);

        // Start the quiz
        quiz.startQuiz();
    }
}
