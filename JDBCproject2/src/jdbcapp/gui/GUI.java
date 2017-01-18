package jdbcapp.gui;


import jdbcapp.Controller;
import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.util.List;
import java.util.Map;

public class GUI {

    private Controller controller;
    private ConnectDBFrame connectDBFrame;
    private String dbInfo;
    private MainMenuFrame mainMenuFrame;


    public GUI(Controller controller) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.controller = controller;
        connectDBFrame = new ConnectDBFrame(this);
    }


    public ResultSetData executeShow(String table, int maxResults) throws Exception {
        return controller.executeShow(table, maxResults);
    }


    public int defineColumnType(int column) {
        return controller.defineColumnType(column);
    }

    public String getDBinfo() {
        return dbInfo;
    }

    public boolean executeAdd(Map<String, String> parameters) throws Exception {
        return controller.addEntity(parameters);
    }

    public boolean executeUpdate(Map<String, String> parameters, String idFieldName, Integer id) throws Exception {
        return controller.updateEntity(parameters, idFieldName, id);
    }

    public boolean executeRemove(String table, String idFieldName, Integer id) throws Exception {
        return controller.removeEntity(table, idFieldName, id);
    }

    public String[] getTableFields(String tableName) {
        return controller.getTableFields(tableName);
    }

    public boolean reconnect(String url, String user, String pass) throws Exception {
        boolean connected = controller.reconnect(url, user, pass);
        if (connected) {
            dbInfo = "Database on url: \n" + url;
            connectDBFrame.dispose();
            mainMenuFrame = new MainMenuFrame(this);
            JOptionPane.showMessageDialog(mainMenuFrame, "Connected to " + url, "Connection details", JOptionPane.INFORMATION_MESSAGE);
        }
        return connected;
    }
}
