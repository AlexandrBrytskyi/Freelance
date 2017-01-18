package app.controller;


import app.sorters.*;
import app.ui.UI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Controller {


    public static final int ARRAY_SIZE = 10000;
    private Sorter bubbleSorter;
    private Sorter insertionSorter;
    private Sorter mergeSorter;
    private UI ui;
    private List<Double> generated;
    private String fileName;

    public Controller() {
        bubbleSorter = new BubbleSorter();
        insertionSorter = new InsertionSorter();
        mergeSorter = new MergeSorter();
        ui = new UI(this);
    }

    /*param isInteger = is int or double numbers
    returns generated file path*/
    public String generateRandomNums(double minNum, double maxNum, String directoryPath) throws IOException {
        generated = new ArrayList<>();
        double range = maxNum - minNum;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            generated.add(Math.random() * range + minNum);
        }
        return writeInFile(directoryPath + "/" + new Date().toString() + "Doubles", generated, "Generated numbers", true);
    }

    private String writeInFile(String path, List numbers, String header, boolean savePath) throws IOException {
        File file = new File(path);
        if (file.exists()) file.delete();
        file.createNewFile();
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write(header + "\n");
            int counter = 1;
            for (Object number : numbers) {
                pw.write("#" + counter++ + ", value = " + number + "\n");
            }
        }
        String absolutePath = file.getAbsolutePath();
        if (savePath) {
            fileName = absolutePath;
        }
        return absolutePath;
    }

    public String bubbleSort() throws IOException {
        long start = System.currentTimeMillis();
        bubbleSorter.sort(generated, Utils.getDoubleComparator());
        long length = System.currentTimeMillis() - start;
        return writeInFile(fileName + "BUBBLESORTED", generated, "Time of bubble sorting is " + length + " milliseconds", false);
    }

    public String insertionSort() throws IOException {
        long start = System.currentTimeMillis();
        insertionSorter.sort(generated, Utils.getDoubleComparator());
        long length = System.currentTimeMillis() - start;
        return writeInFile(fileName + "INSERTIONSORTED", generated, "Time of insertion sorting is " + length + " milliseconds", false);
    }

    public String mergeSort() throws IOException {
        long start = System.currentTimeMillis();
        mergeSorter.sort(generated, Utils.getDoubleComparator());
        long length = System.currentTimeMillis() - start;
        return writeInFile(fileName + "MERGESORTED", generated, "Time of merge sorting is " + length + " milliseconds", false);
    }

}
