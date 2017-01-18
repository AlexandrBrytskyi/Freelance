package labs.lab2;

/**
 * Created by alexandr on 18.10.16.
 */

/*Iterable because we must go through elements*/
public interface IMyHashTable extends Iterable {

    Vector add(Vector vector);

    /*removes all Vectors which has coordinates < than Y*/
    void remove(double y);

    int size();

    boolean isEmpty();

}
