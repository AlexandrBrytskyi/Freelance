package jdbcapp.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalOperationsFrame extends JFrame {

    private JPanel mainPanel;
    private JPanel operationAndColumnNumPanel;
    private JComboBox operation;
    private JComboBox columnName;
    private JTextField result;
    private JButton calculateButton;
    private GUI gui;

    public AdditionalOperationsFrame(String[] colnames, GUI gui) throws HeadlessException {
        super("Function count");
        this.gui = gui;
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initMainPanel(colnames);
        add(mainPanel);
        setVisible(true);
    }

    private void initMainPanel(String[] columnNames) {
        mainPanel = new JPanel(new GridLayout(3, 1));
        operationAndColumnNumPanel = new JPanel(new FlowLayout());
        operation = new JComboBox();
        operation.setToolTipText("Function to count");
        operation.setPreferredSize(new Dimension(getWidth()/2-30, 25));
        addOperations(operation);
        columnName = new JComboBox(columnNames);
        columnName.setSelectedIndex(0);
        columnName.setToolTipText("Name of the column");
        columnName.setPreferredSize(new Dimension(getWidth()/2-30, 25));
        operationAndColumnNumPanel.add(operation);
        operationAndColumnNumPanel.add(this.columnName);
        result = new JTextField();
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setEditable(false);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    result.setText(defineOperationAndCalculate());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, e1.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        mainPanel.add(operationAndColumnNumPanel);
        mainPanel.add(result);
        mainPanel.add(calculateButton);

    }

    private String defineOperationAndCalculate() throws Exception {
        String operationName = (String) operation.getSelectedItem();
        String column = (String) columnName.getSelectedItem();
        return gui.countFunction(operationName, column);
    }

    private void addOperations(JComboBox operation) {
        operation.addItem("MIN");
        operation.addItem("MAX");
        operation.addItem("AVG");
        operation.addItem("MEDIAN");
        operation.setSelectedIndex(0);
    }
}
