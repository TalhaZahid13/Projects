import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SolarManagementSystem {
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create database connection
        DatabaseManager.createTables();

        // Launch splash screen first, then login screen
        SwingUtilities.invokeLater(() -> {
            new SplashScreen();
        });
    }
}

// Database Manager class to handle all database operations
class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:SolarDB;create=true";

    // Connect to the database
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Create necessary tables if they don't exist
    public static void createTables() {
        try (Connection conn = getConnection()) {
            // Create Users table
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "username VARCHAR(50) UNIQUE, password VARCHAR(50), role VARCHAR(20), " +
                    "name VARCHAR(100), email VARCHAR(100), phone VARCHAR(20))");

            // Create Panels table
            stmt.execute("CREATE TABLE panels (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "company VARCHAR(100), grade VARCHAR(50), capacity DOUBLE, " +
                    "price DOUBLE, installation_date DATE)");

            // Create Customer table
            stmt.execute("CREATE TABLE customers (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(100), email VARCHAR(100), phone VARCHAR(20), " +
                    "address VARCHAR(200), panels_installed INT, " +
                    "installation_date DATE, total_cost DOUBLE)");

            // Create Production table
            stmt.execute("CREATE TABLE production (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "customer_id INT, date DATE, units_produced DOUBLE, " +
                    "FOREIGN KEY (customer_id) REFERENCES customers(id))");

            // Insert default admin user
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (username, password, role, name, email, phone) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, "admin");
            ps.setString(2, "admin123");
            ps.setString(3, "admin");
            ps.setString(4, "System Administrator");
            ps.setString(5, "admin@solarcompany.com");
            ps.setString(6, "555-123-4567");
            ps.executeUpdate();

            // Insert a demo user
            ps = conn.prepareStatement(
                    "INSERT INTO users (username, password, role, name, email, phone) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, "user");
            ps.setString(2, "user123");
            ps.setString(3, "user");
            ps.setString(4, "John Doe");
            ps.setString(5, "john@example.com");
            ps.setString(6, "555-987-6543");
            ps.executeUpdate();

            // Insert sample panel data
            ps = conn.prepareStatement(
                    "INSERT INTO panels (company, grade, capacity, price, installation_date) " +
                            "VALUES (?, ?, ?, ?, CURRENT_DATE)");

            String[][] panelData = {
                    {"SunPower", "A+", "400", "1200.50"},
                    {"LG Solar", "A", "375", "1100.25"},
                    {"Canadian Solar", "B+", "350", "950.75"},
                    {"JinkoSolar", "B", "325", "850.00"},
                    {"Trina Solar", "B+", "340", "920.30"}
            };

            for (String[] panel : panelData) {
                ps.setString(1, panel[0]);
                ps.setString(2, panel[1]);
                ps.setDouble(3, Double.parseDouble(panel[2]));
                ps.setDouble(4, Double.parseDouble(panel[3]));
                ps.executeUpdate();
            }

            // Insert sample customer data
            ps = conn.prepareStatement(
                    "INSERT INTO customers (name, email, phone, address, panels_installed, installation_date, total_cost) " +
                            "VALUES (?, ?, ?, ?, ?, CURRENT_DATE, ?)");

            Object[][] customerData = {
                    {"Alice Smith", "alice@example.com", "555-111-2222", "123 Solar St", 5, 6000.00},
                    {"Bob Johnson", "bob@example.com", "555-222-3333", "456 Energy Ave", 3, 3500.00},
                    {"Carol Williams", "carol@example.com", "555-333-4444", "789 Power Blvd", 8, 9500.00},
                    {"David Brown", "david@example.com", "555-444-5555", "321 Sunshine Dr", 4, 4800.00},
                    {"Eva Garcia", "eva@example.com", "555-555-6666", "654 Panel Way", 6, 7200.00}
            };

            for (Object[] customer : customerData) {
                ps.setString(1, (String)customer[0]);
                ps.setString(2, (String)customer[1]);
                ps.setString(3, (String)customer[2]);
                ps.setString(4, (String)customer[3]);
                ps.setInt(5, (Integer)customer[4]);
                ps.setDouble(6, (Double)customer[5]);
                ps.executeUpdate();
            }

            // Insert sample production data
            ps = conn.prepareStatement(
                    "INSERT INTO production (customer_id, date, units_produced) " +
                            "VALUES (?, CURRENT_DATE, ?)");

            for (int i = 1; i <= 5; i++) {
                ps.setInt(1, i);
                // Random production between 20-40 kWh per day
                double randomProduction = 20 + Math.random() * 20;
                ps.setDouble(2, randomProduction);
                ps.executeUpdate();
            }

            System.out.println("Database tables created and sample data added successfully.");

        } catch (SQLException e) {
            // Ignore error if tables already exist
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error setting up database: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Verify login credentials
    public static String[] validateLogin(String username, String password) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, role, name FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String[] result = new String[3];
                result[0] = rs.getString("id");
                result[1] = rs.getString("role");
                result[2] = rs.getString("name");
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Login validation error: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // Get production data for display on user dashboard
    public static double getTotalUnitsProduced() {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(units_produced) FROM production");

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Get panel statistics for admin dashboard
    public static Object[][] getPanelStatistics() {
        List<Object[]> panelStats = new ArrayList<>();

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT company, grade, COUNT(*) as count, AVG(capacity) as avg_capacity " +
                            "FROM panels GROUP BY company, grade");

            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getString("company");
                row[1] = rs.getString("grade");
                row[2] = rs.getInt("count");
                row[3] = rs.getDouble("avg_capacity");
                panelStats.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return panelStats.toArray(new Object[0][]);
    }

    // Get customer data for admin dashboard
    public static Object[][] getCustomerData() {
        List<Object[]> customerData = new ArrayList<>();

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT c.id, c.name, c.email, c.phone, c.panels_installed, " +
                            "c.total_cost, SUM(p.units_produced) as total_production " +
                            "FROM customers c LEFT JOIN production p ON c.id = p.customer_id " +
                            "GROUP BY c.id, c.name, c.email, c.phone, c.panels_installed, c.total_cost");

            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("name");
                row[2] = rs.getString("email");
                row[3] = rs.getString("phone");
                row[4] = rs.getInt("panels_installed");
                row[5] = rs.getDouble("total_cost");
                row[6] = rs.getDouble("total_production");
                customerData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerData.toArray(new Object[0][]);
    }

    // Get sales summary for admin dashboard
    public static double[] getSalesSummary() {
        double[] summary = new double[3]; // total sales, cost, profit

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();

            // Total sales
            ResultSet rs = stmt.executeQuery("SELECT SUM(total_cost) FROM customers");
            if (rs.next()) {
                summary[0] = rs.getDouble(1);
            }

            // Estimated cost (70% of sales for this example)
            summary[1] = summary[0] * 0.7;

            // Profit
            summary[2] = summary[0] - summary[1];

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summary;
    }
}

