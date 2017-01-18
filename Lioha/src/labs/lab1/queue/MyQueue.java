package labs.lab1.queue;

import labs.lab1.ArrayIsFullException;

import java.util.Arrays;

/*Реализация очереди*/
public class MyQueue implements IMyQueue {

    /*переменная. которая хранит размер масива по умолчанию*/
    public static final int DEFAULT_ARR_SIZE = 20;

    /*количество елементов в структуре*/
    private int size;

    /*массив, в котором хранятся данные*/
    private Double[] arr;

    /*ссылка на голову очереди*/
    private Double head;

    /*индекс хвоста в массиве*/
    private int tailIndex;

    /*конструктор без аргументов, массив инициализируется с стандартным размером*/
    public MyQueue() {
        arr = new Double[DEFAULT_ARR_SIZE];
    }

    /*конструктор с параметром размера масива*/
    public MyQueue(int rozmirnist) {
        arr = new Double[rozmirnist];
    }

    @Override
    public boolean add(Double val) throws ArrayIsFullException {
        /*если массив заполнен бросаем ошибку*/
        if (isFull()) throw new ArrayIsFullException("Queue is full");

        /*добавляем елемент в хвост и инкрементируем индекс хвоста*/
        arr[tailIndex++] = val;
        if (head == null) {
            head = val;
        }
        size++;
        return true;
    }

    @Override
    public Double remove() {


        Double res = head;

        /*смещаем голову на один елемент дальше
        * делаем сдвиг елементов в массиве влево посредством копирования
        * быстрым системным методом*/

            head = arr[1];
        System.arraycopy(arr, 1, arr, 0, size - 1);
        arr[size-1] = null;
        tailIndex--;
        size--;
        return res;
    }


    @Override
    public Double peek() {
        return head;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == arr.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "size=" + size +
                "elements : " +
                Arrays.toString(arr) +
                '}';
    }
}
