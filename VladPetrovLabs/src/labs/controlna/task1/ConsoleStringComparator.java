package labs.controlna.task1;


import java.util.Arrays;
import java.util.Scanner;

/*Класс который содержит логику сравнения строк введенных из консоли*/
public class ConsoleStringComparator {

    /*Сканнер - обьект, который умеет считывать данные (в нашем случае из коноли)*/
    private Scanner scanner;

    /*Массив, в который будем записывать строки*/
    private String[] strings;

    public ConsoleStringComparator() {
        /*инициализируем сканнер на консоль*/
        scanner = new Scanner(System.in);
        /*просим у юзера ввести количество строк для сравнения askForStringsSize()
        * и инициализируем массив*/
        strings = new String[askForStringsSize()];

        askStrings();

        printStrings();

        showEqualStrings();
    }

    /*сравниваем строки, которые есть в массиве, пробегаем по всем элементам, если находим совпадение, то выводим на екран*/
    private void showEqualStrings() {
        if (strings.length < 2) return;
        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = i+1; j < strings.length; j++) {
                if (strings[i].equals(strings[j]))
                    System.out.println("String with #" + i + ":" + strings[i] + " = String with #" + j + ":" + strings[j]);
            }
        }
    }

    /*метод, который выводит на екран массив строк*/
    private void printStrings() {
        System.out.println("You have entered that strings:");
        System.out.println(Arrays.toString(strings));
    }

    /*поочередно записываем в массив строки, которые вводит пользователь*/
    private void askStrings() {
        for (int i = 0; i < strings.length; i++) {
            System.out.println("Enter " + i + " string");
            strings[i] = scanner.next();
        }
    }

    /*метод для ввода юзером размера массива строк*/
    private int askForStringsSize() {
        System.out.println("Enter amount of String you want to compare");
        return scanner.nextInt();
    }


}
