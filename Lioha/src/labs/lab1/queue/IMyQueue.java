package labs.lab1.queue;

import labs.lab1.ArrayIsFullException;

/*Инттерфейс нашей очереди, который описывает методы. какие она реализует*/
public interface IMyQueue {


    /*add element to tail*/
    boolean add(Double val) throws ArrayIsFullException;

    /*take and remove the head*/
    Double remove();

    /*take head value but not remove*/
    Double peek();

    /*проверяет пустая ли очередь*/
    boolean isEmpty();

    /*проверяет полная ли очередь*/
    boolean isFull();

    /*возвращает размер очереди*/
    int size();


}
