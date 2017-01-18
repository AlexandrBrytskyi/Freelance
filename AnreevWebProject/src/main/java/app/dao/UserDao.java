package app.dao;


import app.model.User;
import app.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public interface UserDao {

    User add(User user);

    boolean remove(int id);

    User update(User user);

    User getUser(int id);

}
