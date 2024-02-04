import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AmortizationScheduleGUI {
    private JFrame frame = new JFrame("Amortization Schedule");
    private JTable table;
    private JScrollPane scrollPane;

    public AmortizationScheduleGUI(List<AmortizationEntry> amortizationSchedule) {
        this.frame.setDefaultCloseOperation(2);
        this.frame.setSize(800, 400);
        AmortizationTableModel model = new AmortizationTableModel(amortizationSchedule);
        this.table = new JTable(model);
        this.table.setFont(new Font("Arial", 0, 14));
        this.table.getTableHeader().setFont(new Font("Arial", 1, 14));
        this.scrollPane = new JScrollPane(this.table);
        this.frame.add(this.scrollPane);
        this.frame.setVisible(true);
    }

    public void show() {
        this.frame.setVisible(true);
    }
}
