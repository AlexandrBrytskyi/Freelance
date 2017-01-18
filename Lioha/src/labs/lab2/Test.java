package labs.lab2;

import java.util.HashSet;

/**
 * Created by alexandr on 18.10.16.
 */
public class Test {


    public static final int VECTORS_AMOUNT = 10;
    public static final int Y = 0;
    public static final int HASH_TABLE_SIZE = 5;

    public static void main(String[] args) {
        VectorHashTable vectorHashTable = new VectorHashTable(HASH_TABLE_SIZE);

        addVectorsAndPrint(vectorHashTable, VECTORS_AMOUNT);

        showIteratorWork(vectorHashTable);

        removeWithCriteriaAndPrint(vectorHashTable, Y);
    }

    private static void removeWithCriteriaAndPrint(VectorHashTable vectorHashTable, int y) {
        System.out.println("After removing elements with y < " + y);
        vectorHashTable.remove(y);
        System.out.println(vectorHashTable);
    }

    private static void showIteratorWork(VectorHashTable vectorHashTable) {
        System.out.println("iterator");
        for (Object o : vectorHashTable) {
            Vector v = (Vector) o;
            System.out.println(v);
        }
    }

    private static void addVectorsAndPrint(VectorHashTable vectorHashTable, int amount) {
        for (int i = 0; i < amount; i++) {
            vectorHashTable.add(new Vector());
        }

        System.out.println("After adding");
        System.out.println(vectorHashTable);
    }

}
