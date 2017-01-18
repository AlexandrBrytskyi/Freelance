package app.day;

/**
 * Created by alexandr on 01.10.16.
 */
public class WrongDayOfWeekException extends Throwable {

    public WrongDayOfWeekException(int dayOfWeek) {
        super("There is no " + dayOfWeek + " day in the week");
    }
}
