package app.sorters;

import java.util.Comparator;
import java.util.List;


public class BubbleSorter implements Sorter {


    public <T> void sort(List<T> listToSort, Comparator<T> comparator) {

        if (listToSort==null||listToSort.size()<2) return;
        for (int i = 0; i < listToSort.size()-1 ; i++) {
            for (int j = 0; j < listToSort.size() - 1-i; j++) {
                compareAndSwap(listToSort, comparator, j);
            }
        }
    }

    private <T> void compareAndSwap(List<T> listToSort, Comparator<T> comparator, int j) {
        if (comparator.compare(listToSort.get(j), listToSort.get(j + 1)) > 0) {
            T temp = listToSort.get(j);
            listToSort.set(j, listToSort.get(j + 1));
            listToSort.set(j + 1, temp);
        }
    }

}
