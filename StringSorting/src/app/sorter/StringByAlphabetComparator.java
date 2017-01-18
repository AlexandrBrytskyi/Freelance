package app.sorter;

import java.util.Comparator;

/*
класс для сравнения строк по алфавиту
реалиует один метод "compare(String string1, String string2)"
так как этого требует интерфейс Comparator<String>
*/
public class StringByAlphabetComparator implements Comparator<String> {


    /*метод возвращает  0 если по алфавиту строки одинаковые
    *                   число больше 0 если первая строка больше второй
     *                  число меньше 0 если первая строка меньше второй*/
    @Override
    public int compare(String string1, String string2) {
        int length1 = string1.length();
        int length2 = string2.length();
        int lim = Math.min(length1, length2);
        char chars1[] = string1.toCharArray();
        char chars2[] = string2.toCharArray();

        int counter = 0;
        while (counter < lim) {
            char c1 = chars1[counter];
            char c2 = chars2[counter];
            /*сравниваем номера символов в таблице UTF-8*/
            if (c1 != c2) {
                return c1 - c2;
            }
            counter++;
        }
        return 0;
    }
}
