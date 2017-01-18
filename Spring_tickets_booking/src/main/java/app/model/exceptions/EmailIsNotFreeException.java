package app.model.exceptions;


public class EmailIsNotFreeException extends Throwable {


    public EmailIsNotFreeException(String email) {
        super("User with email " + email + " already exists");
    }
}
