package app.dao.storage;


import app.dao.EventDao;
import app.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventDaoImpl implements EventDao {

    @Autowired
    private Storage storage;


    @Override
    public Event getEventById(int id) {
        return storage.getById(Event.class, id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> res = storage.getWithCriteria(Event.class, "title", title);
        List<Event> toReturn = new ArrayList<>();
        for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) {
            try {
                toReturn.add(res.get(i));
            } catch (Exception e) {
                break;
            }
        }
        return toReturn;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> res = storage.getWithCriteria(Event.class, "date", day);
        List<Event> toReturn = new ArrayList<>();
        for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize; i++) {
            try {
                toReturn.add(res.get(i));
            } catch (Exception e) {
                break;
            }
        }
        return toReturn;
    }

    @Override
    public Event addEvent(Event event) {
        return storage.add(event, Event.class);
    }

    @Override
    public Event updateEvent(Event event) {
        return storage.update(event, Event.class);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return storage.remove(Event.class, (int) eventId);
    }
}
