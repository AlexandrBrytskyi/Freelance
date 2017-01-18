package jdbcapp.gui.tablePanels;


import jdbcapp.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddEntityFrame extends JFrame {


    private JPanel mainPanel;
    private JButton submit;
    private JButton cancel;
    private Map<String, JTextField> fields;
    private String[] fieldNames;
    private GUI gui;
    private String tableName;

    public AddEntityFrame(String[] fieldNames, String tableName, GUI gui) throws HeadlessException {
        super("Add entity");
        this.fieldNames = fieldNames;
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
        for (String fieldName : fieldNames) {
            fieldsPanel.add(new JLabel(fieldName));
            JTextField textField = new JTextField();
            fieldsPanel.add(textField);
            fields.put(fieldName, textField);
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
                    if (gui.executeAdd(parameters))
                        JOptionPane.showMessageDialog(mainPanel, "Successful add", "Adding entity", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Get problem while adding\n" + e1.getMessage(), "Adding problem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
