package daos;

import app.dao.OtdelDao;
import app.dao.UserDao;
import app.model.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;

@ContextConfiguration(locations = "classpath:app_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    OtdelDao otdelDao;

    static Otdel otdel;

    @Test
    public void _001add() {
        otdel = new Otdel("vasia", "asasa", new LinkedList<Posada>(), new LinkedList<Otdel>(), null, null);
        otdelDao.add(otdel);

        User user = new User(
                new UserInfo(),
                new byte[2],
                otdel,
                new LinkedList<Posada>(),
                new WorkHistory(new LinkedList<ChangesInSalary>(), new LinkedList<ChangesInPosada>()),
                false, null);

        userDao.add(user);
    }


}
