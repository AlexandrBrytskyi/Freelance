package app.facade_integration_test;

import app.facade.BookingFacade;
import app.model.*;
import app.model.exceptions.EmailIsNotFreeException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = {"classpath:app_context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FacadeTest {

    @Autowired
    private BookingFacade facade;

    static Date date;

    static {
        date = new Date();
//        date.setMinutes(date.getHours() - 1);
    }

    String single_email = String.valueOf(System.currentTimeMillis());
    static Event eventToBeAdded1 = new EventImpl("Den Andreya", date);
    static Event eventToBeAdded2 = new EventImpl("Kinoshecka", new Date());
    static User added = null;


    @Test
    public void _001addUser() {

        User vasia = new UserImpl("Vasia", single_email);

        try {
            vasia = facade.createUser(vasia);
            added = vasia;
        } catch (EmailIsNotFreeException e) {
            Assert.assertTrue(false);
        }
        Assert.assertNotNull(vasia);
    }


    /*must`n add*/
    @Test
    public void _002addUserWithSameEmail() {
        User user = new UserImpl("Petia", single_email);

        try {
            user = facade.createUser(user);
        } catch (EmailIsNotFreeException e) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void _003addSomeEvents() {

        eventToBeAdded1 = facade.createEvent(eventToBeAdded1);
        eventToBeAdded2 = facade.createEvent(eventToBeAdded2);

    }


    @Test
    public void _004getEventsForToday() {
        List<Event> events = facade.getEventsForDay(new Date(), Integer.MAX_VALUE, 1);

        /*2 last events must be equal to just added*/
        Assert.assertEquals(eventToBeAdded1, events.get(events.size() - 2));
        Assert.assertEquals(eventToBeAdded2, events.get(events.size() - 1));

    }


    @Test
    public void _005createTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            /*create tickets on last event with added user in method _001*/
            tickets.add(facade.bookTicket(added.getId(), eventToBeAdded1.getId(), i, Ticket.Category.BAR));
        }

        /*must be equal*/
        Assert.assertEquals(facade.getBookedTickets(eventToBeAdded1, Integer.MAX_VALUE, 1), tickets);
    }

    @Test
    public void _006createTicketWithBookedPlace() {

        try {
            Ticket ticket = facade.bookTicket(added.getId(), eventToBeAdded1.getId(), 10, Ticket.Category.BAR);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }


    @Test
    public void _007cancelTicket() {

        /*now just add to second event some tickets*/
        for (int i = 11; i < 22; i++) {
            /*create tickets on last event with added user in method _001*/
            facade.bookTicket(added.getId(), eventToBeAdded2.getId(), i, Ticket.Category.PREMIUM);
        }

        /*list must be returned in desc order of its event, so first ticket in list eventId must be same with eventToBeAdded2.id*/
        List<Ticket> ticketsInDescOrder = facade.getBookedTickets(added, Integer.MAX_VALUE, 1);
        Ticket first = ticketsInDescOrder.get(0);
        Ticket last = ticketsInDescOrder.get(ticketsInDescOrder.size() - 1);
        Assert.assertTrue(first.getEventId() == eventToBeAdded2.getId());
        Assert.assertTrue(last.getEventId() == eventToBeAdded1.getId());


        int lastId = (int) last.getId();
        /*cancell last ticket*/
        facade.cancelTicket(lastId);

        System.out.println(last);

        Assert.assertTrue(!facade.getBookedTickets(added, Integer.MAX_VALUE, 1).contains(last));
    }


}
