package socketChat.gui;



import socketChat.controller.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientGUI extends JFrame {

    private Client client;
    private JPanel mainPanel;
    private JTextArea output;
    private JTextArea input;
    private JButton send;
    private String host;
    private String name;
    private int port;

    public ClientGUI(String host, int port) throws HeadlessException {
        super("Client app");
        this.port = port;
        this.host = host;
        setSize(new Dimension(600, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        name = JOptionPane.showInputDialog("Enter your name, please");
        if (createClient(host, port, name)) {
            initView();
            add(mainPanel);
            setVisible(true);
        }
    }

    private boolean createClient(String host, int port, String name) {
        try {
            client = new Client(host, port, name, this);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error creating client\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            new DefiningTypeFrame();
            this.dispose();
            return false;
        }
        return true;
    }

    private void initView() {
        mainPanel = new JPanel(new BorderLayout());
        output = new JTextArea("Welcome to chat, " + name +"\n");
        JScrollPane pane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(pane, BorderLayout.CENTER);
        output.setEditable(false);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        input = new JTextArea("Your message here, " + name);
        JScrollPane panein = new JScrollPane(input, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mess = input.getText();
                if (!mess.isEmpty()) {
                    client.sendMessage(mess);
                    appendToOutput("I have sent message:" + mess);
                    input.setText("");
                }
            }
        });
        bottomPanel.add(panein, BorderLayout.CENTER);
        bottomPanel.add(send, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }


    public void appendToOutput(String message) {
       if (message!=null&&output!=null) output.append(message + "\n");
    }

}
