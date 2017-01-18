package labs.controlna.task2;


import java.util.Scanner;

/*Класс который умеет собирать грибы*/
public class GribTaker {

    /*для чтения из консоли*/
    private Scanner scanner;
    /*сумма грибов, используем для мониторинга количества грибов после каждой итерации добавления*/
    private int gribSumma = 0;
    /*максимальное количество грибов, к которому стремимся*/
    public static final int MAX_GRIBS = 20;

    /*в конструкторе инициализируем сканер и запускаем логику*/
    public GribTaker() {
        scanner = new Scanner(System.in);
        showGreeting();
        takeGribi();
    }

    /*просим юзерра ввести количество грибов пока сумма всех грибов не больше 20, тогда работа программы прекращается*/
    private void takeGribi() {
        while (gribSumma < MAX_GRIBS) {
            System.out.println("Положите грибы и нажмите Enter");
            int newGribs = scanner.nextInt();
            countGribsAndPrintStatus(newGribs);
        }
    }

    /*конструкция switch, которую просят в задании
    * в зависимости от того сколько грибов положили, выводятся разные сообщения*/
    private void countGribsAndPrintStatus(int newGribs) {
        gribSumma += newGribs;
        switch (newGribs) {
            case 1:
                System.out.print("Положили " + 1 + " гриб");
                break;
            case 2:
                System.out.print("Положили " + 2 + " гриб");
                break;
            case 3:
                System.out.print("Положили " + 3 + " гриб");
                break;
            case 4:
                System.out.print("Положили " + 4 + " гриб");
                break;
            case 5:
                System.out.print("Положили " + 5 + " гриб");
                break;
            default:
                System.out.print("Положыли более 5 грибов");
        }
        System.out.println(", общая сумма теперь = " + gribSumma);
    }

    /*выводим на екран приветсвие*/
    private void showGreeting() {
        System.out.println("Будем собирать грибы до 20 штук");
    }
}
