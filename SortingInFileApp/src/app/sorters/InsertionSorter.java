package app.sorters;


import java.util.Comparator;
import java.util.List;


public class InsertionSorter implements Sorter {


    public <T> void sort(List<T> listToSort, Comparator<T> comparator) {
        for (int i = 1; i < listToSort.size(); i++) {
            for (int j = 0 + i; j > 0; j--) {
                T elem = listToSort.get(j);
                if (comparator.compare(elem, listToSort.get(j - 1)) < 0) Utils.swap(listToSort, j, j - 1);
            }
        }
    }

}
