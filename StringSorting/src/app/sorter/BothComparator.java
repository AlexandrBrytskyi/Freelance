package app.sorter;

import java.util.Comparator;

/**
 * Сравнивает по обоим критериям
 */
public class BothComparator implements Comparator<String> {

    private Comparator<String> alphabetComparator = new StringByAlphabetComparator();
    private Comparator<String> lengthComparator = new StringByLengthComparator();

    @Override
    public int compare(String string1, String string2) {
        /*записываем результат сравнения по алфавиту*/
        int alphabetResult = alphabetComparator.compare(string1, string2);

        /*если результат сравнения по алфавиту = 0 (по алфавиту строки равны), то сравниваем
        по длинне и возвращаем результат сравнивания по длинне
        иначе просто возвращаем значения сравнения по алфавиту*/
        return alphabetResult == 0 ? lengthComparator.compare(string1, string2) : alphabetResult;
    }
}
