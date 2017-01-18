package labs.lab1.stack;

import labs.lab1.ArrayIsFullException;

import java.util.Stack;

/*Интерфейс описывает методы*/
public interface IMyStack {


    /*add new element in head*/
    String push(String s) throws ArrayIsFullException;

    /*return head but do not remove*/
    String peek();

    /*return and remove head*/
    String pop();

    boolean isEmpty();

    int size();
}
