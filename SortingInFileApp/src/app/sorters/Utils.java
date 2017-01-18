package app.sorters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;


public class Utils {

    public static void swap(List list, int firstInd, int secondInd) {
        if (list instanceof ArrayList) {
            Object temp = list.get(firstInd);
            list.set(firstInd, list.get(secondInd));
            list.set(secondInd, temp);
            temp = null;
        } else {
            ListIterator<Object> iterator1 = list.listIterator(firstInd);
            ListIterator<Object> iterator2 = list.listIterator(secondInd);
            Object temp = iterator1.next();
            iterator1.set(iterator2.next());
            iterator2.set(temp);
        }
    }


    public static Comparator<Integer> getIntegerComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public static Comparator<Double> getDoubleComparator() {
        return new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        };
    }

}
