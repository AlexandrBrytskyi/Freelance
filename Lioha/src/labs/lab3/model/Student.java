package labs.lab3.model;

/**
 * Created by alexandr on 18.10.16.
 */
public class Student {

    /*Прізвище, ім'я, курс, студентський квиток, стипендія*/
    private String surname;
    private String name;
    private Course course;
    private int studak;
    private Stipendia stipendia;

    public Student(String surname, String name, Course course, int studak, Stipendia stipendia) {
        this.surname = surname;
        this.name = name;
        this.course = course;
        this.studak = studak;
        this.stipendia = stipendia;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public int getStudak() {
        return studak;
    }

    public Stipendia getStipendia() {
        return stipendia;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudak(int studak) {
        this.studak = studak;
    }

    public void setStipendia(Stipendia stipendia) {
        this.stipendia = stipendia;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", studak='" + studak + '\'' +
                ", stipendia=" + stipendia +
                '}';
    }
}
