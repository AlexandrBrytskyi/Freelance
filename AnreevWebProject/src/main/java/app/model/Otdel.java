package app.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Otdel {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String otdelName;

    @Column
    private String otdelDesc;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "posadaOtdel", fetch = FetchType.EAGER)
    private List<Posada> posadasInOtdel;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Otdel> podOtdel;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "otdel", fetch = FetchType.EAGER)
    private List<User> workers;

    @ManyToOne()
    private Otdel parent;

    @OneToOne()
    private User mentor;

    public Otdel() {
    }

    public Otdel(String otdelName, String otdelDesc, List<Posada> posadasInOtdel, List<Otdel> podOtdel, Otdel parent, User mentor) {
        this.otdelName = otdelName;
        this.otdelDesc = otdelDesc;
        this.posadasInOtdel = posadasInOtdel;
        this.podOtdel = podOtdel;
        this.parent = parent;
        this.mentor = mentor;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtdelName() {
        return otdelName;
    }

    public void setOtdelName(String otdelName) {
        this.otdelName = otdelName;
    }

    public String getOtdelDesc() {
        return otdelDesc;
    }

    public void setOtdelDesc(String otdelDesc) {
        this.otdelDesc = otdelDesc;
    }

    public List<Posada> getPosadasInOtdel() {
        return posadasInOtdel;
    }

    public void setPosadasInOtdel(List<Posada> posadasInOtdel) {
        this.posadasInOtdel = posadasInOtdel;
    }

    public List<Otdel> getPodOtdel() {
        return podOtdel;
    }

    public void setPodOtdel(List<Otdel> podOtdel) {
        this.podOtdel = podOtdel;
    }

    public Otdel getParent() {
        return parent;
    }

    public void setParent(Otdel parent) {
        this.parent = parent;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Otdel otdel = (Otdel) o;

        if (id != otdel.id) return false;
        if (otdelName != null ? !otdelName.equals(otdel.otdelName) : otdel.otdelName != null) return false;
        if (otdelDesc != null ? !otdelDesc.equals(otdel.otdelDesc) : otdel.otdelDesc != null) return false;
        if (posadasInOtdel != null ? !posadasInOtdel.equals(otdel.posadasInOtdel) : otdel.posadasInOtdel != null)
            return false;
        if (podOtdel != null ? !podOtdel.equals(otdel.podOtdel) : otdel.podOtdel != null) return false;
        if (parent != null ? !parent.equals(otdel.parent) : otdel.parent != null) return false;
        return mentor != null ? mentor.equals(otdel.mentor) : otdel.mentor == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (otdelName != null ? otdelName.hashCode() : 0);
        result = 31 * result + (otdelDesc != null ? otdelDesc.hashCode() : 0);
        result = 31 * result + (posadasInOtdel != null ? posadasInOtdel.hashCode() : 0);
        result = 31 * result + (podOtdel != null ? podOtdel.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (mentor != null ? mentor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Otdel{" +
                "id=" + id +
                ", otdelName='" + otdelName + '\'' +
                ", otdelDesc='" + otdelDesc + '\'' +
//                ", posadasInOtdel=" + posadasInOtdel +
//                ", podOtdel=" + podOtdel +
//                ", parent=" + parent +
//                ", mentor=" + mentor +
                '}';
    }
}
