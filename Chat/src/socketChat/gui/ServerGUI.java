package socketChat.gui;



import socketChat.controller.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame {

    private Server server;
    private JPanel mainPanel;
    private JTextArea output;
    private JButton startButton;
    private JFileChooser fileChooser;
    private JTextField filePathField;
    private int port;

    public ServerGUI(int port) throws HeadlessException {
        super("Server app");
        this.port = port;
        setSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initView();
        add(mainPanel);
        setVisible(true);
    }

    private void initView() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel fileChooserPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Select directory to save log");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        fileChooserPanel.add(label);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooserPanel.add(fileChooser);
        filePathField = new JTextField("Select directory to save logs");
        final JButton selectDirButt = new JButton("Choose");
        selectDirButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(mainPanel);
                String path = fileChooser.getSelectedFile().getPath();
                filePathField.setText(path);
                selectDirButt.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(filePathField, BorderLayout.CENTER);
        topPanel.add(selectDirButt, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        output = new JTextArea("Welcome to chat, select directory to save log and start, please\n");
        output.setEditable(false);
        JScrollPane pane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(pane, BorderLayout.CENTER);
        startButton = new JButton("Start server");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server = new Server(filePathField.getText(), port, ServerGUI.this);
                    JOptionPane.showMessageDialog(mainPanel,
                            "Server created",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    appendToOutput("Server created on localhost:" + port);
                    appendToOutput("See logs on " + server.getLogAppender().getAbsolutePath());
                    startButton.setEnabled(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel,
                            "Problem while creating server",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    new DefiningTypeFrame();
                    ServerGUI.this.dispose();
                }
            }
        });
        startButton.setEnabled(false);
        mainPanel.add(startButton, BorderLayout.SOUTH);
    }


    public void appendToOutput(String message) {
        output.append(message + "\n");
    }
}
