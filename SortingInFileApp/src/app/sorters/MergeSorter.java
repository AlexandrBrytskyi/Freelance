package app.sorters;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class MergeSorter implements Sorter{


    public <T> void sort(List<T> list, Comparator<T> comparator) {
        mergeSortPr(list, comparator);
    }

    private <T> void mergeSortPr(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) return;

        List<T> half1 = copyArr(list, 0, list.size() / 2);
        List<T> half2 = copyArr(list, half1.size(), list.size() - half1.size());

        mergeSortPr(half1, comparator);
        mergeSortPr(half2, comparator);

        list = mergeSorted(half1, half2, comparator, list);
    }


    private <T> List<T> copyArr(List<T> list, int startIndex, int size) {
        List<T> res = new LinkedList<T>();
        for (int i = 0; i < size; i++) {
            res.add(list.get(startIndex + i));
        }
        return res;
    }


    private <T> List<T> mergeSorted(List<T> sorted1, List<T> sorted2, Comparator<T> comparator, List<T> list) {

        int positionOfFirst = 0, positionOfSecond = 0, positionResult = 0;
        for (int i = 0; i < sorted1.size() + sorted2.size(); i++) {
            if (positionOfFirst == sorted1.size()) {
                list.set(positionResult, sorted2.get(positionOfSecond));
                positionOfSecond++;
            } else if (positionOfSecond == sorted2.size()) {
                list.set(positionResult, sorted1.get(positionOfFirst));
                positionOfFirst++;
            } else if (comparator.compare(sorted1.get(positionOfFirst), sorted2.get(positionOfSecond)) > 0) {
                list.set(positionResult, sorted2.get(positionOfSecond));
                positionOfSecond++;
            } else {
                list.set(positionResult, sorted1.get(positionOfFirst));
                positionOfFirst++;
            }
            positionResult++;
        }
        return list;
    }

}
