package app.dao;


import app.dao.storage.Storage;
import app.model.User;
import app.model.UserImpl;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDao {

    private static UserDao dao;
    private static ApplicationContext context;

    @BeforeClass
    public static void initDao() {
        context = new ClassPathXmlApplicationContext("classpath:app_context.xml");
        dao = context.getBean(UserDao.class);
    }

    @AfterClass
    public static void saveMaps() {
        Storage storage = (Storage) context.getBean("storage");
        storage.saveMaps();
    }

    @Test
    public void _01add() {
        User user = new UserImpl("Vasia", "sdsds@dfdf");
        User user1 = new UserImpl("petia", "sdsds@dsfdf");
        Assert.assertNotNull(dao.addUser(user));
        Assert.assertNotNull(dao.addUser(user1));
    }


    @Test
    public void _02getById() {
        User newUser = new UserImpl("PPPSDP", "sdsdsdsd");
        User added = dao.addUser(newUser);
        long id = added.getId();

        User founded = dao.getUserById(id);

        Assert.assertEquals(added, founded);
    }

    @Test
    public void _03getUsersByName() {

        List<User> users = new ArrayList<>();
        String name = String.valueOf(System.currentTimeMillis());
        for (int i = 0; i < 12; i++) {
            users.add(new UserImpl(name, "sdsd"));
        }

        for (User user : users) {
            dao.addUser(user);
        }

        List<User> res = dao.getUsersByName(name, 8, 2);
        System.out.println(res);

        Assert.assertTrue(res.size() == 4);
    }

    @Test
    public void _04getUserByEmail() {
        String email = String.valueOf(System.currentTimeMillis());
        User user = new UserImpl(String.valueOf(System.currentTimeMillis()), email);

        dao.addUser(user);

        User founded = dao.getUserByEmail(email);

        Assert.assertEquals(user, founded);
    }

    @Test
    public void _05updateUser() {
        String email = String.valueOf(System.currentTimeMillis());
        User user = new UserImpl(String.valueOf(System.currentTimeMillis()), email);
        dao.addUser(user);
        User founded = dao.getUserByEmail(email);

        founded.setEmail("new Email");
        long id = founded.getId();

        dao.updateUser(founded);


        Assert.assertEquals(dao.getUserById(id), founded);
    }

    @Test
    public void _06deleteUser() {
        User neww = new UserImpl("vads", "sddsd");
        long id = dao.addUser(neww).getId();

        dao.deleteUser(id);

        Assert.assertNull(dao.getUserById(id));
    }

}
