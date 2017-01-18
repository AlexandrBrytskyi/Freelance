package dataStructures.arraySet;

import java.util.*;

/*Класс который нам нужно реализовать*/
/*Для того, что бы структура работала корректно, переопределите метод equals*/
public class ArraySet<T> implements Set<T> {

    /*массив в котором мы будем хранить елементы*/
    private T[] elements;

  /*  минимальная размерность массива*/
    private static final int MIN_CAPACITY = 10;

   /* текущая размерность массива*/
    private int capacity = MIN_CAPACITY;

   /* количесво елементов в масииве*/
    private int size = 0;

   /* индекс свободного места в массиве*/
    private int freeIndex = 0;


   /* конструктор по умолчанию
    здесь просто инициализуем пустой массив елементов*/
    public ArraySet() {
        elements = (T[]) new Object[capacity];
    }

   /* в этом конструкторе задаем размерность
    и инициализируем массив*/
    public ArraySet(int capacity) {
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }

 /*   понятно*/
    @Override
    public int size() {
        return size;
    }

    /*тоже понятно*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

   /* используем наш итератор для того чтобы пройтись по елементам структуры
            если находим интересующий елемент возвращаем true*/
    @Override
    public boolean contains(Object o) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T val = iterator.next();
            if (val.equals(o)) return true;
        }
        return false;
    }


   /* создает екземпляр нашего итератора*/
    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    /*просто возвращаем масив елементов*/
    @Override
    public Object[] toArray() {
        return elements;
    }


   /* тут смотрим, если размер масива, в который нужно записать елементы меньше чем количество елементов
            то создаем новый масив, иначе записываем елементы в тот, который прихождит и возвращаем*/
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length <= size) a = (T1[]) new Object[size];
        System.arraycopy(elements, 0, a, 0, size);
        return a;
    }


   /* добавление елемента*/
    @Override
    public boolean add(T t) {
       /* если такой обьект уже есть, мы его не добавляем*/
        if (contains(t)) return false;

        /*иначе добавляем елемент на свободную позицию, потом инкрементируем её*/
        elements[freeIndex++] = t;
        size++;

       /* метод который смотрит сколько елементов в масиве, и если нужно увеличивает размер масива*/
        watchAndGrow();
        return true;
    }

    private void watchAndGrow() {
        /*если массив заполнен нужно увеличить размер*/
        if (freeIndex == capacity) {
            growArraySize();
        }
    }

    private void growArraySize() {
      /*  увеличиваем размерность  в полтора раза*/
        capacity *= 1.5;

     /*   создаем новый массив и копируем в него содержание текущего масива елементов*/
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(elements, 0, newArr, 0, elements.length);

       /* присваиваем ссылке елементов новое значение*/
        elements = newArr;
    }


   /* тоже самое что и с увеличением размера массива, но уменьшаем размер на 20 процентов, если он заполнен елементами только наполовину
            нужно когда елементы удаляются из структуры*/
    private void watchAndDegree() {
        if (capacity / size > 2 && capacity >= MIN_CAPACITY) {
            capacity = (int) (capacity * 0.8);
            T[] newArr = (T[]) new Object[capacity];
            System.arraycopy(elements, 0, newArr, 0, size);
            elements = newArr;
        }
    }

   /* используем итератор
            если находим такой елемент, удалем его тем же итератором*/
    @Override
    public boolean remove(Object o) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T val = iterator.next();
            if (val.equals(o)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


/*    проверяет методом contains каждый елемент входящей коллекции*/
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

  /*  понятно*/
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T t : c) {
            res |= add(t);
        }
        return res;
    }

  /*  оставляет в структуре только те елементы, которые есть в коллекции*/
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T val = iterator.next();
            if (!c.contains(val)) {
                remove(val);
                res |= true;
            }
        }
        return res;
    }


 /*   понятно*/
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            res |= remove(o);
        }
        return res;
    }

/*    понятно*/
    @Override
    public void clear() {
        elements = (T[]) new Object[MIN_CAPACITY];
    }


    @Override
    public String toString() {
        String res = "";
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            res += iterator.next() + ",";
        }
        return res;
    }


    /*реализация итератора*/
    private class ArraySetIterator implements Iterator<T> {

       /* текущий елемент на который смотрит итератор*/
        private int currPosition = -1;

       /* если мы в конце массива или следующий елемент null возвращаем false*/
        @Override
        public boolean hasNext() {
            if (currPosition == elements.length - 1 || elements[currPosition + 1] == null) return false;
            return true;
        }

       /* возвращаем ссылку на следующий елемент массива*/
        @Override
        public T next() {
            return elements[++currPosition];
        }

        @Override
        public void remove() {
           /* если удаляем последний елемент, то просто присваиваем значение null
                    иначе делаем сдвиг елементов влево посредством их копирования методом System.arraycopy()*/
            if (currPosition == capacity - 1) {
                elements[currPosition] = null;
            } else {
                System.arraycopy(elements, currPosition + 1, elements, currPosition, elements.length - currPosition - 1);
            }

           /* если нужно урезаем массив*/
            watchAndDegree();
            size--;
            freeIndex--;
        }
    }

}
