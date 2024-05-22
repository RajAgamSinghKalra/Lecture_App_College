import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class QuizPanel extends JPanel {
    private JLabel headingLabel, nameLabel, questionLabel;
    private JRadioButton[] answerButtons;
    private ButtonGroup buttonGroup;
    private JTextArea feedbackArea;
    private JButton submitButton, prevButton, nextButton;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;

    public QuizPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        questions = createQuestions();
        setupUIComponents();
        loadQuestion(0);
    }

    private void setupUIComponents() {
        headingLabel = new JLabel("QUIZ");
        styleLabel(headingLabel, 128, 20, 200, 30, 32, Font.BOLD);

        nameLabel = new JLabel("Quiz: Hello World");
        styleLabel(nameLabel, 128, 60, 300, 20, 18, Font.PLAIN);
        questionLabel = new JLabel();
        styleLabel(questionLabel, 128, 100, 1239, 30, 18, Font.PLAIN);

        answerButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            styleRadioButton(answerButtons[i], 128, 140 + i * 40, 1239, 30);
            buttonGroup.add(answerButtons[i]);
        }
        feedbackArea = new JTextArea();
        styleTextArea(feedbackArea, 128, 300, 1239, 50);
        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Poppins", Font.BOLD, 16));
        submitButton.setBounds(628, 360, 180, 40); 
        submitButton.addActionListener(e -> handleSubmit());
        add(submitButton);

        // Previous and Next Buttons
        prevButton = new JButton("<");
        nextButton = new JButton(">");
        styleNavButton(prevButton, 118, 360, 50, 40);
        styleNavButton(nextButton, 1340, 360, 50, 40);

        prevButton.addActionListener(e -> loadQuestion(currentQuestionIndex - 1));
        nextButton.addActionListener(e -> loadQuestion(currentQuestionIndex + 1));

        add(prevButton);
        add(nextButton);
    }

    private void styleLabel(JLabel label, int x, int y, int width, int height, int fontSize, int fontStyle) {
        label.setBounds(x, y, width, height);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Poppins", fontStyle, fontSize));
        add(label);
    }

    private void styleRadioButton(JRadioButton radioButton, int x, int y, int width, int height) {
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(Color.WHITE);
        radioButton.setForeground(Color.BLACK);
        radioButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(radioButton);
    }

    private void styleTextArea(JTextArea textArea, int x, int y, int width, int height) {
        textArea.setBounds(x, y, width, height);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Poppins", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textArea);
    }

    private void styleNavButton(JButton button, int x, int y, int width, int height) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 16));
        button.setBounds(x, y, width, height);
        add(button);
    }

    private void handleSubmit() {
        QuizQuestion question = questions.get(currentQuestionIndex);
        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].isSelected()) {
                if (answerButtons[i].getText().equals(question.getCorrectAnswer())) {
                    feedbackArea.setText("Correct!");
                } else {
                    feedbackArea.setText("Incorrect. The correct answer is: " + question.getCorrectAnswer());
                }
                return;
            }
        }
        feedbackArea.setText("Please select an answer.");
    }

    private void loadQuestion(int index) {
        if (index < 0 || index >= questions.size()) {
            return;
        }
        currentQuestionIndex = index;
        QuizQuestion question = questions.get(index);
        questionLabel.setText(question.getQuestion());
        String[] answers = question.getAnswers();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(answers[i]);
        }
        buttonGroup.clearSelection();
        feedbackArea.setText("");
    }

    private List<QuizQuestion> createQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the output of the Hello World program?", new String[]{"Hello, World!", "Hello", "World", "Hello, Java"}, "Hello, World!"));
        questions.add(new QuizQuestion("What is the correct syntax for the main method in Java?", new String[]{"public static void main(String[] args)", "public static void main()", "void main(String[] args)", "static void main(String[] args)"}, "public static void main(String[] args)"));
        questions.add(new QuizQuestion("What is a class in Java?", new String[]{"A blueprint for creating objects", "A function", "A variable", "An object"}, "A blueprint for creating objects"));
        questions.add(new QuizQuestion("What is the output of: System.out.println(1 + 2 + \"3\")?", new String[]{"33", "6", "123", "15"}, "33"));
        questions.add(new QuizQuestion("Which keyword is used to create a class in Java?", new String[]{"class", "Class", "struct", "define"}, "class"));
        questions.add(new QuizQuestion("Which of the following is a valid variable declaration in Java?", new String[]{"int number;", "integer number;", "num int;", "int number"}, "int number;"));
        questions.add(new QuizQuestion("What is the default value of an int variable in Java?", new String[]{"0", "null", "undefined", "1"}, "0"));
        questions.add(new QuizQuestion("Which method is used to print a message to the console?", new String[]{"System.out.print", "System.out.println", "print", "println"}, "System.out.println"));
        questions.add(new QuizQuestion("What does JVM stand for?", new String[]{"Java Virtual Machine", "Java Variable Method", "Java Visual Machine", "Java Version Manager"}, "Java Virtual Machine"));
        questions.add(new QuizQuestion("Which of the following is a valid way to start a comment in Java?", new String[]{"// Comment", "/* Comment */", "/** Comment */", "All of the above"}, "All of the above"));
        return questions;
    }
}

class QuizQuestion {
    private String question;
    private String[] answers;
    private String correctAnswer;

    public QuizQuestion(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
