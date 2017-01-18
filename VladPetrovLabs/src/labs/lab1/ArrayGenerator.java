package labs.lab1;


import java.util.Arrays;

/*Класс для генерации массива*/
public class ArrayGenerator {


    /*метод который принимает количество строк и колонок матрицы
    * и создает 2мерный массив, который заполняет рандомными числами от -100 до 100*/
    public int[][] generateArray(int rows, int columns) {
       int [][] res = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                res[i][j] = (int) (Math.random()*200-100);
            }
        }
        return res;
    }


    /*метод, для печпти 2мерного массива интов*/
    public void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

}
