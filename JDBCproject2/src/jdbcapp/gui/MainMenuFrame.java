package jdbcapp.gui;

import jdbcapp.gui.tablePanels.ConcretteTablePanel;

import javax.swing.*;
import java.awt.*;


public class MainMenuFrame extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tablesPanelsPane;
    private GUI gui;


    public MainMenuFrame(GUI gui) throws HeadlessException {
        super("My Sql Server database editor");
        this.gui = gui;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setResizable(false);
        initViewComponents();
        add(mainPanel);
        setVisible(true);
    }

    private void initViewComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tablesPanelsPane = new JTabbedPane(JTabbedPane.TOP);
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "client"));
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "agents"));
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "department"));
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "invoice"));
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "insurance_policy"));
        tablesPanelsPane.add(new ConcretteTablePanel(gui, "payment"));
        mainPanel.add(tablesPanelsPane);
    }
}
