import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AmortizationTableModel extends AbstractTableModel {
    // Array of column names for the table
    private final String[] columnNames = new String[]{"Month", "Payment", "Principal", "Interest", "Remaining Balance"};

    // List to store AmortizationEntry objects
    private final List<AmortizationEntry> data;

    // Constructor to initialize the table model with data
    public AmortizationTableModel(List<AmortizationEntry> data) {
        this.data = data;
    }

    // Get the number of rows in the table
    public int getRowCount() {
        return this.data.size();
    }

    // Get the number of columns in the table
    public int getColumnCount() {
        return this.columnNames.length;
    }

    // Get the value at a specific row and column in the table
    public Object getValueAt(int rowIndex, int columnIndex) {
        AmortizationEntry entry = (AmortizationEntry)this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entry.getMonth();
            case 1:
                return String.format("%.2f", entry.getMonthlyPayment());
            case 2:
                return String.format("%.2f", entry.getPrincipalPayment());
            case 3:
                return String.format("%.2f", entry.getInterestPayment());
            case 4:
                return String.format("%.2f", entry.getRemainingBalance());
            default:
                return null;
        }
    }

    // Get the column name for a specific column index
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}
