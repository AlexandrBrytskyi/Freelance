package jdbcapp.gui.tablePanels;


import jdbcapp.gui.GUI;
import jdbcapp.model.ResultSetData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ConcretteTablePanel extends AbstractTablePanel {


    public ConcretteTablePanel(GUI gui, String tableName) {
        super(gui);
        TABLE_NAME = tableName;
        fields = gui.getTableFields(TABLE_NAME);
    }

}
