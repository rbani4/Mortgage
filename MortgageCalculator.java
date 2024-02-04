import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MortgageCalculator {
    public static void main(String[] args) {
        // Initialize loan details
        double loanAmount = 200000;
        double annualInterestRate = 0.05;
        int loanTerm = 30;
        double downPayment = 20000;

        // Create an amortization schedule
        AmortizationSchedule amortizationSchedule = new AmortizationSchedule(loanAmount, annualInterestRate, loanTerm, downPayment);
        List<Double> schedule = amortizationSchedule.getSchedule();

        // Get the monthly payment and print it
        double monthlyPayment = schedule.get(0);
        System.out.println("Monthly Payment: $" + monthlyPayment);

        // Sort the amortization schedule and print it
        amortizationSchedule.sortSchedule();
        System.out.println("Sorted Amortization Schedule: " + schedule);

        // Calculate and print the total interest paid
        double totalInterestPaid = Calculator.calculateTotalInterestPaid(schedule);
        System.out.println("Total Interest Paid over the Life of the Loan: $" + totalInterestPaid);

        // Search for a target balance in the schedule and print the result
        double targetBalance = 150000;
        int month = amortizationSchedule.searchSchedule(targetBalance);
        if (month >= 0) {
            System.out.println("Found balance $" + targetBalance + " at month " + (month + 1));
        } else {
            System.out.println("Balance $" + targetBalance + " not found in the schedule.");
        }
    }
}
