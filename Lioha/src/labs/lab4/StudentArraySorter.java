package labs.lab4;

import java.util.*;

/*клас сортер масива студентов
* можно добавлять и другие сортировки сюда потом*/
public class StudentArraySorter {


    /*сортировка подсчетом осуществляется таким образом
    * 1 создаем дополнительный массив размерностью найбольшего елемента в массиве
    * 2 пробегаемся по массиву который нужно отсортировать,
    *               и добавляем в дополнительный массив по индексу текущей величины елемента текущий елемент\
    *               (тоесть делаем подсчет)
    * 3 инициализируем счетчик
    * 4 пробегаем по дополнительному массиву и когда находим значение,
    *               то записываем в результат по индексу счетчика
    *               значение дополнительного массива сколько раз,
    *               чему соответствует текущее значение дополнительного массива (количество подсчетов)
    *               */

    /*возвращаем время сортировки в милисекундах*/
    public long countSort(Student[] array) {

        long startTime = System.nanoTime();

        /*создаем массив очередей, с помощью которых будет осущесвяться подсчет елементов(студентов)
        *
        *   почему очередь, потому что считаем не инты, а студенты*/
        Queue<Student>[] c = new Queue[Student.MAX_TOWN_CODE_VALUE];



        /* 2 пробегаемся по массиву который нужно отсортировать,
        *       и добавляем в дополнительный массив по индексу текущей величины елемента текущий елемент\
        *       (тоесть делаем подсчет)*/
        for (Student student : array) {
            int code = student.getTownCode();
            if (c[code] == null) {
                c[code] = new LinkedList<>();
                c[code].add(student);
            } else {
                c[code].add(student);
            }
        }
        int n = 0;


        /*4 пробегаем по дополнительному массиву и когда находим значение,
        *       то записываем в результат по индексу счетчика
        *       значение дополнительного массива сколько раз,
        *       чему соответствует текущее значение дополнительного массива (количество подсчетов)*/
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < (null == c[i] ? 0 : c[i].size()); j++) {
                array[n++] = c[i].remove();
            }
        }
        return System.nanoTime() - startTime;
    }

}