import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Mortgage {
    private double loanAmount;
    private double annualInterestRate;
    private int loanTerm;
    private double downPayment;

    public Mortgage(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanTerm = loanTerm;
        this.downPayment = downPayment;
    }

    public double calculateMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 12;
        int totalMonths = loanTerm * 12;
        loanAmount -= downPayment;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
    }
}