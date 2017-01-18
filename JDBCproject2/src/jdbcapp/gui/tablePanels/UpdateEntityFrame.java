package jdbcapp.gui.tablePanels;

import jdbcapp.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class UpdateEntityFrame extends JFrame {

    private JPanel mainPanel;
    private JButton submit;
    private JButton cancel;
    private Map<String, JTextField> fields;
    private Map<String, String> fieldNamesAndValues;
    private GUI gui;
    private String tableName;
    private String idFieldName = null;

    public UpdateEntityFrame(Map<String, String> fieldNamesAndValues, String tableName, GUI gui) throws HeadlessException {
        super("Update entity");
        this.fieldNamesAndValues = fieldNamesAndValues;
        this.gui = gui;
        this.tableName = tableName;
        initViewComponents();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initViewComponents() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2));
        fields = new HashMap<>();
        for (Map.Entry<String, String> entry : fieldNamesAndValues.entrySet()) {
            fieldsPanel.add(new JLabel(entry.getKey()));
            JTextField textField = new JTextField(entry.getValue());
            fieldsPanel.add(textField);
            fields.put(entry.getValue(), textField);
            if (idFieldName == null) idFieldName = entry.getKey();
        }
        JScrollPane scrollPane = new JScrollPane(fieldsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> parameters = new HashMap<String, String>();
                for (Map.Entry<String, JTextField> stringJTextFieldEntry : fields.entrySet()) {
                    parameters.put(stringJTextFieldEntry.getKey(), stringJTextFieldEntry.getValue().getText());
                }
                parameters.put("table_name", tableName);
                try {
                    if (gui.executeUpdate(parameters, idFieldName, Integer.valueOf(fields.get(idFieldName).getText())))
                        JOptionPane.showMessageDialog(mainPanel, "Successful update", "Updating entity", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Get problem while updating\n" + e1.getMessage(), "Updating problem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
