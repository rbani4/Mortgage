import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageCalculatorGUI extends JFrame {
    private JTextField mortgageAmountField;
    private JComboBox<String> amortizationYearsBox;
    private JComboBox<String> amortizationMonthsBox;
    private JComboBox<String> paymentFrequencyBox;
    private JTextField interestRateField;
    private JComboBox<String> interestTypeBox;
    private JComboBox<String> interestTermYearsBox;
    private JComboBox<String> interestTermMonthsBox;
    private JButton calculateButton;
    private JPanel cards; // Panel that uses CardLayout

    private final String CALCULATOR_PANEL = "Card with Mortgage Inputs";
    private final String RESULT_PANEL = "Card with Mortgage Results";

    public MortgageCalculatorGUI(String loggedInUsername) {
        setTitle("Mortgage Payment Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create the card layout container.
        cards = new JPanel(new CardLayout());

        JPanel card1 = createCalculatorCard();
        JPanel card2 = createResultCard();

        // Add the cards to the deck
        cards.add(card1, CALCULATOR_PANEL);
        cards.add(card2, RESULT_PANEL);

        // Add the card deck to the frame
        add(cards);
    }

    private JPanel createCalculatorCard() {
        // Create components
        mortgageAmountField = new JTextField();
        amortizationYearsBox = new JComboBox<>(generateNumberStrings(1, 30));
        amortizationMonthsBox = new JComboBox<>(generateNumberStrings(0, 11));
        paymentFrequencyBox = new JComboBox<>(new String[]{"Monthly", "Biweekly", "Weekly"});
        interestRateField = new JTextField();
        interestTypeBox = new JComboBox<>(new String[]{"Fixed", "Variable"});
        interestTermYearsBox = new JComboBox<>(generateNumberStrings(1, 10));
        interestTermMonthsBox = new JComboBox<>(generateNumberStrings(0, 11));
        calculateButton = new JButton("Calculate");

        // Layout
        JPanel card = new JPanel(new GridLayout(0, 2, 5, 5));
        card.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        card.add(new JLabel("Mortgage Amount:"));
        card.add(mortgageAmountField);
        card.add(new JLabel("Amortization Period:"));
        JPanel amortizationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        amortizationPanel.add(amortizationYearsBox);
        amortizationPanel.add(new JLabel(" Years "));
        amortizationPanel.add(amortizationMonthsBox);
        amortizationPanel.add(new JLabel(" Months "));
        card.add(amortizationPanel);
        card.add(new JLabel("Payment Frequency:"));
        card.add(paymentFrequencyBox);
        card.add(new JLabel("Interest Rate: (%)"));
        card.add(interestRateField);
        card.add(new JLabel("Interest Type:"));
        card.add(interestTypeBox);
        card.add(new JLabel("Interest Term:"));
        JPanel interestTermPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        interestTermPanel.add(interestTermYearsBox);
        interestTermPanel.add(new JLabel(" Years "));
        interestTermPanel.add(interestTermMonthsBox);
        interestTermPanel.add(new JLabel(" Months "));
        card.add(interestTermPanel);
        card.add(calculateButton);

        // Listeners
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCalculationResults();
            }
        });

        return card;
    }

    private JPanel createResultCard() {
        JPanel card = new JPanel(new BorderLayout());
        JLabel resultLabel = new JLabel("Calculation results will be shown here.", SwingConstants.CENTER);
        card.add(resultLabel, BorderLayout.CENTER);

        // Create a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch back to the calculator panel
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, CALCULATOR_PANEL);
            }
        });

        // Create a panel for the back button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);

        // Add the button panel to the bottom of the result card
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void showCalculationResults() {
        try {
            // Retrieve and parse inputs
            double principal = Double.parseDouble(mortgageAmountField.getText());
            double annualInterestRate = Double.parseDouble(interestRateField.getText());
            int amortizationYears = Integer.parseInt((String) amortizationYearsBox.getSelectedItem());
            int amortizationMonths = Integer.parseInt((String) amortizationMonthsBox.getSelectedItem());
            int totalMonths = amortizationYears * 12 + amortizationMonths;
            String paymentFrequency = (String) paymentFrequencyBox.getSelectedItem();

            // Convert annual interest rate to monthly and calculate monthly payment
            double monthlyInterestRate = annualInterestRate / 100 / 12;
            double monthlyPayment = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths)) /
                    (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);

            double finalPayment = monthlyPayment;
            String paymentMessage = "<html>Monthly Payment: <b>$%.2f</b></html>";

            // Adjust payment based on frequency
            if (paymentFrequency.equals("Biweekly")) {
                finalPayment = monthlyPayment / 2;
                paymentMessage = "<html>Biweekly Payment: <b>$%.2f</b></html>";
            } else if (paymentFrequency.equals("Weekly")) {
                finalPayment = monthlyPayment / 4;
                paymentMessage = "<html>Weekly Payment: <b>$%.2f</b></html>";
            }

            // Update the result card with the calculation
            JLabel resultLabel = new JLabel(String.format(paymentMessage, finalPayment), SwingConstants.CENTER);
            JPanel resultCard = (JPanel) cards.getComponent(1); // Assuming the result card is the second component
            resultCard.removeAll(); // Remove previous results if any
            resultCard.add(resultLabel);
            resultCard.revalidate(); // Refresh the panel to show updated results

            // Switch to the result card
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, RESULT_PANEL);
        } catch (NumberFormatException ex) {
            // Handle invalid input format
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] generateNumberStrings(int start, int end) {
        String[] numbers = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = String.valueOf(i);
        }
        return numbers;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            private String loggedInUsername;

            public void run() {
                new MortgageCalculatorGUI(this.loggedInUsername).setVisible(true);
            }
        });
    }
}
