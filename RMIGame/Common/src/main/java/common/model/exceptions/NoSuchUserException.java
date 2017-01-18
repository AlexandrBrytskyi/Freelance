package common.model.exceptions;


import java.io.Serializable;


public class NoSuchUserException extends Exception implements Serializable{

    public NoSuchUserException() {
    }

    public NoSuchUserException(String s) {
        super(s);
    }


}
