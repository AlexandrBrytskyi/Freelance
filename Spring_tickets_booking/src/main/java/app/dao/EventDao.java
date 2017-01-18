package app.dao;


import app.model.Event;

import java.util.Date;
import java.util.List;

public interface EventDao {


    Event getEventById(int id);


    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);


    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);


    Event addEvent(Event event);


    Event updateEvent(Event event);


    boolean deleteEvent(long eventId);


}
