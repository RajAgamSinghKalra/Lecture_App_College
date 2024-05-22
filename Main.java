import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setTitle("Raj ka Lecture");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebarPanel;

    public MainFrame() {
        setupFrame();
        initializeComponents();
    }

    private void setupFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.BLACK);
        sidebarPanel.setLayout(null);
        sidebarPanel.setPreferredSize(new Dimension(96, getHeight()));
        add(sidebarPanel, BorderLayout.WEST);
        
        LecturePanel lecturePanel = new LecturePanel();
        QuizPanel quizPanel = new QuizPanel();

        contentPanel.add(lecturePanel, "Lecture");
        contentPanel.add(quizPanel, "Quiz");
        add(contentPanel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeSidebar();
            }
        });
    }

    private void resizeSidebar() {
        sidebarPanel.setPreferredSize(new Dimension(96, getHeight()));
        int buttonHeight = getHeight() / 3;

        JButton lectureButton = createButton("Lecture", 0, buttonHeight);
        JButton quizButton = createButton("Quiz", buttonHeight, buttonHeight);
        JButton exitButton = createButton("Exit", buttonHeight * 2, buttonHeight);

        sidebarPanel.removeAll();
        sidebarPanel.add(lectureButton);
        sidebarPanel.add(quizButton);
        sidebarPanel.add(exitButton);
        sidebarPanel.revalidate();
        sidebarPanel.repaint();

        lectureButton.addActionListener(e -> cardLayout.show(contentPanel, "Lecture"));
        quizButton.addActionListener(e -> cardLayout.show(contentPanel, "Quiz"));
        exitButton.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text, int yPosition, int height) {
        JButton button = new JButton(text);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 16));
        button.setBounds(0, yPosition, 96, height);
        return button;
    }
}
