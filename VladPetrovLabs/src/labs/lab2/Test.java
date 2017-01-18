package labs.lab2;


/*Создаем экземпляр класса с методом и вызываем единственный метод */
public class Test {

    public static void main(String[] args) {
        Lab2 lab2 = new Lab2();

                        /*можно передать и больше параметров
                        lab2.findAverage(47, 94, 54, 78, 96, 85)*/
        double average = lab2.findAverage(47, 94,58,46.50);

    }
}
