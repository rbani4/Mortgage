import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private boolean loggedIn = false;
    private String loggedInUsername;

    public LoginWindow() {
        setTitle("Mortgage Payment Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        inputPanel.setBackground(new Color(230, 230, 230));

        JLabel titleLabel = new JLabel("Mortgage Payment Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = createStyledButton("Login");
        signupButton = createStyledButton("Signup");

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);
        inputPanel.add(signupButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (authenticateUser(username, password)) {
                    loggedInUsername = username;
                    loggedIn = true;
                    openMortgageCalculator();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (signupUser(username, password)) {
                    loggedInUsername = username;
                    loggedIn = true;
                    clearFields();
                    JOptionPane.showMessageDialog(LoginWindow.this, "Account created!", "Signup Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Password must be 8 or more characters", "Signup Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addHoverEffect(loginButton);
        addHoverEffect(signupButton);
    }

    private boolean authenticateUser(String username, String password) {
        return !username.isEmpty() && password.length() >= 8;
    }

    private boolean signupUser(String username, String password) {
        return !username.isEmpty() && password.length() >= 8;
    }

    private void openMortgageCalculator() {
        MortgageCalculatorGUI mortgageCalculator = new MortgageCalculatorGUI(loggedInUsername);
        mortgageCalculator.setVisible(true);

        dispose();
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private JButton createStyledButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 150, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 170, 70));
                button.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(50, 150, 50));
                button.setFont(new Font("Arial", Font.BOLD, 14));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}