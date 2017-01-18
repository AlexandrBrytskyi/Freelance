package app.model;


import java.io.Serializable;
import java.util.Date;

public class EventImpl implements Serializable, Event {

    private long id;
    private String title;
    private Date date;

    public EventImpl() {
    }

    public EventImpl(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventImpl event = (EventImpl) o;

        if (id != event.id) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        return date != null ? date.equals(event.date) : event.date == null;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }



    @Override
    public String toString() {
        return "EventImpl{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }

}
