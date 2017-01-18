package app.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ChangesInSalary {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private WorkHistory workHistory;

    @Column
    private double previousSalary;

    @Column
    private double difference;

    @Column
    private double changedSalary;

    @Temporal(TemporalType.DATE)
    private Date dateSalaryChanged;

    @Column
    private String description;

    public ChangesInSalary() {
    }

    public WorkHistory getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(WorkHistory workHistory) {
        this.workHistory = workHistory;
    }

    public ChangesInSalary(WorkHistory workHistory, double previousSalary, double difference, double changedSalary, Date dateSalaryChanged, String desc) {

        this.workHistory = workHistory;
        this.previousSalary = previousSalary;
        this.difference = difference;
        this.changedSalary = changedSalary;
        this.dateSalaryChanged = dateSalaryChanged;
        this.description = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreviousSalary() {
        return previousSalary;
    }

    public void setPreviousSalary(double previousSalary) {
        this.previousSalary = previousSalary;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getChangedSalary() {
        return changedSalary;
    }

    public void setChangedSalary(double changedSalary) {
        this.changedSalary = changedSalary;
    }

    public Date getDateSalaryChanged() {
        return dateSalaryChanged;
    }

    public void setDateSalaryChanged(Date dateSalaryChanged) {
        this.dateSalaryChanged = dateSalaryChanged;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangesInSalary that = (ChangesInSalary) o;

        if (id != that.id) return false;
        if (Double.compare(that.previousSalary, previousSalary) != 0) return false;
        if (Double.compare(that.difference, difference) != 0) return false;
        if (Double.compare(that.changedSalary, changedSalary) != 0) return false;
        if (dateSalaryChanged != null ? !dateSalaryChanged.equals(that.dateSalaryChanged) : that.dateSalaryChanged != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(previousSalary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(difference);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(changedSalary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateSalaryChanged != null ? dateSalaryChanged.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChangesInSalary{" +
                "id=" + id +
                ", previousSalary=" + previousSalary +
                ", difference=" + difference +
                ", changedSalary=" + changedSalary +
                ", dateSalaryChanged=" + dateSalaryChanged +
                ", description='" + description + '\'' +
                '}';
    }
}
