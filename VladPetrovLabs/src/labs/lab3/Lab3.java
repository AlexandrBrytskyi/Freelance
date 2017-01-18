package labs.lab3;



public class Lab3 {

    /*храним константы чисел между которыми считаем среднее арифметическое в методе без параметров*/
    public static final int A = 47;
    public static final int B = 94;


    /*метод без параметров*/
    public double findAverage() {

        System.out.print("find average between " + A + " " + B);
        double res = (A + B) / 2;
        System.out.print(" = " + res);
        return res;
    }


    /*перегруженный метод*/
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
