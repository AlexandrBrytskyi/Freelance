package app.services;

import app.dao.EventDao;
import app.model.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Override
    public Event getEventById(int id) {
        LOGGER.info("getting event with id " + id);
        Event founded = eventDao.getEventById(id);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        LOGGER.info("getting events with title " + title  + ", pageSize " + pageSize + ", pagenum " + pageNum);
        List<Event> founded = eventDao.getEventsByTitle(title, pageSize, pageNum);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        LOGGER.info("getting events with date day " + day  + ", pageSize " + pageSize + ", pagenum " + pageNum);
        List<Event> founded = eventDao.getEventsForDay(day, pageSize, pageNum);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    @Override
    public Event createEvent(Event event) {
        LOGGER.info("creating event " + event);
        Event added = eventDao.addEvent(event);
        LOGGER.info("added event " + added);
        return added;
    }

    @Override
    public Event updateEvent(Event event) {
        LOGGER.info("updating event " + event);
        return eventDao.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        LOGGER.info("removing event " + eventId);
        boolean res = eventDao.deleteEvent(eventId);
        LOGGER.info("removing result " + res);
        return res;
    }
}
