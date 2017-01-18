package app.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkHistory {

    @Id
    @GeneratedValue
    private int id;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "workHistory", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<ChangesInSalary> changesInWork;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "workHistory", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<ChangesInPosada> changesInPosada;

    @OneToOne
    private User user;

    public WorkHistory() {
    }

    public WorkHistory(List<ChangesInSalary> changesInWork, List<ChangesInPosada> changesInPosada) {
        this.changesInWork = changesInWork;
        this.changesInPosada = changesInPosada;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChangesInSalary> getChangesInWork() {
        return changesInWork;
    }

    public void setChangesInWork(List<ChangesInSalary> changesInWork) {
        this.changesInWork = changesInWork;
    }

    public List<ChangesInPosada> getChangesInPosada() {
        return changesInPosada;
    }

    public void setChangesInPosada(List<ChangesInPosada> changesInPosada) {
        this.changesInPosada = changesInPosada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkHistory that = (WorkHistory) o;

        if (id != that.id) return false;
        if (changesInWork != null ? !changesInWork.equals(that.changesInWork) : that.changesInWork != null)
            return false;
        return changesInPosada != null ? changesInPosada.equals(that.changesInPosada) : that.changesInPosada == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (changesInWork != null ? changesInWork.hashCode() : 0);
        result = 31 * result + (changesInPosada != null ? changesInPosada.hashCode() : 0);
        return result;
    }


}
