import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {
        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        JFrame splashFrame = new JFrame();
        splashFrame.setUndecorated(true);
        splashFrame.setSize(600, 400);
        splashFrame.setLayout(new BorderLayout());
        splashFrame.setLocationRelativeTo(null);
        JLabel welcomeLabel = new JLabel("Prestige Solar Energy Management", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(255, 255, 255));
        splashFrame.add(welcomeLabel, BorderLayout.CENTER);
        splashFrame.getContentPane().setBackground(new Color(28, 246, 6));
        splashFrame.setVisible(true);
        Timer splashTimer = new Timer(3000, e -> {
            splashFrame.dispose();
            showMainMenu();
        });
        splashTimer.setRepeats(false);
        splashTimer.start();
    }

    private static void showMainMenu() {
        String osName = "Prestige Solar Energy Management";
        JFrame frame = new JFrame(osName + " - Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(osName + " Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBackground(new Color(28, 246, 6));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(28, 246, 6));

        JButton dashboardButton = createHoverStyledButton("Welcome to Dashboard!");
        JButton totalSalesButton = createHoverStyledButton("Total Sales History");
        dashboardButton.addActionListener(e -> DashBoard(frame));
        totalSalesButton.addActionListener(e -> showSalesHistory(frame));

        buttonPanel.add(dashboardButton);
        buttonPanel.add(totalSalesButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Powered by " + osName, JLabel.CENTER);
        footerLabel.setFont(new Font("Roboto", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(97, 97, 97));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(footerLabel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void DashBoard(JFrame parentFrame) {
        JFrame processFrame = new JFrame("Dashboard");
        processFrame.setSize(500, 500);
        processFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.ITALIC, 24));
        titleLabel.setForeground(new Color(80, 80, 129));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        processFrame.add(titleLabel, BorderLayout.NORTH);

        JPanel processPanel = new JPanel();
        processPanel.setLayout(new GridLayout(5, 2, 10, 10));
        processPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        processPanel.setBackground(new Color(244, 244, 244));

        JButton createButton = createHoverStyledButton("Create Process");
        JButton destroyButton = createHoverStyledButton("Destroy Process");
        JButton suspendButton = createHoverStyledButton("Suspend Process");
        JButton resumeButton = createHoverStyledButton("Resume Process");

        processPanel.add(createButton);
        processPanel.add(destroyButton);
        processPanel.add(suspendButton);

        processFrame.add(processPanel, BorderLayout.CENTER);

        JButton backButton = createHoverStyledButton("Back");
        backButton.addActionListener(e -> {
            processFrame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backPanel.setBackground(new Color(244, 244, 244));
        processFrame.add(backPanel, BorderLayout.SOUTH);

        processFrame.setLocationRelativeTo(parentFrame);
        processFrame.setVisible(true);
        parentFrame.setVisible(false);
    }

    private static void showSalesHistory(JFrame parentFrame) {
        JFrame salesFrame = new JFrame("Total Sales History");
        salesFrame.setSize(500, 400);
        salesFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Total Sales History", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        salesFrame.add(titleLabel, BorderLayout.NORTH);

        JTextArea salesTextArea = new JTextArea("Sales data will be displayed here.");
        salesTextArea.setEditable(false);
        salesFrame.add(new JScrollPane(salesTextArea), BorderLayout.CENTER);

        JButton backButton = createHoverStyledButton("Back");
        backButton.addActionListener(e -> {
            salesFrame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(backButton);
        salesFrame.add(backPanel, BorderLayout.SOUTH);

        salesFrame.setLocationRelativeTo(parentFrame);
        salesFrame.setVisible(true);
        parentFrame.setVisible(false);
    }

    private static JButton createHoverStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                FontMetrics fm = g2.getFontMetrics();
                int stringWidth = fm.stringWidth(getText());
                int stringHeight = fm.getAscent();
                int x = (getWidth() - stringWidth) / 2;
                int y = (getHeight() + stringHeight) / 2 - 2;
                g2.setColor(getForeground());
                g2.drawString(getText(), x, y);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(39, 39, 87));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 129));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(39, 39, 87));
            }
        });

        return button;
    }
}
