package com.cherno.rain;


import com.cherno.rain.client_server.Client;
import com.cherno.rain.client_server.MessageSendable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatFrame extends JFrame {

    private JPanel mainPanel;
    private JTextArea output;
    private JTextArea input;
    private JButton send;
    private MessageSendable messageSendable;
    private String name;

    public ChatFrame(String name, MessageSendable client) throws HeadlessException {
        super(name + " chat");
        this.name = name;
        this.messageSendable = client;
        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initView();
        add(mainPanel);
        setVisible(true);
    }


    private void initView() {
        mainPanel = new JPanel(new BorderLayout());
        output = new JTextArea("Welcome to chat, " + name + "\n");
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
                    messageSendable.sendMessage(name + ": " + mess);
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
        if (message != null && output != null) output.append(message + "\n");
    }

}
