package app.services;

import app.dao.OtdelDao;
import app.dao.PosadaDao;
import app.dao.UserDao;
import app.model.*;
import app.services.tokens.TokensContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PosadasManipulationServiceImpl implements PosadasManipulatingService {

    @Autowired
    private TokensContext tokensContext;

    @Autowired
    private OtdelDao otdelDao;

    @Autowired
    private PosadaDao posadaDao;

    @Autowired
    private UserDao userDao;

    public Posada createNewPosada(String posadaName, String description, int posadaOtdel, double posadaSalary, String userToken) {

        User user = tokensContext.getTokens().get(userToken);
        if (user == null) throw new IllegalArgumentException("Wrong token");

        Posada posada = new Posada(posadaName, user, null, description, otdelDao.getById(posadaOtdel), posadaSalary);

        return posadaDao.add(posada);

    }

    public User addNewUser(String token, int otdelId, boolean canCreateOtdel) {

        User whoCalls = tokensContext.getTokens().get(token);

        if (whoCalls == null) throw new IllegalArgumentException("Wrong token");

        User newUser = new User(new UserInfo(), null, otdelDao.getById(otdelId), new LinkedList<Posada>(),
                new WorkHistory(new LinkedList<ChangesInSalary>(), new LinkedList<ChangesInPosada>()),
                canCreateOtdel, null);

        newUser.getWorkHistory().setUser(newUser);

        return userDao.add(newUser);
    }
}
