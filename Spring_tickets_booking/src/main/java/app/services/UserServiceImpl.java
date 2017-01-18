package app.services;


import app.dao.UserDao;
import app.model.Event;
import app.model.User;
import app.model.exceptions.EmailIsNotFreeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(long userId) {
        LOGGER.info("getting user with id " + userId);
        User founded = userDao.getUserById(userId);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    @Override
    public User getUserByEmail(String email) {
        LOGGER.info("getting user with email " + email);
        User founded = userDao.getUserByEmail(email);
        LOGGER.info("Returning " + founded);
        return founded;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        LOGGER.info("getting users with name " + name + ", pageSize " + pageSize + ", pagenum " + pageNum);
        List<User> founded = userDao.getUsersByName(name, pageSize, pageNum);
        LOGGER.info("Returning " + founded);
        return founded;
    }


    @Override
    public User createUser(User user) throws EmailIsNotFreeException {
        LOGGER.info("Adding user  " + user);
        String email = user.getEmail();
        LOGGER.info("Watching either exists user with email " + email);
        User found = userDao.getUserByEmail(email);
        if (found == null) {
            LOGGER.info("Adding user to map" + user);
            User added = userDao.addUser(user);
            LOGGER.info("Added user " + added);
            return added;
        } else {
            LOGGER.info("User with such email exists :" + email);
            throw new EmailIsNotFreeException(email);
        }
    }

    @Override
    public User updateUser(User user) {
        LOGGER.info("updating user " + user);
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        LOGGER.info("removing user with id" + userId);
        boolean res = userDao.deleteUser(userId);
        LOGGER.info("result of removing is " + res);
        return res;
    }


}
