import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Self {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Self::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Circular Button App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light Gray Background

        // Header panel (top) with reduced height
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color startColor = new Color(25, 25, 112); // Dark Blue Gradient Start
                Color endColor = new Color(230, 240, 255); // Very Light Blue Gradient End
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 70)); // Reduced height

        JLabel headerLabel = new JLabel("Office Orbit", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE); // White Heading Text
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Button panel (center) with reduced gap
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 240, 255)); // Very Light Blue Panel Background
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 15)); // Reduced gap
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Button names and icons
        String[] buttonNames = {
                "Self Service", "Expense Claim", "Book a Travel", "Applause"
        };
        String[] iconPaths = {
                "icons/self_service.png", "icons/expense_claim.png", "icons/book_travel.png",
                "icons/applause.png"
        };

        // Add buttons to the panel
        for (int i = 0; i < buttonNames.length; i++) {
            JPanel buttonPanelItem = createCircularButton(buttonNames[i], iconPaths[i]);
            buttonPanel.add(buttonPanelItem);
        }

        // Footer panel (bottom)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(25, 25, 112)); // Dark Blue Footer Background
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        JLabel footerLabel = new JLabel("Powered by Office Orbit Solutions", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        footerLabel.setForeground(Color.LIGHT_GRAY); // Light Gray Footer Text
        footerPanel.add(footerLabel);

        // Add panels to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createCircularButton(String text, String iconPath) {
        // Panel to hold button and label
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // Create button
        JButton button = new JButton();
        button.setLayout(new BorderLayout());

        // Load icon
        try {
            BufferedImage icon = ImageIO.read(new File(iconPath));
            Image scaledIcon = icon.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledIcon));
        } catch (IOException e) {
            System.err.println("Error loading icon: " + iconPath);
        }

        // Make button circular
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorderPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(180, 200, 230)); // Light Blue Hover Background
                button.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(null);
                button.repaint();
            }
        });

        // Add button action listeners


        // Button circular effect
        button.setUI(new CircleButtonUI());

        // Add button to the panel
        panel.add(button, BorderLayout.CENTER);

        // Add text label below the button
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(Color.BLACK); // Black Label Text
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.SOUTH);

        return panel;
    }
}

// CircleButtonUI Class
class CircleButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int diameter = Math.min(c.getWidth(), c.getHeight());
        int x = (c.getWidth() - diameter) / 2;
        int y = (c.getHeight() - diameter) / 2;

        // Button background color
        g2.setColor(new Color(25, 25, 112)); // Dark Blue Button Background
        g2.fillOval(x, y, diameter, diameter);

        // Button border effect
        g2.setColor(Color.WHITE); // White Border
        g2.drawOval(x, y, diameter - 1, diameter - 1);
        g2.dispose();

        super.paint(g, c);
    }
}