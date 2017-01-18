package jdbcapp.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ConnectDBFrame extends JFrame {

    private GUI gui;
    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGHT = 300;
    private JPanel mainPanel;
    private JTextField hostField;
    private JTextField portField;
    private JTextField userField;
    private JTextField passField;
    private JTextField dbNameField;
    private JButton connectButton;
    private String url = null;

    public ConnectDBFrame(GUI gui) {
        super("MySQL Database connection");
        this.gui = gui;
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private void initMainPanel() {
        int rows = 6;
        int rowHeigth = FRAME_HEIGHT / rows;
        mainPanel = new JPanel(new GridLayout(rows, 1));

        hostField = addRow("Host", "localhost", rowHeigth);

        portField = addRow("Port", "3306", rowHeigth);

        userField = addRow("User", "username", rowHeigth);

        passField = addRow("Password", "pass", rowHeigth);
        dbNameField = addRow("DB name", "somedb", rowHeigth);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                url = "jdbc:mysql://" + hostField.getText() + ":" + portField.getText() + "/" + dbNameField.getText();
                if (JOptionPane.showConfirmDialog(mainPanel, "Really connect to\n" + url, "Connection confirm",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    try {
                        gui.reconnect(url, userField.getText(), passField.getText());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(mainPanel, e1.getMessage(), "Some problem", JOptionPane.WARNING_MESSAGE);
                    }
            }
        });

        mainPanel.add(connectButton);
    }

    private JTextField addRow(String label, String field, int rowHeight) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        JLabel labelRef = new JLabel(label, SwingConstants.LEFT);
        labelRef.setPreferredSize(new Dimension(FRAME_WIDTH / 3, rowHeight));
        JTextField textFieldref = new JTextField(field);
        textFieldref.setPreferredSize(new Dimension(FRAME_WIDTH - FRAME_WIDTH / 3 - 10, rowHeight));
        rowPanel.add(labelRef);
        rowPanel.add(textFieldref);
        mainPanel.add(rowPanel);
        return textFieldref;
    }

    public String getUrl() {
        return url;
    }
}
