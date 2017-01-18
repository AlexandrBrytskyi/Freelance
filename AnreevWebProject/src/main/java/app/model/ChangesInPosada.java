package app.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ChangesInPosada {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private WorkHistory workHistory;

    @OneToOne()
    @JoinColumn(referencedColumnName = "id")
    private Posada previousPosada;

    @OneToOne
    private Posada newPosada;

    @Column
    private String description;

    @Temporal(value = TemporalType.DATE)
    private Date datePosadaChanged;

    public ChangesInPosada() {
    }

    public ChangesInPosada(WorkHistory workHistory, Posada previousPosada, Posada newPosada, String description, Date datePosadaChanged) {
        this.workHistory = workHistory;
        this.previousPosada = previousPosada;
        this.newPosada = newPosada;
        this.description = description;
        this.datePosadaChanged = datePosadaChanged;
    }

    public WorkHistory getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(WorkHistory workHistory) {
        this.workHistory = workHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posada getPreviousPosada() {
        return previousPosada;
    }

    public void setPreviousPosada(Posada previousPosada) {
        this.previousPosada = previousPosada;
    }

    public Posada getNewPosada() {
        return newPosada;
    }

    public void setNewPosada(Posada newPosada) {
        this.newPosada = newPosada;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePosadaChanged() {
        return datePosadaChanged;
    }

    public void setDatePosadaChanged(Date datePosadaChanged) {
        this.datePosadaChanged = datePosadaChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangesInPosada that = (ChangesInPosada) o;

        if (id != that.id) return false;
        if (previousPosada != null ? !previousPosada.equals(that.previousPosada) : that.previousPosada != null)
            return false;
        if (newPosada != null ? !newPosada.equals(that.newPosada) : that.newPosada != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return datePosadaChanged != null ? datePosadaChanged.equals(that.datePosadaChanged) : that.datePosadaChanged == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (previousPosada != null ? previousPosada.hashCode() : 0);
        result = 31 * result + (newPosada != null ? newPosada.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (datePosadaChanged != null ? datePosadaChanged.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChangesInPosada{" +
                "id=" + id +
                ", previousPosada=" + previousPosada.getId() +
                ", newPosada=" + newPosada.getId() +
                ", description='" + description + '\'' +
                ", datePosadaChanged=" + datePosadaChanged +
                '}';
    }
}
