class AmortizationEntry {
    // Private instance variables to store data
    private int month;
    private double monthlyPayment;
    private double principalPayment;
    private double interestPayment;
    private double remainingBalance;

    // Constructor to initialize the object
    public AmortizationEntry(int month, double monthlyPayment, double principalPayment, double interestPayment, double remainingBalance) {
        this.month = month;
        this.monthlyPayment = monthlyPayment;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.remainingBalance = remainingBalance;
    }

    // Method to format and return a string representation of the object
    public String toString() {
        return String.format("%-7d%-10.2f%-11.2f%-11.2f%-16.2f", this.month, this.monthlyPayment, this.principalPayment, this.interestPayment, this.remainingBalance);
    }

    // Getter methods to access private instance variables
    public int getMonth() {
        return this.month;
    }

    public double getRemainingBalance() {
        return this.remainingBalance;
    }

    public double getInterestPayment() {
        return this.interestPayment;
    }

    public double getPrincipalPayment() {
        return this.principalPayment;
    }

    public double getMonthlyPayment() {
        return this.monthlyPayment;
    }
}
