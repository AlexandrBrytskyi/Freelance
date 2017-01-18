package app.ui;


import app.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UI extends JFrame {

    private JPanel mainPanel;
    private JButton generate;
    private JFileChooser fileChooser;
    private JTextField directoryPathField;
    private Controller controller;
    private JButton selectDirButt;
    private JTextField minimum;
    private JTextField maximum;
    private JButton bubbleSort;
    private JButton insertionSort;
    private JButton mergeSort;

    public UI(Controller controller) throws HeadlessException {
        super("Sorting app");
        this.controller = controller;
        setSize(new Dimension(400, 200));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initView();
        add(mainPanel);
        setVisible(true);
    }

    private void initView() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel fileChooserPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Select directory to generate and save array");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        fileChooserPanel.add(label);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooserPanel.add(fileChooser);
        directoryPathField = new JTextField("Select directory to generate and save array");
        selectDirButt = new JButton("Choose");
        selectDirButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(mainPanel);
                String path = fileChooser.getSelectedFile().getPath();
                directoryPathField.setText(path);
                selectDirButt.setEnabled(false);
                generate.setEnabled(true);
                setSortButtonsEnabled(false);
            }
        });
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(directoryPathField, BorderLayout.CENTER);
        topPanel.add(selectDirButt, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        generate = new JButton("Generate");
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (defineMinMax()) {
                        String path = controller.generateRandomNums(Double.valueOf(minimum.getText()), Double.valueOf(maximum.getText()), directoryPathField.getText());
                        JOptionPane.showMessageDialog(mainPanel,
                                "Generated nums is saved in " + path,
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        setSortButtonsEnabled(true);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel,
                            "Problem while generating numbers and writing in file",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean defineMinMax() {
                try {
                    double min = Double.valueOf(minimum.getText());
                    double max = Double.valueOf(maximum.getText());
                    if (max < min) return showErrorMessageAndReturn();
                } catch (NumberFormatException e) {
                    return showErrorMessageAndReturn();
                }
                return true;
            }

            private boolean showErrorMessageAndReturn() {
                JOptionPane.showMessageDialog(mainPanel,
                        "Enter correct minimun and maximum numbers",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        });
        generate.setEnabled(false);
        centerPanel.add(generate);
        JPanel minMaxPanel = new JPanel(new GridLayout(2, 1));
        minimum = new JTextField();
        maximum = new JTextField();
        JLabel minLabel = new JLabel("Minimum");
        minLabel.setLabelFor(minimum);
        JLabel maxLabel = new JLabel("Maximum");
        minLabel.setLabelFor(maximum);
        JPanel labelsPanel = new JPanel(new GridLayout(1, 2));
        labelsPanel.add(minLabel);
        labelsPanel.add(maxLabel);
        JPanel fieldsPanel = new JPanel(new GridLayout(1, 2));
        fieldsPanel.add(minimum);
        fieldsPanel.add(maximum);
        minMaxPanel.add(labelsPanel);
        minMaxPanel.add(fieldsPanel);

        centerPanel.add(minMaxPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel sortButtonsPanel = new JPanel(new GridLayout(1, 3));
        bubbleSort = new JButton("BubbleSort");
        bubbleSort.addActionListener(sortButtonsListener);
        insertionSort = new JButton("InsertionSort");
        insertionSort.addActionListener(sortButtonsListener);
        mergeSort = new JButton("MergeSort");
        mergeSort.addActionListener(sortButtonsListener);
        setSortButtonsEnabled(false);
        sortButtonsPanel.add(bubbleSort);
        sortButtonsPanel.add(insertionSort);
        sortButtonsPanel.add(mergeSort);

        mainPanel.add(sortButtonsPanel, BorderLayout.SOUTH);
    }

    private void setSortButtonsEnabled(boolean b) {
        bubbleSort.setEnabled(b);
        insertionSort.setEnabled(b);
        mergeSort.setEnabled(b);
    }


    private ActionListener sortButtonsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String sorter = defineSorterName(e);
                String path = sorter.equals("Bubble") ? controller.bubbleSort() :
                        sorter.equals("Insertion") ? controller.insertionSort() : controller.mergeSort();
                JOptionPane.showMessageDialog(mainPanel,
                        sorter + " sort worked\n nums is saved in " + path,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Problem while sorting numbers and writing in file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private String defineSorterName(ActionEvent e) {
            return (e.getSource().equals(bubbleSort) ? "Bubble" : e.getSource().equals(insertionSort) ? "Insertion" : "Merge");
        }
    };

}
