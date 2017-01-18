package app.services;

import app.dao.EventDao;
import app.dao.TicketDao;
import app.dao.UserDao;
import app.model.Event;
import app.model.Ticket;
import app.model.TicketImpl;
import app.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class TicketServiceImpl implements TicketService {
    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);


    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        LOGGER.info("trying to book ticket whith user " + userId + ", event " + eventId + ", place " + place + ", category " + category.toString());
        watchPlaceBooked(eventId, place);

        Ticket ticket = new TicketImpl(eventId, userId, category, place);
        LOGGER.info("adding ticket " + ticket);
        Ticket added = ticketDao.addTicket(ticket);
        LOGGER.info("added ticket " + added);
        return added;
    }

    private void watchPlaceBooked(long eventId, int place) {
        LOGGER.info("watching either exists tickets for that place " + place);
        boolean exists = false;
        List<Ticket> bookedForThatEvent = ticketDao.getTickets(eventId, Integer.MAX_VALUE, 1);
        if (!bookedForThatEvent.isEmpty()) for (Ticket ticket : bookedForThatEvent) {
            if (ticket.getPlace() == place) {
                exists = true;
                break;
            }
        }
        if (exists) {
            LOGGER.info("place is booked " + place + ", throw exception");
            throw new IllegalArgumentException("place is booked " + place);
        }
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        LOGGER.info("getting tickets booked by " + user + ", pageSize " + pageSize + ", pagenum " + pageNum);
        List<Ticket> founded = ticketDao.getTickets(user.getId(), pageSize, pageNum);
        if (founded.size() > 1) founded = sortAscendingEventDate(founded);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    private List<Ticket> sortAscendingEventDate(List<Ticket> founded) {
        Collections.sort(founded, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                return eventDao.getEventById((int) o2.getEventId()).getDate().compareTo(eventDao.getEventById((int) o1.getEventId()).getDate());
            }
        });
        return founded;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        LOGGER.info("getting tickets for event " + event + ", pageSize " + pageSize + ", pagenum " + pageNum);
        List<Ticket> founded = ticketDao.getTickets(((int) event.getId()), pageSize, pageNum);
        if (founded.size() > 1) founded = sortAscendingUserEmail(founded);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    private List<Ticket> sortAscendingUserEmail(List<Ticket> founded) {
        Collections.sort(founded, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                return userDao.getUserById((int) o2.getUserId()).getEmail().compareTo(userDao.getUserById((int) o1.getUserId()).getEmail());
            }
        });
        return founded;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        LOGGER.info("trying to cabcell ticket with id " + ticketId);
        boolean res = ticketDao.removeTicket(ticketId);
        LOGGER.info("removing ticket result " + res);
        return res;
    }
}
