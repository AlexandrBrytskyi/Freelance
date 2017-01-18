package app.dao;


import app.dao.storage.Storage;
import app.model.Ticket;
import app.model.TicketImpl;
import app.model.User;
import app.model.UserImpl;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTicketDao {

    private static TicketDao dao;
    private static ApplicationContext context;

    @BeforeClass
    public static void initDao() {
        context = new ClassPathXmlApplicationContext("classpath:app_context.xml");
        dao = context.getBean(TicketDao.class);
    }

    @AfterClass
    public static void saveMaps() {
        Storage storage = (Storage) context.getBean("storage");
        storage.saveMaps();
    }

    @Test
    public void _01add() {
        Ticket ticket = new TicketImpl(2, 3, Ticket.Category.BAR, 2);

        Ticket added = dao.addTicket(ticket);

        System.out.println(added);
        Assert.assertNotNull(added);
    }


    @Test
    public void _02remove() {
        Ticket ticket = new TicketImpl(2, 3, Ticket.Category.BAR, 2);
        Ticket added = dao.addTicket(ticket);

        long id = ticket.getId();

        Assert.assertTrue(dao.removeTicket(id));
    }

    @Test
    public void _03getUsersByName() {

    }

    @Test
    public void _04getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        int eventId = 24;
        long userId = 25;

        for (int i = 0; i < 5; i++) {

            Ticket.Category bar = Ticket.Category.BAR;
            int place = 4;
            tickets.add(new TicketImpl(eventId, userId, bar, place));
        }

        for (Ticket ticket : tickets) {
            Ticket added = dao.addTicket(ticket);
            System.out.println(added);
        }

        List<Ticket> getByEventId = dao.getTickets(eventId, 10, 1);
        System.out.println(getByEventId);

        Assert.assertTrue(getByEventId.size() == 5);

        List<Ticket> getByUserId = dao.getTickets(userId, 10, 1);
        System.out.println(getByUserId);

        Assert.assertTrue(getByUserId.size() == 5);

        for (Ticket ticket : getByUserId) {
            dao.removeTicket(ticket.getId());
        }
    }


}
