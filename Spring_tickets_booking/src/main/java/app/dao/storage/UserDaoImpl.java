package app.dao.storage;

import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {


    @Autowired
    private Storage storage;

    @Override
    public User getUserById(long userId) {
        return storage.getById(User.class, (int) userId);
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> res = storage.getWithCriteria(User.class, "email", email);
        return (res != null && !res.isEmpty()) ? res.get(0) : null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> res = storage.getWithCriteria(User.class, "name", name);
        List<User> toReturn = new ArrayList<>();
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
    public User addUser(User user) {
        return storage.add(user, User.class);
    }


    /*по суті непотрібний метод, тому, що об’єкт вже лежить у мапі*/
    @Override
    public User updateUser(User user) {
        return storage.update(user, User.class);
    }

    @Override
    public boolean deleteUser(long userId) {
        return storage.remove(User.class, (int) userId);
    }

}
