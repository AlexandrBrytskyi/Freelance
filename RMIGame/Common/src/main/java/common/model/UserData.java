package common.model;


import java.util.Arrays;

public class UserData {
    private Integer[] numbersForUser;
    private String userName;

    public UserData() {
    }

    public UserData(Integer[] numbersForUser, String userName) {
        this.numbersForUser = numbersForUser;
        this.userName = userName;
    }



    public Integer[] getNumbersForUser() {
        return numbersForUser;
    }

    public void setNumbersForUser(Integer[] numbersForUser) {
        this.numbersForUser = numbersForUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "numbersForUser=" + Arrays.toString(numbersForUser) +
                ", userName='" + userName + '\'' +
                '}';
    }
}
