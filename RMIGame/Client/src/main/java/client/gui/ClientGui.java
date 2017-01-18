package client.gui;

import common.model.Response;
import common.model.exceptions.NoSuchUserException;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;
import common.service.IService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

@Component
public class ClientGui extends JFrame {

    private JPanel mainPanel;
    private java.util.List<JTextField> numbers;
    private JButton check;
    private JButton register;
    private String userName;
    private String accessToken = "null";


    private IService service;

    public ClientGui() throws HeadlessException {
        super("Client LOTO game");
        setSize(new Dimension(400, 150));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userName = JOptionPane.showInputDialog(this, "Enter your name, please", "Player name", JOptionPane.INFORMATION_MESSAGE);
        connectToRemoteService();
        setResizable(false);
        initComponents();
        add(mainPanel);
        setVisible(true);
    }

    private void connectToRemoteService() {
        try {
            service = createStub();
            JOptionPane.showMessageDialog(ClientGui.this, "Connected successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ClientGui.this, "Sorry we have problem\n" + e.getMessage(), "Try again", JOptionPane.ERROR_MESSAGE);
            connectToRemoteService();
        }
    }


    private IService createStub() {
        String host = JOptionPane.showInputDialog(
                ClientGui.this, "Ok, " + userName + " now we have connect to client, enter host",
                "Server host",
                JOptionPane.INFORMATION_MESSAGE);
        String port = JOptionPane.showInputDialog(
                ClientGui.this, "Ok, " + userName + " now enter port",
                "Server host",
                JOptionPane.INFORMATION_MESSAGE);
        RmiProxyFactoryBean stub = new RmiProxyFactoryBean();
        stub.setServiceUrl("rmi://" + host + ":" + port + "/RMI_SERVICE");
        stub.setServiceInterface(IService.class);
        stub.afterPropertiesSet();
        return (IService) stub.getObject();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 1));
        JPanel labels = new JPanel(new GridLayout(1, 6));
        JPanel textFields = new JPanel(new GridLayout(1, 6));
        numbers = new ArrayList<JTextField>();
        for (int i = 0; i < 6; i++) {
            JLabel label = new JLabel("Num " + (i + 1));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            labels.add(label);
            JTextField textField = new JTextField();
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            numbers.add(textField);
            textFields.add(textField);
        }
        fieldsPanel.add(labels);
        fieldsPanel.add(textFields);
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accessToken = service.registerToGame(userName);
                JOptionPane.showMessageDialog(ClientGui.this, "You have just registered, your token: \n" + accessToken);
                register.setEnabled(false);
            }
        });
        buttonsPanel.add(register);
        check = new JButton("Check");
        buttonsPanel.add(check);
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkNumbers()) {
                    Integer[] nums = new Integer[6];
                    for (int i = 0; i < nums.length; i++) {
                        nums[i] = Integer.valueOf(numbers.get(i).getText());
                    }
                    try {
                        Response response = service.isIWinner(accessToken, nums);
                        if (response.isWinner()) {
                            JOptionPane.showMessageDialog(ClientGui.this, response.getMessage() + "\nmatched numbers are " + Arrays.toString(response.getWinNumbers()),
                                    "Winner", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(ClientGui.this, response.getMessage(),
                                    "Loser", JOptionPane.INFORMATION_MESSAGE);
                        }
                        System.exit(0);
                    } catch (NoSuchUserException e1) {
                        JOptionPane.showMessageDialog(ClientGui.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private boolean checkNumbers() {
        for (int i = 0; i < numbers.size(); i++) {
            try {
                Integer num = Integer.valueOf(numbers.get(i).getText());
                if (num < 0 || num > 50) {
                    JOptionPane.showMessageDialog(ClientGui.this, "Wrong #" + (i + 1), "Must be in (0,50)", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ClientGui.this, "Not a number #" + (i + 1), "Wrong number", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

}
