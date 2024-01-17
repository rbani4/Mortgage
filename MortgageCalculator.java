import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class MortgageCalculator {
    public static void main(String[] args) {
        double loanAmount = 200000;
        double annualInterestRate = 0.05;
        int loanTerm = 30;
        double downPayment = 20000;

        AmortizationSchedule amortizationSchedule = new AmortizationSchedule(loanAmount, annualInterestRate, loanTerm, downPayment);
        List<Double> schedule = amortizationSchedule.getSchedule();

        double monthlyPayment = schedule.get(0);
        System.out.println("Monthly Payment: $" + monthlyPayment);

        amortizationSchedule.sortSchedule();
        System.out.println("Sorted Amortization Schedule: " + schedule);

        double totalInterestPaid = Calculator.calculateTotalInterestPaid(schedule);
        System.out.println("Total Interest Paid over the Life of the Loan: $" + totalInterestPaid);

        double targetBalance = 150000;
        int month = amortizationSchedule.searchSchedule(targetBalance);
        if (month >= 0) {
            System.out.println("Found balance $" + targetBalance + " at month " + (month + 1));
        } else {
            System.out.println("Balance $" + targetBalance + " not found in the schedule.");
        }
    }
}
