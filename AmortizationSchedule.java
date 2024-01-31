import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AmortizationSchedule {
    private List<Double> schedule;

    // Constructor to create an amortization schedule
    public AmortizationSchedule(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        // Create a Mortgage object to calculate monthly payments
        Mortgage mortgage = new Mortgage(loanAmount, annualInterestRate, loanTerm, downPayment);
        double monthlyPayment = mortgage.calculateMonthlyPayment();
        this.schedule = new ArrayList();
        double remainingBalance = loanAmount;

        // Calculate the amortization schedule for each month
        for(int month = 1; month <= loanTerm * 12; ++month) {
            double interestPayment = remainingBalance * (annualInterestRate / 12.0);
            double principalPayment = monthlyPayment - interestPayment;
            remainingBalance -= principalPayment;
            this.schedule.add(remainingBalance);
        }
    }

    // Getter method to retrieve the amortization schedule
    public List<Double> getSchedule() {
        return this.schedule;
    }

    // Method to sort the schedule in ascending order
    public void sortSchedule() {
        Collections.sort(this.schedule);
    }

    // Method to search for a target balance in the schedule
    public int searchSchedule(double targetBalance) {
        return Collections.binarySearch(this.schedule, targetBalance);
    }
}
