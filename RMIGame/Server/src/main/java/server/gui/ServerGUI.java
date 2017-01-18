package server.gui;

import common.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

@Component
public class ServerGUI extends JFrame {

    private JPanel mainPanel;
    private JTextArea output;

    @Autowired(required = true)
    private IService service;

    public ServerGUI() throws HeadlessException {
        super("Server LOTO game");
        setSize(new Dimension(600, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        add(mainPanel);
        setVisible(true);
    }

    public void initRMIService() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("RMI_SERVICE");
        exporter.setService(service);
        exporter.setServiceInterface(IService.class);
        exporter.setRegistryHost("localhost");
        exporter.setAlwaysCreateRegistry(true);
        try {
            exporter.setRegistry(LocateRegistry.createRegistry(9999));
            printMessage("Registry created :\n" + LocateRegistry.getRegistry().toString());
            exporter.afterPropertiesSet();
        } catch (RemoteException e) {
            e.printStackTrace();
            printMessage(e.getMessage());
            printMessage("try again");
        }
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        output = new JTextArea();
        printMessage("Hello, loto game client is initializing");
        output.setEditable(false);
        JScrollPane pane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(pane, BorderLayout.CENTER);
    }

    public void printMessage(String message) {
        output.append(message+"\n");
    }
}
