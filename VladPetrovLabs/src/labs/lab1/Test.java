package labs.lab1;


public class Test {

    public static void main(String[] args) {

        /*создаем екземпляр генератора*/
        ArrayGenerator arrayGenerator = new ArrayGenerator();

        /*генерируем массив*/
        int[][] generated = arrayGenerator.generateArray(8, 8);


        /*выводим на экран*/
        System.out.println("Generated array ");
        arrayGenerator.print(generated);
    }
}
