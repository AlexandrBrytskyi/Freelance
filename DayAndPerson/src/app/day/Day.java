package app.day;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandr on 01.10.16.
 */
public class Day {
    /*
    * a. Set the day.
b. Print the day.
c. Return the day.
d. Return the next day.
e. Return the previous day.
f. Calculate and return the day by adding certain days to the current day. For example, if the current day is Monday and we add 4 days, the day to be returned is Friday. Similarly, if today is Tuesday and we add 13 days, the day to be returned is Monday.
g. Add the appropriate constructors.
h. Write the definitions of the methods to implement the operations for the class Day, as defined in A through G.
i. Write a program to test various operations on the class Day.*/

    private DaysOfWeek day;
    private int dayNubmerInWeek;

    public Day(DaysOfWeek day) {
        this.day = day;
        this.dayNubmerInWeek = Arrays.asList(DaysOfWeek.values()).indexOf(day) + 1;
    }

    public Day(int dayOfWeek) throws WrongDayOfWeekException {
        if (dayOfWeek == 0 || dayOfWeek > 7) throw new WrongDayOfWeekException(dayOfWeek);
        this.day = DaysOfWeek.values()[dayOfWeek - 1];
        this.dayNubmerInWeek = dayOfWeek;
    }

    public void printDay() {
        System.out.println("The day is " + day);
    }

    public Day getCurrentDay() {
        return this;
    }

    public Day getNextDay() {
        int newDayNum = dayNubmerInWeek;
        if (dayNubmerInWeek == 7) {
            newDayNum = 1;
        } else {
            newDayNum++;
        }
        try {
            return new Day(newDayNum);
        } catch (WrongDayOfWeekException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Day getPreviousDay() {
        int newDayNum = dayNubmerInWeek;
        if (dayNubmerInWeek == 1) {
            newDayNum = 7;
        } else {
            newDayNum--;
        }
        try {
            return new Day(newDayNum);
        } catch (WrongDayOfWeekException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Day getDateByPlusDays(int daysToPlus) {
        int newDayNum = dayNubmerInWeek;
        newDayNum = (newDayNum + daysToPlus) % 7;
        try {
            return newDayNum == 0 ? new Day(7) : new Day(newDayNum);
        } catch (WrongDayOfWeekException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DaysOfWeek getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Day{" +
                "day=" + day +
                ", dayNubmerInWeek=" + dayNubmerInWeek +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day1 = (Day) o;

        if (dayNubmerInWeek != day1.dayNubmerInWeek) return false;
        return day == day1.day;

    }
}
