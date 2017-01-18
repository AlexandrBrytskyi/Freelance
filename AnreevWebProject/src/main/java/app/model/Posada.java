package app.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Posada {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String posadaName;

    @OneToOne()
    private User mentor;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User userWorking;

    @Column
    private String description;

    @OneToOne
    private Otdel posadaOtdel;

    @Column
    private double salary;


    public Posada() {
    }

    public Posada(String posadaName, User mentor, User userWorking, String description, Otdel posadaOtdel, double salary) {
        this.posadaName = posadaName;
        this.mentor = mentor;
        this.userWorking = userWorking;
        this.description = description;
        this.posadaOtdel = posadaOtdel;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosadaName() {
        return posadaName;
    }

    public void setPosadaName(String posadaName) {
        this.posadaName = posadaName;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    public User getUserWorking() {
        return userWorking;
    }

    public void setUserWorking(User userWorking) {
        this.userWorking = userWorking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Otdel getPosadaOtdel() {
        return posadaOtdel;
    }

    public void setPosadaOtdel(Otdel posadaOtdel) {
        this.posadaOtdel = posadaOtdel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posada posada = (Posada) o;

        if (id != posada.id) return false;
        if (Double.compare(posada.salary, salary) != 0) return false;
        if (posadaName != null ? !posadaName.equals(posada.posadaName) : posada.posadaName != null) return false;
        if (mentor != null ? !mentor.equals(posada.mentor) : posada.mentor != null) return false;
        if (userWorking != null ? !userWorking.equals(posada.userWorking) : posada.userWorking != null) return false;
        if (description != null ? !description.equals(posada.description) : posada.description != null) return false;
        return posadaOtdel != null ? posadaOtdel.equals(posada.posadaOtdel) : posada.posadaOtdel == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (posadaName != null ? posadaName.hashCode() : 0);
        result = 31 * result + (mentor != null ? mentor.hashCode() : 0);
        result = 31 * result + (userWorking != null ? userWorking.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (posadaOtdel != null ? posadaOtdel.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Posada{" +
                "id=" + id +
                ", posadaName='" + posadaName + '\'' +
//                ", mentor=" + mentor +
//                ", userWorking=" + userWorking +
//                ", description='" + description + '\'' +
//                ", posadaOtdel=" + posadaOtdel +
                ", salary=" + salary +
                '}';
    }
}
