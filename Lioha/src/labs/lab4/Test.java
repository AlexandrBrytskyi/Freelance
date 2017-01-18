package labs.lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandr on 19.10.16.
 */
public class Test {

    public static final int ARRAY_SIZE = 15;

    public static void main(String[] args) {
        Student[] students = new Student[ARRAY_SIZE];

        /*заполняем массив студентом данными*/
        fillStudents(students);

        System.out.println("After creating");
        System.out.println(makeString(students));

        /*сортируем*/
        StudentArraySorter sorter = new StudentArraySorter();
        long nanos = sorter.countSort(students);


        System.out.println("After sorting");
        System.out.println(makeString(students));
        System.out.printf("Time for sorting is %d nanos", nanos);
    }

    private static void fillStudents(Student[] students) {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            Student student = new Student("Surname" + i, "Name" + i, (int) (Math.random() * 1000), "+380973991" + i + i);
            students[i] = student;
        }
    }

    /*красиво формирует строку с елементов массива*/
    private static String makeString(Student[] students) {
        StringBuilder builder = new StringBuilder();
        for (Student student : students) {
            builder.append(student).append("\n");
        }
        return builder.toString();
    }
}