// Splash Screen class
class SplashScreen extends JFrame {
    public SplashScreen() {
        // Remove window decorations
        setUndecorated(true);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(46, 204, 113), 3));

        // Create content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add title
        JLabel titleLabel = new JLabel("Solar Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(46, 204, 113));

        // Add logo/image (simulated with text)
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(Color.WHITE);

        JLabel logoLabel = new JLabel("☀️", JLabel.CENTER);
        logoLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        logoLabel.setForeground(new Color(241, 196, 15));
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        // Add version and copyright
        JLabel versionLabel = new JLabel("Version 1.0.0", JLabel.CENTER);
        JLabel copyrightLabel = new JLabel("© 2025 Solar Company. All Rights Reserved.", JLabel.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(versionLabel);
        bottomPanel.add(copyrightLabel);

        // Add progress bar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(46, 204, 113));

        // Assemble content panel
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(logoPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add content panel and progress bar to main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Make visible
        setVisible(true);

        // Start progress bar animation and timer
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50); // Total time: ~5 seconds
                    progressBar.setValue(i);
                }
                Thread.sleep(100); // Small delay at the end

                // Close splash screen and show login page
                SwingUtilities.invokeLater(() -> {
                    dispose(); // Close splash screen
                    new LoginPage(); // Open login page
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

// Login page class
class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> roleComboBox;

    public LoginPage() {
        setTitle("Solar Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel headerLabel = new JLabel("Solar Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Login form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel roleLabel = new JLabel("Login As:");
        roleComboBox = new JComboBox<>(new String[]{"User", "Admin"});

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        formPanel.add(roleLabel);
        formPanel.add(roleComboBox);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(exitButton);
        formPanel.add(loginButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Footer
        JLabel footerLabel = new JLabel("© 2025 Solar Company", JLabel.CENTER);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this,
                            "Please enter both username and password",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] userData = DatabaseManager.validateLogin(username, password);

                if (userData != null) {
                    String role = userData[1];
                    String name = userData[2];

                    // Check if the selected role matches the actual user role
                    String selectedRole = roleComboBox.getSelectedItem().toString().toLowerCase();

                    if (role.equalsIgnoreCase(selectedRole)) {
                        JOptionPane.showMessageDialog(LoginPage.this,
                                "Login successful! Welcome " + name,
                                "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Open the appropriate dashboard
                        if (role.equalsIgnoreCase("admin")) {
                            new AdminDashboard();
                        } else {
                            new UserDashboard(Integer.parseInt(userData[0]));
                        }

                        // Close the login window
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this,
                                "You don't have permission to login as " + selectedRole,
                                "Access Denied", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this,
                            "Invalid username or password",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Exit button action
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}

// User Dashboard class
class UserDashboard extends JFrame {
    private int userId;

    public UserDashboard(int userId) {
        this.userId = userId;

        setTitle("Solar Management System - User Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("User Dashboard", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Statistics panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Solar Production Statistics"));

        // Get data from database
        double totalUnits = DatabaseManager.getTotalUnitsProduced();

        // Create info cards
        statsPanel.add(createInfoCard("Total Units Produced",
                new DecimalFormat("#,###.##").format(totalUnits) + " kWh",
                new Color(46, 204, 113)));

        statsPanel.add(createInfoCard("Electricity Generated",
                new DecimalFormat("#,###.##").format(totalUnits) + " kWh",
                new Color(52, 152, 219)));

        statsPanel.add(createInfoCard("Carbon Offset",
                new DecimalFormat("#,###.##").format(totalUnits * 0.85) + " kg CO₂",
                new Color(155, 89, 182)));

        statsPanel.add(createInfoCard("Equivalent Trees Planted",
                new DecimalFormat("#,###").format(totalUnits * 0.15) + " trees",
                new Color(26, 188, 156)));

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // Create production chart panel (simplified for this example)
        JPanel chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Daily Production"));
        chartPanel.setPreferredSize(new Dimension(getWidth(), 200));

        // Simplified chart visualization
        JTextArea chartArea = new JTextArea();
        chartArea.setEditable(false);
        chartArea.setText("Daily production chart visualization would be here.\n" +
                "In a real application, this would use JavaFX charts or JFreeChart library.");

        chartPanel.add(chartArea);
        mainPanel.add(chartPanel, BorderLayout.SOUTH);

        // Add to frame
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createInfoCard(String title, String value, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(color);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
}

// Admin Dashboard class
class AdminDashboard extends JFrame {
    private JTabbedPane tabbedPane;

    public AdminDashboard() {
        setTitle("Solar Management System - Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Overview", createOverviewPanel());
        tabbedPane.addTab("Customers", createCustomersPanel());
        tabbedPane.addTab("Panel Information", createPanelInfoPanel());

        // Header with logout button
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Get sales data
        double[] salesData = DatabaseManager.getSalesSummary();
        DecimalFormat currencyFormat = new DecimalFormat("$#,###.##");

        // Create a panel for financial metrics
        JPanel financialPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        financialPanel.setBorder(BorderFactory.createTitledBorder("Financial Overview"));

        financialPanel.add(createInfoCard("Total Sales",
                currencyFormat.format(salesData[0]),
                new Color(46, 204, 113)));

        financialPanel.add(createInfoCard("Total Costs",
                currencyFormat.format(salesData[1]),
                new Color(231, 76, 60)));

        financialPanel.add(createInfoCard("Profit",
                currencyFormat.format(salesData[2]),
                new Color(52, 152, 219)));

        // Customer statistics
        Object[][] customerData = DatabaseManager.getCustomerData();
        JPanel customerStatsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        customerStatsPanel.setBorder(BorderFactory.createTitledBorder("Customer Statistics"));

        customerStatsPanel.add(createInfoCard("Total Customers",
                String.valueOf(customerData.length),
                new Color(155, 89, 182)));

        int totalPanels = 0;
        for (Object[] customer : customerData) {
            totalPanels += (int)customer[4];
        }

        customerStatsPanel.add(createInfoCard("Total Panels Installed",
                String.valueOf(totalPanels),
                new Color(241, 196, 15)));

        double totalProduction = 0;
        for (Object[] customer : customerData) {
            totalProduction += (double)customer[6];
        }

        customerStatsPanel.add(createInfoCard("Total Production",
                new DecimalFormat("#,###.##").format(totalProduction) + " kWh",
                new Color(230, 126, 34)));

        // Add the panels to the main panel
        JPanel topPanel = new JPanel(new BorderLayout(0, 15));
        topPanel.add(financialPanel, BorderLayout.NORTH);
        topPanel.add(customerStatsPanel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);

        // Create a simple chart area (placeholder)
        JPanel chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Sales & Production Analysis"));

        JTextArea chartArea = new JTextArea();
        chartArea.setEditable(false);
        chartArea.setText("Sales and production charts would be displayed here.\n" +
                "In a real application, this would use JavaFX charts or JFreeChart library.");

        chartPanel.add(chartArea);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Get customer data
        Object[][] customerData = DatabaseManager.getCustomerData();

        // Column names
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Panels", "Total Cost", "Production (kWh)"};

        // Create table
        JTable customerTable = new JTable(customerData, columnNames);
        customerTable.setFillsViewportHeight(true);
        customerTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(customerTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add some controls for customer management
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(new JButton("Add Customer"));
        controlPanel.add(new JButton("Edit Selected"));
        controlPanel.add(new JButton("Delete Selected"));
        controlPanel.add(new JButton("Refresh Data"));

        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPanelInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Get panel data
        Object[][] panelData = DatabaseManager.getPanelStatistics();

        // Column names
        String[] columnNames = {"Company", "Grade", "Count", "Avg Capacity (W)"};

        // Create table
        JTable panelTable = new JTable(panelData, columnNames);
        panelTable.setFillsViewportHeight(true);
        panelTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(panelTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add some controls for panel management
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(new JButton("Add Panel Type"));
        controlPanel.add(new JButton("Edit Selected"));
        controlPanel.add(new JButton("Delete Selected"));
        controlPanel.add(new JButton("Refresh Data"));

        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createInfoCard(String title, String value, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(color);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
}