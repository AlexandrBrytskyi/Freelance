package app.starter;

import app.sorter.BothComparator;
import app.sorter.StringByAlphabetComparator;
import app.sorter.StringByLengthComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by alexandr on 01.10.16.
 */
public class SortingApp {

    /*ридер для того, что бы читать ввод пользователя из консоли*/
    private static BufferedReader reader;

    /*здесь хранятся строки, введённые пользователем*/
    private String[] strings;

    /* это обьект, который умеет сравнивать строки, реализацию которого мы написали самостотельно*/
    private Comparator<String> comparator;

    private int menuOptionChosen = -1;

    public SortingApp() {
       /* инициализуем ридер, который будет считывать из консоли*/
        reader = new BufferedReader(new InputStreamReader(System.in));

        showGreeting();

        showMenu();

 /*       спрашиваем у пользователя сколько строк и инициализуем масив*/
        strings = new String[askForStringsSize()];

  /*      наполнем данными*/
        fillStrings();

     /*   сортируем и выводим*/
        showMessageAndSort();
    }

    private void showMenu() {
        System.out.println("Choose method to sort please:\n" +
                "1 - sort by alphabet\n" +
                "2 - sort by length\n" +
                "3 - mix sort (first priority is alphabet)");
        try {
            menuOptionChosen = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e1) {
            showInvalidMenuOption();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showInvalidMenuOption() {
        System.out.println("Wrong menu option!!!");
        showMenu();
    }

    private void showMessageAndSort() {
        System.out.println("You have entered array: ");
        System.out.println(makeArrayString());
        System.out.println("Now array is sorting");


        switch (menuOptionChosen) {
            case 1: {
                comparator = new StringByAlphabetComparator();
                break;
            }
            case 2: {
                comparator = new StringByLengthComparator();
                break;
            }
            case 3: {
                comparator = new BothComparator();
                break;
            }
        }



        /*один из классов утилит, для работы с массивами
        * в данном случае принимает компаратор, написанный нами и массив*/
        Arrays.sort(strings, comparator);
        System.out.println("Ok, sorted");
        System.out.println("Sorted array is:");
        System.out.println(makeArrayString());
    }

    /*   используем StringBuilder() как и просят в задании
           берем все елементы масива и клеим в одну строку с переносом*/
    private String makeArrayString() {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string).append("\n");
        }
        return builder.toString();
    }


    /*  наполняет масив строк*/
    private void fillStrings() {
        System.out.println("Enter now " + strings.length + " strings one by one");
        for (int i = 0; i < strings.length; i++) {
            try {
                strings[i] = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* спрашивает юзера ввести размер масива, если человек вводит не число, то программа просит ввести заново*/
    private int askForStringsSize() {
        System.out.println("Enter number of Strings you want to sort");
        try {
            return Integer.valueOf(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Oh, that is not number value, try again");
            return askForStringsSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void showGreeting() {
        System.out.println("Hello! This is Strings sorter app" + "\n" +
                "It will be sorted lexographically first and then by string length");
    }


}
