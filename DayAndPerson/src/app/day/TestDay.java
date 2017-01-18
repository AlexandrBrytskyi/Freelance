package app.day;

/**
 * Created by alexandr on 01.10.16.
 */
public class TestDay {
    static Day day = null;

    public static void main(String[] args) {
        System.out.println("Testing day methods");

        System.out.println("Test creating day");
        creatingDayTest();

        System.out.println("Printing day");
        day.printDay();

        System.out.println("Test method getCurrentDay");
        getCurrentDayTest();

        System.out.println("Test getNextDay");
        getNextDayTest();

        System.out.println("Test previousDay");
        getPreviousDayTest();

        System.out.println("Test getDayByPlusDays");
        getDaysByPlusDaysTest();

    }

    private static void getDaysByPlusDaysTest() {
        day = new Day(DaysOfWeek.TUESDAY);
        Day expectable = new Day(DaysOfWeek.MONDAY);
        Day result = day.getDateByPlusDays(13);
        System.out.println("Expectable value:" + expectable + "\n" +
                "value is " + result + ", result is " + result.equals(expectable));
    }

    private static void getPreviousDayTest() {
        Day expectable = new Day(DaysOfWeek.SATURDAY);
        Day result = day.getPreviousDay();
        System.out.println("Expectable value:" + expectable + "\n" +
                "value is " + result + ", result is " + result.equals(expectable));
    }

    private static void getNextDayTest() {
        Day expectable = new Day(DaysOfWeek.MONDAY);
        Day result = day.getNextDay();
        System.out.println("Expectable value:" + expectable + "\n" +
                "value is " + result + ", result is " + result.equals(expectable));
    }

    private static void getCurrentDayTest() {
        Day sunday = new Day(DaysOfWeek.SUNDAY);
        Day result = day.getCurrentDay();
        System.out.println("Expectable value:" + sunday + "\n" +
                "value is " + result + ", result is " + result.equals(sunday));
    }

    private static void creatingDayTest() {
        try {
            day = new Day(1);
        } catch (WrongDayOfWeekException e) {
            e.printStackTrace();
        }

        System.out.println("Expectable value:" + DaysOfWeek.SUNDAY + "\n" +
                "value is " + day.getDay() + ", result is " + day.getDay().equals(DaysOfWeek.SUNDAY));
    }
}
