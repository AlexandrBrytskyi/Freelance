package jdbcapp.gui;


import jdbcapp.model.ResultSetData;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class QuerriesFrame extends JFrame {

    private JPanel mainPanel;
    private JLabel dbURLLabel;
    private JTextArea queriesArea;
    private JScrollPane textAreaScroll;
    private JPanel queriiesAndResultPanel;
    private JPanel additionalPanel;
    private JButton executeButton;
    private JButton additionalButton;
    private JButton reconnectButton;
    private JTextField maxValues;
    private GUI gui;
    private JScrollPane scrollPane;
    private JTable resultTable;
    private ResultSetDataTableModel tableModel;

    public QuerriesFrame(GUI gui) {
        super("JDBC Application");
        this.gui = gui;
        setSize(new Dimension(800, 600));
        setResizable(false);
        initMainPanel();
        setEditableFields(false);
        add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initMainPanel() {
        mainPanel = new JPanel(new BorderLayout(3, 3));
        dbURLLabel = new JLabel("We are not connected yet", SwingConstants.CENTER);
        mainPanel.add(dbURLLabel, BorderLayout.NORTH);

        additionalPanel = new JPanel(new FlowLayout());
        executeButton = new JButton("Execute");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String qurry = queriesArea.getText();
                if (qurry != null && !qurry.isEmpty()) {
                    try {
                        gui.executeStatement(qurry, defineMaxResultsNum());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(mainPanel, e1.getMessage(), "Problem", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                } else
                    JOptionPane.showMessageDialog(mainPanel, "No statement!!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }

            private int defineMaxResultsNum() {
                try {
                    return Integer.valueOf(maxValues.getText());
                } catch (Exception e) {
                    return -1;
                }
            }
        });
        additionalButton = new JButton("Additional Functions");
        additionalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.additionalButtonPressed();
            }
        });
        maxValues = new JTextField("Max Values");
        maxValues.setPreferredSize(new Dimension(100, 30));
        maxValues.setToolTipText("Maxial amount of querry results");
        reconnectButton = new JButton("Reconnect");
        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.reconnectRequested();
            }
        });
        additionalPanel.add(executeButton);
        additionalPanel.add(additionalButton);
        additionalPanel.add(maxValues);
        additionalPanel.add(reconnectButton);
        mainPanel.add(additionalPanel, BorderLayout.SOUTH);

        queriiesAndResultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        queriesArea = new JTextArea("Write your querry here ");
        textAreaScroll = new JScrollPane(queriesArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textAreaScroll.setPreferredSize(new Dimension(getWidth() - 50, (getHeight() - (dbURLLabel.getHeight() + additionalPanel.getHeight() + 20)) / 3));
        queriiesAndResultPanel.add(textAreaScroll);


        mainPanel.add(queriiesAndResultPanel, BorderLayout.CENTER);


    }

    public void showExecuteUpdateResult(boolean res) {
        JOptionPane.showMessageDialog(mainPanel,
                res ? "Update successfull" : "Some problem occurs :(", "Result", JOptionPane.INFORMATION_MESSAGE);
        clearTable();
    }

    private void clearTable() {
        try {
            queriiesAndResultPanel.remove(1);
            queriiesAndResultPanel.updateUI();
            resultTable = null;
        } catch (Exception e) {
            return;
        }
    }

    public void showExucuteQuerryResult(ResultSetData res) {
        JOptionPane.showMessageDialog(mainPanel, "Querry executed", "Result", JOptionPane.INFORMATION_MESSAGE);
        clearTable();
        makeNewTable(res);
    }

    private void makeNewTable(ResultSetData data) {
        tableModel = new ResultSetDataTableModel(data);
        resultTable = new JTable(tableModel);
        CustomRenderer renderer = new CustomRenderer();
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            TableColumn column = resultTable.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer);
        }
        sizeColumnsToFit(resultTable, 3);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(resultTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(textAreaScroll.getSize().width, (int) (textAreaScroll.getSize().height * 1.6)));
        queriiesAndResultPanel.add(scrollPane);
        queriiesAndResultPanel.updateUI();
    }

    public void setEditableFields(boolean editable) {
        queriesArea.setEditable(editable);
        executeButton.setEnabled(editable);
        maxValues.setEditable(editable);
        additionalButton.setEnabled(editable);
    }

    public void setDBinfoLabelText(String text) {
        dbURLLabel.setText("Now connected to " + text);
    }

    private class ResultSetDataTableModel extends AbstractTableModel {

        private ResultSetData data;

        public ResultSetDataTableModel(ResultSetData data) {
            this.data = data;
        }


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
