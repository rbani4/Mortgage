import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MortgageCalculatorGUI {
    private JFrame frame;
    private JTextField principalField, interestField, termField;
    private JButton calculateButton;
    private JLabel monthlyPaymentLabel;


    public MortgageCalculatorGUI() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        principalField = new JTextField();
        interestField = new JTextField();
        termField = new JTextField();
        calculateButton = new JButton("Calculate");
        monthlyPaymentLabel = new JLabel("Monthly Payment: ");


        frame.add(new JLabel("Principal: "));
        frame.add(principalField);
        frame.add(new JLabel("Annual Interest Rate: "));
        frame.add(interestField);
        frame.add(new JLabel("Term (Years): "));
        frame.add(termField);
        frame.add(calculateButton);
        frame.add(monthlyPaymentLabel);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double principal = Double.parseDouble(principalField.getText());
                double interestRate = Double.parseDouble(interestField.getText());
                int term = Integer.parseInt(termField.getText());


                MortgageCalculator calculator = new MortgageCalculator(principal, interestRate, term);
                double monthlyPayment = calculator.calculateMonthlyPayment();


                monthlyPaymentLabel.setText("Monthly Payment: " + monthlyPayment);
            }
        });
    }


    public void show() {
        frame.setVisible(true);
    }
}

