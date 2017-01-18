package app.dao;


import app.model.Event;
import app.model.Ticket;
import app.model.User;

import java.util.List;

public interface TicketDao {


    Ticket addTicket(Ticket ticket);

    List<Ticket> getTickets(long userId, int pageSize, int pageNum);

    List<Ticket> getTickets(int eventId, int pageSize, int pageNum);

    boolean removeTicket(long ticketId);

}
