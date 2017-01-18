package app.dao;


import app.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @PersistenceContext()
    private EntityManager em;


    public User add(User user) {
//        em.persist(user.getWorkHistory());
        em.persist(user);
        return user;
    }

    public boolean remove(int id) {
        em.remove(em.find(User.class, id));
        return true;
    }

    public User update(User user) {
//        em.merge(user.getWorkHistory());
        return em.merge(user);
    }

    public User getUser(int id) {
        return em.find(User.class, id);
    }
}
