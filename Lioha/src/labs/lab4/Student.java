package labs.lab4;

/**
 * Created by alexandr on 19.10.16.
 */
public class Student {
    public static final int MAX_TOWN_CODE_VALUE = 999;
    /*
    *Прізвище, ім’я, код міста
(тризначне число), номер
домашнього телефону
Розподіленого
підрахунку
За кодом міста  */

    private String surname;
    private String name;
    private int townCode;
    private String phoneNum;

    public Student(String surname, String name, int townCode, String phoneNum) {
        this.surname = surname;
        this.name = name;
        /*код города может быть число от 0 то 999*/
        if (townCode < 0 || townCode > MAX_TOWN_CODE_VALUE) throw new IllegalArgumentException("Code is 3 digit value and must be bigger than 0");
        this.townCode = townCode;
        this.phoneNum = phoneNum;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTownCode() {
        return townCode;
    }

    public void setTownCode(int townCode) {
        this.townCode = townCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                " townCode=" + townCode +
                ", surname='" + surname +
                ", name='" + name +
                ", phoneNum='" + phoneNum +
                '}';
    }
}
