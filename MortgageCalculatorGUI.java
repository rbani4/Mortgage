import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MortgageCalculatorGUI {
    private JFrame mainFrame;

    private JLabel loggedInLabel;
    private JPanel inputPanel, resultPanel;
    private JTextField loanAmountField, interestRateField, loanTermField, downPaymentField;
    private JButton calculateButton, viewAmortizationButton;
    private JLabel resultLabel;
    private List<AmortizationEntry> amortizationSchedule;

    public MortgageCalculatorGUI(String loggedInUsername) {
        mainFrame = new JFrame("Mortgage Calculator");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 300);
        mainFrame.setBackground(new Color(240, 240, 240));

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(210, 210, 210));

        resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resultPanel.setBackground(new Color(240, 240, 240));

        loanAmountField = createInputField("Loan Amount ($)");
        interestRateField = createInputField("Annual Interest Rate (%)");
        loanTermField = createInputField("Loan Term (Years)");
        downPaymentField = createInputField("Down Payment ($)");

        calculateButton = createButton("Calculate");
        viewAmortizationButton = createButton("View Amortization Schedule");

        resultLabel = new JLabel("Monthly Payment: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        inputPanel.add(new JLabel("Loan Amount ($):"));
        inputPanel.add(loanAmountField);
        inputPanel.add(new JLabel("Annual Interest Rate (%):"));
        inputPanel.add(interestRateField);
        inputPanel.add(new JLabel("Loan Term (Years):"));
        inputPanel.add(loanTermField);
        inputPanel.add(new JLabel("Down Payment ($):"));
        inputPanel.add(downPaymentField);
        inputPanel.add(calculateButton);
        inputPanel.add(viewAmortizationButton);

        resultPanel.add(resultLabel);

        mainFrame.add(inputPanel, BorderLayout.CENTER);
        mainFrame.add(resultPanel, BorderLayout.SOUTH);

        amortizationSchedule = new ArrayList<>();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMonthlyPayment();
            }
        });

        viewAmortizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!amortizationSchedule.isEmpty()) {
                    showAmortizationSchedule();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Please calculate the monthly payment first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainFrame.setVisible(true);
    }

    private JTextField createInputField(String placeholder) {
        JTextField textField = new JTextField(10);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setMargin(new Insets(5, 5, 5, 5));
        textField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        textField.setToolTipText(placeholder);
        return textField;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 150, 50));
        button.setMargin(new Insets(10, 20, 10, 20));
        return button;
    }

    private void calculateMonthlyPayment() {
        try {
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            double annualInterestRate = Double.parseDouble(interestRateField.getText()) / 100;
            int loanTerm = Integer.parseInt(loanTermField.getText());
            double downPayment = Double.parseDouble(downPaymentField.getText());

            double monthlyPayment = calculateMonthlyPayment(loanAmount, annualInterestRate, loanTerm, downPayment);
            resultLabel.setText("Monthly Payment: $" + String.format("%.2f", monthlyPayment));
            amortizationSchedule = generateAmortizationSchedule(loanAmount, annualInterestRate, loanTerm, downPayment);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        double monthlyInterestRate = annualInterestRate / 12;
        int totalMonths = loanTerm * 12;
        loanAmount -= downPayment;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
    }

    private List<AmortizationEntry> generateAmortizationSchedule(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        double monthlyInterestRate = annualInterestRate / 12;
        int totalMonths = loanTerm * 12;
        loanAmount -= downPayment;

        List<AmortizationEntry> schedule = new ArrayList<>();
        double remainingBalance = loanAmount;

        for (int month = 1; month <= totalMonths; month++) {
            double interestPayment = remainingBalance * monthlyInterestRate;
            double principalPayment = calculateMonthlyPayment(loanAmount, annualInterestRate, loanTerm, downPayment) - interestPayment;
            remainingBalance -= principalPayment;
            schedule.add(new AmortizationEntry(month, calculateMonthlyPayment(loanAmount, annualInterestRate, loanTerm, downPayment), principalPayment, interestPayment, remainingBalance));
        }

        return schedule;
    }

    private void showAmortizationSchedule() {
        AmortizationScheduleGUI amortizationGUI = new AmortizationScheduleGUI(amortizationSchedule);
        amortizationGUI.show();
    }


    public void setVisible(boolean b) {
    }
}

