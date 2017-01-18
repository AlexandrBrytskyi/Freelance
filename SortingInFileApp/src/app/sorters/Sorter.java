package app.sorters;


import java.util.Comparator;
import java.util.List;

public interface Sorter {

    <T> void sort(List<T> listToSort, Comparator<T> comparator);

}
