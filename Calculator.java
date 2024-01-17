import java.util.List;

class Calculator {
    public static double calculateTotalInterestPaid(List<Double> schedule) {
        double totalInterest = 0;
        for (double balance : schedule) {
            totalInterest += balance;
        }
        return totalInterest;
    }
}