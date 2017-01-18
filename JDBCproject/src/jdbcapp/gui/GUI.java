package jdbcapp.gui;


import jdbcapp.Controller;
import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import java.util.List;

public class GUI {
    private QuerriesFrame mainFrame;
    private ConnectDBFrame connectDBFrame;
    private Controller controller;


    public GUI(Controller controller) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.controller = controller;
        connectDBFrame = new ConnectDBFrame(this);
        mainFrame = new QuerriesFrame(this);
    }


    public void executeStatement(String query, int maxResults) throws Exception {
        Object result = controller.execute(query, maxResults);
        if (result instanceof Boolean) {
            boolean res = (boolean) result;
            mainFrame.showExecuteUpdateResult(res);
        } else {
            ResultSetData res = (ResultSetData) result;
            mainFrame.showExucuteQuerryResult(res);
        }
    }


    public boolean reconnect(String url, String user, String pass) throws Exception {
        boolean res = controller.reconnect(url, user, pass);
        if (res == true) {
            setMainFrameFieldsEditable(true);
            mainFrame.setDBinfoLabelText(url);
            connectDBFrame.dispose();
            JOptionPane.showMessageDialog(mainFrame.getContentPane(), "Connected to " + url, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        return res;
    }

    private void setMainFrameFieldsEditable(boolean b) {
        mainFrame.setEditableFields(true);
    }

    public void reconnectRequested() {
        mainFrame.setEditableFields(false);
        connectDBFrame.pack();
        connectDBFrame.show();
    }

    public String countFunction(String operationName, String column) throws Exception {
        return controller.countFunction(operationName, column);
    }

    public void additionalButtonPressed() {
        List<NameValuesEntry> table = controller.getLastreturned().getTable();
        String[] names = new String[table.size()];
        for (int i = 0; i < table.size(); i++) {
            names[i] = table.get(i).getName();
        }
        new AdditionalOperationsFrame(names, this);
    }

    public int defineColumnType(int column) {
        return controller.defineColumnType(column);
    }
}
