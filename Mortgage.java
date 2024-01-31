class Mortgage {
    // Private instance variables to store loan details
    private double loanAmount;
    private double annualInterestRate;
    private int loanTerm;
    private double downPayment;

    // Constructor to initialize the Mortgage object with loan details
    public Mortgage(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanTerm = loanTerm;
        this.downPayment = downPayment;
    }

    // Method to calculate the monthly mortgage payment
    public double calculateMonthlyPayment() {
        // Calculate the monthly interest rate
        double monthlyInterestRate = this.annualInterestRate / 12.0;
        // Calculate the total number of months for the loan
        int totalMonths = this.loanTerm * 12;
        // Subtract the down payment from the loan amount
        this.loanAmount -= this.downPayment;
        // Use the formula to calculate the monthly payment
        return this.loanAmount * monthlyInterestRate / (1.0 - Math.pow(1.0 + monthlyInterestRate, (double)(-totalMonths)));
    }
}
