import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LecturePanel extends JPanel {
    private JLabel headerLabel, nameLabel;
    private JTextArea contentArea, codeArea, outputArea;
    private JButton executeButton;

    public LecturePanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        initializeComponents();
    }

    private void initializeComponents() {
        headerLabel = new JLabel("LECTURE");
        styleLabel(headerLabel, 128, 20, 200, 30, 32, Font.BOLD);

        nameLabel = new JLabel("Lecture one, Hello World");
        styleLabel(nameLabel, 128, 60, 300, 20, 18, Font.PLAIN);

        String lectureText = "Lecture: Understanding the Hello World Program in Java\n\n"
                + "The Hello World program is a simple yet complete Java application that prints 'Hello, World!' to the console. "
                + "This program is often used as an introductory exercise in programming courses to demonstrate basic syntax and functionality of a programming language. "
                + "\n\nKey Components:\n"
                + "1. The 'public class HelloWorld' defines a class named HelloWorld.\n"
                + "2. The 'public static void main(String[] args)' method is the entry point for any Java application. "
                + "In this method, 'System.out.println(\"Hello, World!\");' prints the text Hello, World! to the console.\n"
                + "\n// Try modifying the print line below and see the output change in real time.";
        contentArea = new JTextArea(lectureText);
        styleTextArea(contentArea, 128, 100, 1239, 208);

        String initialCode = "// Modify and type your Java code here then press Execute.\n"
                + "public class HelloWorld {\n"
                + "    public static void main(String[] args) {\n"
                + "        System.out.println(\"Hello, World!\");\n"
                + "    }\n"
                + "}";
        codeArea = new JTextArea(initialCode);
        styleTextArea(codeArea, 128, 318, 1239, 208);

        outputArea = new JTextArea("Output will be displayed here");
        styleTextArea(outputArea, 128, 536, 1239, 208);

        executeButton = new JButton("Execute");
        executeButton.setBackground(Color.DARK_GRAY);
        executeButton.setForeground(Color.WHITE);
        executeButton.setFont(new Font("Poppins", Font.BOLD, 16));
        executeButton.setBounds(628, 754, 180, 40);
        executeButton.addActionListener(e -> runCode(codeArea.getText()));
        add(executeButton);
    }

    private void styleLabel(JLabel label, int x, int y, int width, int height, int fontSize, int fontStyle) {
        label.setBounds(x, y, width, height);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Poppins", fontStyle, fontSize));
        add(label);
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

    private void runCode(String code) {
        if (code.contains("System.out.println")) {
            int start = code.indexOf("System.out.println") + 19;
            int end = code.lastIndexOf("\");");
            if (start >= 0 && end > start && end < code.length()) {
                String output = code.substring(start, end);
                outputArea.setText("Output: " + output);
            } else {
                outputArea.setText("Error: Could not find proper output string.");
            }
        } else {
            outputArea.setText("No output command found.");
        }
    }
}
