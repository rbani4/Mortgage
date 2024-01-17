import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AmortizationScheduleGUI {
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;

    public AmortizationScheduleGUI(List<AmortizationEntry> amortizationSchedule) {
        frame = new JFrame("Amortization Schedule");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        AmortizationTableModel model = new AmortizationTableModel(amortizationSchedule);

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        scrollPane = new JScrollPane(table);

        frame.add(scrollPane);

        frame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }
}