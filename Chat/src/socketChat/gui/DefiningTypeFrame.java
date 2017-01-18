package socketChat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DefiningTypeFrame extends JFrame {

    private JPanel mainPanel;
    private JRadioButton serverRadio;
    private JRadioButton clientRadio;
    private JTextField host;
    private JTextField port;
    private JButton submit;

    public DefiningTypeFrame() throws HeadlessException {
        super("Who I am?");
        setSize(new Dimension(200, 200));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        add(mainPanel);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridLayout(0, 1));

        JPanel radioButtonsPanel = new JPanel(new FlowLayout());
        serverRadio = new JRadioButton("Create server");
        clientRadio = new JRadioButton("Connect to server");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(serverRadio);
        buttonGroup.add(clientRadio);
        serverRadio.setSelected(true);
        radioButtonsPanel.add(serverRadio);
        radioButtonsPanel.add(clientRadio);
        mainPanel.add(radioButtonsPanel);

        serverRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit.setText("Create");
                host.setText("localhost");
                host.setEditable(false);
            }
        });

        clientRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit.setText("Connect");
                host.setEditable(true);
            }
        });


        JPanel labelsPanel = new JPanel(new GridLayout(1, 2));
        JPanel fieldsPanel = new JPanel(new GridLayout(1, 2));

        host = new JTextField("localhost");
        host.setEditable(false);
        host.setHorizontalAlignment(SwingConstants.CENTER);
        fieldsPanel.add(host);
        JLabel hostLabel = new JLabel("Host");
        hostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hostLabel.setLabelFor(host);
        labelsPanel.add(hostLabel);

        port = new JTextField("9999");
        port.setHorizontalAlignment(SwingConstants.CENTER);
        fieldsPanel.add(port);
        JLabel portLabel = new JLabel("Port");
        portLabel.setLabelFor(port);
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelsPanel.add(portLabel);

        mainPanel.add(labelsPanel);
        mainPanel.add(fieldsPanel);

        submit = new JButton("Create");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String portStr = port.getText();
                int port;
                try {
                    port = Integer.parseInt(portStr);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DefiningTypeFrame.this,
                            "Check port please, not a number or null",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (serverRadio.isSelected()) {
                    new ServerGUI(port);
                } else {
                    new ClientGUI(host.getText(), port);
                }
                DefiningTypeFrame.this.dispose();
            }
        });

        mainPanel.add(submit);

    }
}
