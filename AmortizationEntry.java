import javax.swing.SwingUtilities;
class AmortizationEntry {
    private int month;
    private double monthlyPayment;
    private double principalPayment;
    private double interestPayment;
    private double remainingBalance;

    public AmortizationEntry(int month, double monthlyPayment, double principalPayment, double interestPayment, double remainingBalance) {
        this.month = month;
        this.monthlyPayment = monthlyPayment;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.remainingBalance = remainingBalance;
    }

    @Override
    public String toString() {
        return String.format("%-7d%-10.2f%-11.2f%-11.2f%-16.2f", month, monthlyPayment, principalPayment, interestPayment, remainingBalance);
    }

    public int getMonth() {
        return month;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public double getPrincipalPayment() {
        return principalPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
