package jdbcapp.gui.tablePanels;


import jdbcapp.gui.GUI;
import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTablePanel extends JPanel implements ITablePanel {


    protected String TABLE_NAME;
    protected String[] fields;

    protected JButton addButton;
    protected JButton updateButton;
    protected JButton removeButton;
    protected JButton showEntitiesButton;
    protected JTextField maxResults;
    protected JTable resultTable;
    protected ResultSetDataTableModel tableModel;
    protected GUI gui;
    protected JLabel tableEntities;
    protected boolean isTableInited;

    public AbstractTablePanel(GUI gui) {
        super();
        this.gui = gui;
        initViewComponents();
    }

    private void initViewComponents() {
        setLayout(new BorderLayout(3, 3));

        JLabel label = new JLabel(gui.getDBinfo());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);

        tableEntities = new JLabel("here will be results");
        tableEntities.setHorizontalAlignment(SwingConstants.CENTER);
        tableEntities.setVerticalAlignment(SwingConstants.CENTER);
        add(tableEntities, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add");
        addButton.addActionListener(defineAddButtonListener());
        updateButton = new JButton("Update");
        updateButton.setEnabled(false);
        updateButton.addActionListener(defineUpdateButtonListener());
        removeButton = new JButton("Remove");
        removeButton.setEnabled(false);
        removeButton.addActionListener(defineRemoveButtonListener());
        showEntitiesButton = new JButton("Show Entities");
        showEntitiesButton.addActionListener(defineShowButtonListener());
        maxResults = new JTextField("Max Results");
        maxResults.setPreferredSize(new Dimension(100, 20));
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(showEntitiesButton);
        bottomPanel.add(maxResults);
    }

    @Override
    public void addEntity() {
        new AddEntityFrame(fields, TABLE_NAME, gui);
    }

    @Override
    public void removeEntity() {
        try {
            if (gui.executeRemove(TABLE_NAME, tableModel.getData().getTable().get(0).getName(),
                    Integer.valueOf(tableModel.getData().getTable().get(0).getValues().get(tableModel.getSelectedIndex()))))
                JOptionPane.showMessageDialog(AbstractTablePanel.this, "Removed successfull", "Removing", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(AbstractTablePanel.this, "Problem removing\n" + e1.getMessage(), "Removing", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateEntity() {
        new UpdateEntityFrame(tableModel.getSelectedValueNamesAndValues(), TABLE_NAME, gui);
    }

    @Override
    public void showTableEntities() {
        Integer maxRes;
        ResultSetData resultSetData;
        try {
            maxRes = Integer.valueOf(maxResults.getText());
        } catch (Exception e1) {
            maxRes = Integer.MAX_VALUE;
        }
        try {
            resultSetData = gui.executeShow(TABLE_NAME, maxRes);
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(AbstractTablePanel.this, "Some problem occurs\n" + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isTableInited) {
            tableModel.setData(resultSetData);
        } else {
            addTableWithResults(resultSetData);
        }
        resultTable.updateUI();
    }

    protected ActionListener defineAddButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntity();
            }
        };
    }


    protected ActionListener defineUpdateButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEntity();
            }
        };
    }


    protected ActionListener defineRemoveButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEntity();
            }
        };
    }


    protected ActionListener defineShowButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableEntities();
            }
        };
    }

    protected void addTableWithResults(ResultSetData data) {
        remove(tableEntities);
        tableModel = new ResultSetDataTableModel(data);
        resultTable = new JTable(tableModel);
        CustomRenderer renderer = new CustomRenderer();
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            TableColumn column = resultTable.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer);
        }
        sizeColumnsToFit(resultTable, 3);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(resultTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        updateUI();
    }

    public ResultSetDataTableModel getTableModel() {
        return tableModel;
    }


    protected class ResultSetDataTableModel extends AbstractTableModel {

        private ResultSetData data;
        private int selectedIndex;

        public ResultSetDataTableModel(ResultSetData data) {
            this.data = data;
        }

        private ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedIndex = resultTable.getSelectedRow();
                updateButton.setEnabled(true);
                removeButton.setEnabled(true);
            }
        };

        public int getRowCount() {
            return data.getTable().get(0).getValues().size();
        }

        public int getColumnCount() {
            return data.getTable().size();
        }

        @Override
        public String getColumnName(int column) {
            return data.getTable().get(column).getName();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            String s = data.getTable().get(columnIndex).getValues().get(rowIndex);
            return s == null ? "NULL" : checkNumber(s);
        }


        /*if it is number just format it to be printed in pattern 0.00
        * 2 digits after comma*/
        private Object checkNumber(String s) {
            try {
                return Integer.valueOf(s);
            } catch (Exception e) {
                try {
                    DecimalFormat format = new DecimalFormat("0.00");
                    return format.format(Double.valueOf(s));
                } catch (Exception e1) {
                    return s;
                }
            }
        }

        public void setData(ResultSetData data) {
            this.data = data;
        }

        public ResultSetData getData() {
            return data;
        }

        public Map<String, String> getSelectedValueNamesAndValues() {
            Map<String, String> res = new HashMap<>();
            for (NameValuesEntry nameValuesEntry : data.getTable()) {
                res.put(nameValuesEntry.getName(), nameValuesEntry.getValues().get(selectedIndex));
            }
            return res;
        }

        public int getSelectedIndex() {
            return selectedIndex;
        }
    }


    private class CustomRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 6703872492730589499L;
        private static final int NULL = -1;
        private static final int STRING = 0;
        private static final int NUMBER = 1;


        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value.equals("NULL")) {
                setNullRender(cellComponent);
            } else if (defineColumnType(column) == STRING) {
                cellComponent.setBackground(Color.YELLOW);
                ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
                /*yellow*/
            } else if (defineColumnType(column) == NUMBER) {
                cellComponent.setBackground(Color.GREEN);
                ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
                /*green*/
            } else {
                setNullRender(cellComponent);
                /*red*/
            }

            return cellComponent;
        }

        private void setNullRender(Component cellComponent) {
            cellComponent.setBackground(Color.RED);
            ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        }

        private int defineColumnType(int column) {
            return gui.defineColumnType(column);
        }
    }


    public static void sizeColumnsToFit(JTable table, int columnMargin) {
        JTableHeader tableHeader = table.getTableHeader();

        if (tableHeader == null) {
            // can't auto size a table without a header
            return;
        }

        FontMetrics headerFontMetrics = tableHeader.getFontMetrics(tableHeader.getFont());

        int[] minWidths = new int[table.getColumnCount()];
        int[] maxWidths = new int[table.getColumnCount()];

        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            int headerWidth = headerFontMetrics.stringWidth(table.getColumnName(columnIndex));

            minWidths[columnIndex] = headerWidth + columnMargin;

            int maxWidth = getMaximalRequiredColumnWidth(table, columnIndex, headerWidth);

            maxWidths[columnIndex] = Math.max(maxWidth, minWidths[columnIndex]) + columnMargin;
        }

        adjustMaximumWidths(table, minWidths, maxWidths);

        for (int i = 0; i < minWidths.length; i++) {
            if (minWidths[i] > 0) {
                table.getColumnModel().getColumn(i).setMinWidth(minWidths[i]);
            }

            if (maxWidths[i] > 0) {
                table.getColumnModel().getColumn(i).setMaxWidth(maxWidths[i]);

                table.getColumnModel().getColumn(i).setWidth(maxWidths[i]);
            }
        }
    }

    private static void adjustMaximumWidths(JTable table, int[] minWidths, int[] maxWidths) {
        if (table.getWidth() > 0) {
            // to prevent infinite loops in exceptional situations
            int breaker = 0;

            // keep stealing one pixel of the maximum width of the highest column until we can fit in the width of the table
            while (sum(maxWidths) > table.getWidth() && breaker < 10000) {
                int highestWidthIndex = findLargestIndex(maxWidths);

                maxWidths[highestWidthIndex] -= 1;

                maxWidths[highestWidthIndex] = Math.max(maxWidths[highestWidthIndex], minWidths[highestWidthIndex]);

                breaker++;
            }
        }
    }

    private static int getMaximalRequiredColumnWidth(JTable table, int columnIndex, int headerWidth) {
        int maxWidth = headerWidth;

        TableColumn column = table.getColumnModel().getColumn(columnIndex);

        TableCellRenderer cellRenderer = column.getCellRenderer();

        if (cellRenderer == null) {
            cellRenderer = new DefaultTableCellRenderer();
        }

        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            Component rendererComponent = cellRenderer.getTableCellRendererComponent(table,
                    table.getModel().getValueAt(row, columnIndex),
                    false,
                    false,
                    row,
                    columnIndex);

            double valueWidth = rendererComponent.getPreferredSize().getWidth();

            maxWidth = (int) Math.max(maxWidth, valueWidth);
        }

        return maxWidth;
    }

    private static int findLargestIndex(int[] widths) {
        int largestIndex = 0;
        int largestValue = 0;

        for (int i = 0; i < widths.length; i++) {
            if (widths[i] > largestValue) {
                largestIndex = i;
                largestValue = widths[i];
            }
        }

        return largestIndex;
    }

    private static int sum(int[] widths) {
        int sum = 0;

        for (int width : widths) {
            sum += width;
        }

        return sum;
    }
}
