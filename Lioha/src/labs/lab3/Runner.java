package labs.lab3;

import labs.lab3.model.Course;
import labs.lab3.model.Stipendia;
import labs.lab3.model.Student;
import labs.lab3.tree.IMyBinaryTree;
import labs.lab3.tree.MyBinaryTree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alexandr on 18.10.16.
 */
public class Runner {
    private IMyBinaryTree tree;

    public Runner() {
        /*инициализируем дерево*/
        tree = new MyBinaryTree();

        /*заполняе дерево данными*/
        fillTree();

        /*выводим дерево в консоль*/
        printWith2Methods();

        /*запускаем консольный интерфейс пользователя*/
        initUserDialog();
    }

    /*запрашиваем пользователя ввести критерии из клавиатуры*/
    private void initUserDialog() {
        Scanner s = new Scanner(System.in);
        System.out.println("Now enter course of students must be removed");
        System.out.println("Available vars are: " + Arrays.toString(Course.values()));
        String course = s.next();
        System.out.println("Now enter Stipendia of students must be removed");
        System.out.println("Available vars are: " + Arrays.toString(Stipendia.values()));
        String stipuha = s.next();

        /*запрашиваем у дерева лист студентов, который подходит критерию
        * выводим его и удаляем
        * если елементов для удаления нет, програма останавливается*/
        List<Student> toRemove = tree.findByCriteria(Course.valueOf(course), Stipendia.valueOf(stipuha));
        if (toRemove.isEmpty()) {
            System.out.println("Nothing to remove");
            System.exit(0);
        } else {
            System.out.println("We must remove " + toRemove);
            System.out.println("Start removing and printing each operation...");
        }

        /*выводим каждого удаляемого студента и производим удаление из дерева
        * после каждого удаления дерево выводится заново*/
        for (Student student : toRemove) {
            System.out.println("Removing now :" + student);
            tree.remove(student.getStudak());
            printWith2Methods();
        }

    }

    /*выводим в консоль содержымое дерева 2мя способами
    * в первом обходим структуру паралельно
    * во втором обходим в ширину (наочнее показывает структуру дерева)*/
    public void printWith2Methods() {
        System.out.println("Obhod paralelno\n" + tree.getStringTreeParalel() + "\n");
        System.out.println("Obhod v shirinu\n" + tree.getStringTreeShirina() + "\n");
    }


    /*заполнение дерева данными*/
    private void fillTree() {
        System.out.println("Dodaemo zalikovki u poriadku:\n");
        for (int i = 0; i < 10; i++) {
            Student st = new Student("Surname" + i, "Name" + i,
                    Course.values()[i % Course.values().length],
                    (int) (Math.random() * 10000),
                    Stipendia.values()[i % Stipendia.values().length]);
            System.out.println(st.getStudak());
            tree.add(st.getStudak(), st);
        }
    }

    public static void main(String[] args) {
        new Runner();
    }

}
