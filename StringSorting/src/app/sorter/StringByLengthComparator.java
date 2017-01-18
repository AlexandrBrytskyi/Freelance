package app.sorter;

import java.util.Comparator;


/*
класс для сравнения строк по длинне
реалиует один метод "compare(String string1, String string2)"
так как этого требует интерфейс Comparator<String>
*/
public class StringByLengthComparator implements Comparator<String> {


    @Override
    public int compare(String string1, String string2) {
        int length1 = string1.length();
        int length2 = string2.length();
        /*сравниваем длинну строк*/
        return length1 - length2;
    }
}
