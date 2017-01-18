package labs.lab1.stack;

import labs.lab1.ArrayIsFullException;

import java.util.Arrays;
import java.util.EmptyStackException;

/*Реализация стэка
* первый вошел - последний вышел*/
public class MyStack implements IMyStack {

    /*размер массива по умолчанию*/
    public static final int DEFAULT_ARR_SIZE = 20;

    /*массив для хранения елементов*/
    private String[] arr;

    /*количество елементов в массиве*/
    private int size;


    public MyStack() {
        arr = new String[DEFAULT_ARR_SIZE];
    }

    public MyStack(int rozmirnist) {
        arr = new String[rozmirnist];
    }

    @Override
    public String push(String s) throws ArrayIsFullException {
        /*кладем елемент в первую ячейку, тоесть в голову
        * бросаем ошибку, если массив заполнен
        * сначала освобождаеем место первой ячейки посредством перемещения всех елементов на одну позицию вправо
        * потом добавляем*/
        if (size >= arr.length) throw new ArrayIsFullException("Stack is full");
        System.arraycopy(arr, 0, arr, 1, size++);
        arr[0] = s;
        return arr[0];
    }

    @Override
    public String peek() {
        if (null == arr[0]) throw new EmptyStackException();
        return arr[0];
    }

    @Override
    public String pop() {
        /*возвращаем и удаляем из массива голову (1й елемент из массива)*/
        if (null == arr[0]) throw new EmptyStackException();
        String res = arr[0];
        System.arraycopy(arr, 1, arr, 0, size - 1);
        size--;
        return res;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "elements : " + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }
}
