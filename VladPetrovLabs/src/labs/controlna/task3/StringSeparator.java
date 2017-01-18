package labs.controlna.task3;


import java.util.Scanner;


/*Класс что содержит логику построения строки из подмножесства символов*/
public class StringSeparator {

    /*для чтения из консоли*/
    private Scanner scanner;

    /*в конструкторе инициализируем сканнер и запускаем логику*/
    public StringSeparator() {
        scanner = new Scanner(System.in);
        String input = getUserInputString();
        System.out.println("Введена строка " + input);
        substring(input);
    }


    /*метод для построения из подмножества массива символов новой строки
    * просим ввести интервал который будем брать из массива
    * и выводим результат*/
    private void substring(String input) {
        int startPosition = getStartIndex(input);
        int endPosition = getEndIndex(input, startPosition);
        System.out.println("Выбираем из множества символов из " + startPosition + " по " + endPosition + " символы");
        System.out.println("Результат " + input.substring(startPosition, endPosition));
    }


    /*просим ввести начальный индекс, если он неверный просим ввести ещё раз*/
    private int getStartIndex(String input) {
        int maxStartPosition = input.length() - 2;
        System.out.println("Введите начальный индекс символа (min=0, max =" + maxStartPosition + ")");
        int startPosition = scanner.nextInt();
        if (startPosition > maxStartPosition || startPosition < 0) {
            System.out.println("Неправильно введен индекс!!!");
            return getStartIndex(input);
        }
        return startPosition;
    }

    /*просим ввести конечный индекс, если он неверный просим ввести ещё раз*/
    private int getEndIndex(String input, int startIndex) {
        int maxEndPosition = input.length();
        int minEndPosition = startIndex + 1;
        System.out.println("Введите начальный индекс символа (min=" + minEndPosition + "," + "max =" + maxEndPosition + ")");
        int endPosition = scanner.nextInt();
        if (endPosition > maxEndPosition || endPosition < minEndPosition) {
            System.out.println("Неправильно введен индекс!!!");
            return getEndIndex(input, startIndex);
        }
        return endPosition;
    }

    /*просим ввести строку, (множество символов) которую будем резать*/
    private String getUserInputString() {
        System.out.println("Введите строку которую будем делить");
        return scanner.nextLine();
    }
}
