import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginWindow extends JFrame {
    // Fields for UI components and user state
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private boolean loggedIn = false;
    private String loggedInUsername;

    // Constructor for the LoginWindow
    public LoginWindow() {
        // Set up the JFrame
        this.setTitle("Mortgage Payment Calculator");
        this.setDefaultCloseOperation(3);
        this.setSize(400, 250);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);

        // Create UI components and layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        inputPanel.setBackground(new Color(230, 230, 230));
        JLabel titleLabel = new JLabel("Mortgage Payment Calculator");
        titleLabel.setFont(new Font("Arial", 1, 18));
        titleLabel.setHorizontalAlignment(0);
        JLabel usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        this.loginButton = this.createStyledButton("Login");
        this.signupButton = this.createStyledButton("Signup");
        inputPanel.add(usernameLabel);
        inputPanel.add(this.usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(this.passwordField);
        inputPanel.add(this.loginButton);
        inputPanel.add(this.signupButton);
        panel.add(titleLabel, "North");
        panel.add(inputPanel, "Center");
        this.add(panel);

        // Add action listeners and hover effects to buttons
        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login button click
                String username = LoginWindow.this.usernameField.getText();
                char[] passwordChars = LoginWindow.this.passwordField.getPassword();
                String password = new String(passwordChars);
                if (LoginWindow.this.authenticateUser(username, password)) {
                    LoginWindow.this.loggedInUsername = username;
                    LoginWindow.this.loggedIn = true;
                    LoginWindow.this.openMortgageCalculator();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password.", "Login Error", 0);
                }
            }
        });
        this.signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle signup button click
                String username = LoginWindow.this.usernameField.getText();
                char[] passwordChars = LoginWindow.this.passwordField.getPassword();
                String password = new String(passwordChars);
                if (LoginWindow.this.signupUser(username, password)) {
                    LoginWindow.this.loggedInUsername = username;
                    LoginWindow.this.loggedIn = true;
                    LoginWindow.this.clearFields();
                    JOptionPane.showMessageDialog(LoginWindow.this, "Account created!", "Signup Success", 1);
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Password must be 8 or more characters", "Signup Error", 0);
                }
            }
        });
        this.addHoverEffect(this.loginButton);
        this.addHoverEffect(this.signupButton);
    }

    // Method to authenticate a user
    private boolean authenticateUser(String username, String password) {
        return !username.isEmpty() && password.length() >= 8;
    }

    // Method to sign up a user
    private boolean signupUser(String username, String password) {
        return !username.isEmpty() && password.length() >= 8;
    }

    // Method to open the MortgageCalculatorGUI
    private void openMortgageCalculator() {
        MortgageCalculatorGUI mortgageCalculator = new MortgageCalculatorGUI(this.loggedInUsername);
        mortgageCalculator.setVisible(true);
        this.dispose();
    }

    // Method to clear text fields
    private void clearFields() {
        this.usernameField.setText("");
        this.passwordField.setText("");
    }

    // Method to create a styled button
    private JButton createStyledButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", 1, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 150, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    // Method to add hover effect to a button
    private void addHoverEffect(final JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 170, 70));
                button.setFont(new Font("Arial", 1, 15));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(50, 150, 50));
                button.setFont(new Font("Arial", 1, 14));
            }
        });
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}
