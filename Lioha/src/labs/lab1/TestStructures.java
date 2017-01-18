package labs.lab1;

import labs.lab1.queue.IMyQueue;
import labs.lab1.queue.MyQueue;
import labs.lab1.stack.IMyStack;
import labs.lab1.stack.MyStack;


public class TestStructures {

    public static void main(String[] args) {
        /*инициализация структур*/
        IMyQueue myQueue = new MyQueue(10);
        IMyStack stack = new MyStack(10);

        /*в методе записываются значения в очередь
        * очередь выводится в консоль*/
        writeQueueElementsAndPrint(myQueue);

        /*последнее задание
        * из очереди забираем(удаляем) елементы,
         * все, которые больше 0, округляем и кладем в стек в двоичном виде
        * выводим структуры в консоль*/

        lastTask(myQueue, stack);

    }

    private static void lastTask(IMyQueue myQueue, IMyStack stack) {
        while (!myQueue.isEmpty()) {
            Double val = myQueue.remove();
            if (val>0) {
                try {
                    stack.push(Long.toBinaryString(Math.round(val)));
                } catch (ArrayIsFullException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(myQueue);
        System.out.println(stack);
    }

    private static void writeQueueElementsAndPrint(IMyQueue myQueue) {
        while (!myQueue.isFull()) {
            try {
                myQueue.add(Double.valueOf(Math.random() * 300 - 150));
            } catch (ArrayIsFullException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        System.out.println(myQueue);
    }
}
