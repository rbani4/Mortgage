public class MortgageCalculator {
    private double principal;
    private double annualInterestRate;
    private int termInYears;


    public MortgageCalculator(double principal, double annualInterestRate, int termInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.termInYears = termInYears;
    }


    public double calculateMonthlyPayment() {
        double monthlyInterestRate = this.annualInterestRate / 100 / 12;
        int totalNumberOfPayments = this.termInYears * 12;


        return (this.principal * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalNumberOfPayments));
    }
}
