package app.dao.storage;


import app.dao.TicketDao;
import app.model.Event;
import app.model.Ticket;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private Storage storage;


    @Override
    public Ticket addTicket(Ticket ticket) {
        return storage.add(ticket, Ticket.class);
    }

    @Override
    public List<Ticket> getTickets(long userId, int pageSize, int pageNum) {
        List<Ticket> res = storage.getWithCriteria(Ticket.class, "userId", userId);
        List<Ticket> toReturn = new ArrayList<>();
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
    public List<Ticket> getTickets(int eventId, int pageSize, int pageNum) {
        List<Ticket> res = storage.getWithCriteria(Ticket.class, "eventId", (long)eventId);
        List<Ticket> toReturn = new ArrayList<>();
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
    public boolean removeTicket(long ticketId) {
        return storage.remove(Ticket.class, (int) ticketId);
    }
}
