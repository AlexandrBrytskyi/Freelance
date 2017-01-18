package weather.console.exceptions;


public class WrongCommandException extends Exception {
    public WrongCommandException(String message) {
        super(message);
    }
}
