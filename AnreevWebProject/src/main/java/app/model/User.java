package app.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userPersonalNum;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserInfo userInfo;

    @Lob
    private byte[] avatar;

    /*must create when user added*/
    @OneToOne()
    private Otdel otdel;

    /*must create when user added*/

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "userWorking", fetch = FetchType.EAGER)
    private List<Posada> posadas;

    /*must create when user added*/
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private WorkHistory workHistory;

    /*must create when user added*/
    @Column
    private boolean canCreateOtdel;

    @Column
    private String password;

    public User() {
    }

    public User(UserInfo userInfo, byte[] avatar, Otdel otdel, List<Posada> posadas, WorkHistory workHistory, boolean canCreateOtdel, String password) {
        this.userInfo = userInfo;
        this.avatar = avatar;
        this.otdel = otdel;
        this.posadas = posadas;
        this.workHistory = workHistory;
        this.canCreateOtdel = canCreateOtdel;
        this.password = password;
    }

    public int getUserPersonalNum() {
        return userPersonalNum;
    }

    public void setUserPersonalNum(int userPersonalNum) {
        this.userPersonalNum = userPersonalNum;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Otdel getOtdel() {
        return otdel;
    }

    public void setOtdel(Otdel otdel) {
        this.otdel = otdel;
    }

    public List<Posada> getPosadas() {
        return posadas;
    }

    public void setPosadas(List<Posada> posadas) {
        this.posadas = posadas;
    }

    public WorkHistory getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(WorkHistory workHistory) {
        this.workHistory = workHistory;
    }

    public boolean isCanCreateOtdel() {
        return canCreateOtdel;
    }

    public void setCanCreateOtdel(boolean canCreateOtdel) {
        this.canCreateOtdel = canCreateOtdel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userPersonalNum != user.userPersonalNum) return false;
        if (canCreateOtdel != user.canCreateOtdel) return false;
        if (userInfo != null ? !userInfo.equals(user.userInfo) : user.userInfo != null) return false;
        if (!Arrays.equals(avatar, user.avatar)) return false;
        if (otdel != null ? !otdel.equals(user.otdel) : user.otdel != null) return false;
        if (posadas != null ? !posadas.equals(user.posadas) : user.posadas != null) return false;
        if (workHistory != null ? !workHistory.equals(user.workHistory) : user.workHistory != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = userPersonalNum;
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(avatar);
        result = 31 * result + (otdel != null ? otdel.hashCode() : 0);
        result = 31 * result + (posadas != null ? posadas.hashCode() : 0);
        result = 31 * result + (workHistory != null ? workHistory.hashCode() : 0);
        result = 31 * result + (canCreateOtdel ? 1 : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userPersonalNum=" + userPersonalNum +
                ", userInfo=" + userInfo +
//                ", avatar=" + Arrays.toString(avatar) +
                ", otdel=" + otdel +
                ", posadas=" + posadas +
                ", workHistory=" + workHistory +
                ", canCreateOtdel=" + canCreateOtdel +
                ", password='" + password + '\'' +
                '}';
    }
}
