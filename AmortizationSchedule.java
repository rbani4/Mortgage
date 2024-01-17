import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class AmortizationSchedule {
    private List<Double> schedule;

    public AmortizationSchedule(double loanAmount, double annualInterestRate, int loanTerm, double downPayment) {
        Mortgage mortgage = new Mortgage(loanAmount, annualInterestRate, loanTerm, downPayment);
        double monthlyPayment = mortgage.calculateMonthlyPayment();

        schedule = new ArrayList<>();
        double remainingBalance = loanAmount;

        for (int month = 1; month <= loanTerm * 12; month++) {
            double interestPayment = remainingBalance * (annualInterestRate / 12);
            double principalPayment = monthlyPayment - interestPayment;
            remainingBalance -= principalPayment;
            schedule.add(remainingBalance);
        }
    }

    public List<Double> getSchedule() {
        return schedule;
    }

    public void sortSchedule() {
        Collections.sort(schedule);
    }

    public int searchSchedule(double targetBalance) {
        return Collections.binarySearch(schedule, targetBalance);
    }
}