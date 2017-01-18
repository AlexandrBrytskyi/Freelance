package app.dao;


import app.dao.storage.Storage;
import app.model.Event;
import app.model.EventImpl;
import app.model.User;
import app.model.UserImpl;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEventDao {

    private static EventDao dao;
    private static ApplicationContext context;

    @BeforeClass
    public static void initDao() {
        context = new ClassPathXmlApplicationContext("classpath:app_context.xml");
        dao = context.getBean(EventDao.class);
    }

    @AfterClass
    public static void saveMaps() {
        Storage storage = (Storage) context.getBean("storage");
        storage.saveMaps();
    }

    @Test
    public void _01addGetById() {
        Event event = new EventImpl("title", new Date());
        long id = dao.addEvent(event).getId();

        Event founded = dao.getEventById((int) id);
        Assert.assertNotNull(founded);
    }


    @Test
    public void _03getByDay() {
        List<Event> events;

        events = dao.getEventsForDay(new Date(), 10, 1);

        System.out.println(events);

        Assert.assertTrue(events.size() != 0);
    }

    @Test
    public void _05update() {
        Event event = new EventImpl("title", new Date());
        long id = dao.addEvent(event).getId();

        event.setTitle("updated");

        Event updated = dao.updateEvent(event);

        Event founded = dao.getEventById((int) id);
        Assert.assertNotNull(founded);

        Assert.assertEquals(updated, founded);
    }

    @Test
    public void _06delete() {
        Event neww = new EventImpl("vads", new Date());
        long id = dao.addEvent(neww).getId();

        dao.deleteEvent(id);

        Assert.assertNull(dao.getEventById((int) id));
    }

}
