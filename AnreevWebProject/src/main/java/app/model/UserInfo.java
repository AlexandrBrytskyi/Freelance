package app.model;

import javax.persistence.*;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String userName;

    @Column
    private String userSurname;

    @Column
    private String userPhone;

    @Column
    private String userAddress;

    @Column
    private String userPassport;

    public UserInfo() {
    }

    public UserInfo(String userName, String userSurname, String userPhone, String userAddress, String userPassport) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userPassport = userPassport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPassport() {
        return userPassport;
    }

    public void setUserPassport(String userPassport) {
        this.userPassport = userPassport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != userInfo.id) return false;
        if (userName != null ? !userName.equals(userInfo.userName) : userInfo.userName != null) return false;
        if (userSurname != null ? !userSurname.equals(userInfo.userSurname) : userInfo.userSurname != null)
            return false;
        if (userPhone != null ? !userPhone.equals(userInfo.userPhone) : userInfo.userPhone != null) return false;
        if (userAddress != null ? !userAddress.equals(userInfo.userAddress) : userInfo.userAddress != null)
            return false;
        return userPassport != null ? userPassport.equals(userInfo.userPassport) : userInfo.userPassport == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userPassport != null ? userPassport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPassport='" + userPassport + '\'' +
                '}';
    }

}
