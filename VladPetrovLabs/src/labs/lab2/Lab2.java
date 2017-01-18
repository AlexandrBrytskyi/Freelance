package labs.lab2;



public class Lab2 {

    /*метод, который принимает массив чисел и считает среднее арифметическое
    * при вызове метода параметры передаются через кому независимо от того, сколько их
    *
    * считаем сумму, делим на количество элементов*/
    public double findAverage(double... values) {

        System.out.print("find average between ");

        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            System.out.print(values[i] + " ");
        }
        double res = sum / values.length;
        System.out.print("= " + res);
        return res;
    }
}
