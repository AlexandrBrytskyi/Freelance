package dataStructures.arraySet;

import java.util.ArrayList;

/**
 * Created by alexandr on 14.10.16.
 */
public class ArraysSetTest {


    public static void main(String[] args) {
        /*Создаем нашу структуру*/
        ArraySet<Integer> arraySet = new ArraySet<>();

        /*записываем елементы, должно быть 15 елементов, так как записать одинаковые данные нельзя*/
        for (int i = 1; i <= 20; i++) {
            if (i < 10) {
                arraySet.add(i);
            } else {
                arraySet.add(i - 5);
            }
        }
        System.out.println(arraySet);

        /*проверяем как работает удаление елемента*/
        arraySet.remove(13);
        System.out.println(arraySet);

        /*создаем лист с елементами от 1 до 10*/
        ArrayList<Integer> ints = new ArrayList<Integer>() {{
            for (int i = 0; i < 10; i++) {
                add(i);
            }
        }};

        /*проверяем работу removeAll()*/
        arraySet.removeAll(ints);
        System.out.println(arraySet);

        /*проверяем работу метода addAll()*/
        arraySet.addAll(ints);
        System.out.println(arraySet);


        /*проверяем как работает for each*/
        System.out.println("print with for each");
        for (Integer integer : arraySet) {
            System.out.print(integer + ",");
        }
    }
}
