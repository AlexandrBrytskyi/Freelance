package app.services;

import app.dao.OtdelDao;
import app.dao.PosadaDao;
import app.dao.UserDao;
import app.model.*;
import app.services.tokens.TokensContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class WorkerManipulationServiceImpl implements WorkerManimulationService {

    @Autowired
    private TokensContext tokensContext;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PosadaDao posadaDao;

    @Autowired
    private OtdelDao otdelDao;


    public boolean addPosadaToExistingUser(String token, int posadaId, int userId) {
        User user = tokensContext.getTokens().get(token);
        if (user == null) throw new IllegalArgumentException("Wrong token");

        Posada posada = posadaDao.getById(posadaId);
        User userToAdd = userDao.getUser(userId);

        userToAdd.getPosadas().add(posada);
        posada.setUserWorking(userToAdd);
        WorkHistory workHistory = userToAdd.getWorkHistory();
        workHistory.getChangesInPosada().add(new ChangesInPosada(workHistory, null, posada, "Taken on work", new Date()));
        workHistory.getChangesInWork().add(new ChangesInSalary(workHistory, 0, posada.getSalary(), posada.getSalary(), new Date(), "Taken on work"));

        userToAdd = userDao.update(userToAdd);
        posadaDao.update(posada);

        return true;
    }

    public boolean fireUser(String token, int userId, int posadaId) {
        User user = tokensContext.getTokens().get(token);
        if (user == null) throw new IllegalArgumentException("Wrong token");


        Posada posada = posadaDao.getById(posadaId);
        User userToFire = userDao.getUser(userId);

        user.getPosadas().remove(posada);
        WorkHistory workHistory = userToFire.getWorkHistory();
        workHistory.getChangesInPosada().add(
                new ChangesInPosada(workHistory, posada, null, "Fired", new Date()));
        workHistory.getChangesInWork().add(
                new ChangesInSalary(workHistory, posada.getSalary(), posada.getSalary(), 0, new Date(), "Fired"));

        posada.setUserWorking(null);

        posadaDao.update(posada);

        userDao.update(userToFire);

        return true;
    }

    public boolean growSalary(String token, int userId, int posadaId, String description, double growVal) {
        User user = tokensContext.getTokens().get(token);
        if (user == null) throw new IllegalArgumentException("Wrong token");


        Posada posada = posadaDao.getById(posadaId);
        User userToGrow = userDao.getUser(userId);

        double oldSalary = posada.getSalary();
        posada.setSalary(oldSalary + growVal);

        WorkHistory workHistory = userToGrow.getWorkHistory();
        workHistory.getChangesInWork().add(
                new ChangesInSalary(workHistory, oldSalary, growVal, posada.getSalary(), new Date(), "Growed " + description));

        posadaDao.update(posada);
       User upd = userDao.update(userToGrow);

        return true;
    }

    public boolean dischargeSalary(String token, int userId, int posadaId, String description, double disCharge) {
        User user = tokensContext.getTokens().get(token);
        if (user == null) throw new IllegalArgumentException("Wrong token");


        Posada posada = posadaDao.getById(posadaId);
        User userToDischarge = userDao.getUser(userId);

        double oldSalary = posada.getSalary();
        posada.setSalary(oldSalary - disCharge);

        WorkHistory workHistory = userToDischarge.getWorkHistory();
        workHistory.getChangesInWork().add(
                new ChangesInSalary(workHistory, oldSalary, disCharge, posada.getSalary(), new Date(), "Growed salary in posada " + posada.getPosadaName()));


        posadaDao.update(posada);
        userDao.update(userToDischarge);

        return true;
    }

    @Override
    public List<Posada> getWorkOfOtdel(int otdel) {
        return posadaDao.getFree(otdel);
    }

}
