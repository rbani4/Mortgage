import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AmortizationTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Month", "Payment", "Principal", "Interest", "Remaining Balance"};
    private final List<AmortizationEntry> data;

    public AmortizationTableModel(List<AmortizationEntry> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AmortizationEntry entry = data.get(rowIndex);
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

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}