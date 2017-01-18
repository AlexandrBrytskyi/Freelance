package app.services;


import app.dao.OtdelDao;
import app.dao.UserDao;
import app.model.Otdel;
import app.model.Posada;
import app.model.User;
import app.services.tokens.TokensContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class OtdelManipulationServiceImpl implements OtdelManipulatingService {

    @Autowired
    private OtdelDao otdelDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokensContext tokensContext;


    public Otdel createOtdel(String token, String description, String otdelName) {

        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Illegal token");

        Otdel otdel = new Otdel(otdelName, description, new LinkedList<Posada>(), new LinkedList<Otdel>(), null, user);

        otdel = otdelDao.add(otdel);

        return otdel;
    }

    public Otdel creatPodeOtdel(String token, String description, String otdelName, int parentOtdel) {

        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Illegal token");

        if (otdelDao.getById(parentOtdel).getMentor().getUserPersonalNum() != user.getUserPersonalNum())
            throw new IllegalArgumentException("User cant create podotdel for this otdel");

        Otdel otdel = new Otdel(otdelName, description, new LinkedList<Posada>(), new LinkedList<Otdel>(), otdelDao.getById(parentOtdel), user);

        return otdelDao.add(otdel);
    }

    public Otdel setMentor(String token, int otdel, int mentor) {

        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Illegal token");

        if (!user.getOtdel().getMentor().equals(user)) throw new IllegalArgumentException("You do not have access");
        User newMentor = userDao.getUser(mentor);
        if (!newMentor.isCanCreateOtdel()) throw new IllegalArgumentException("This user can not ne mentor");

        Otdel otdelToChange = otdelDao.getById(otdel);
        otdelToChange.setMentor(newMentor);

        return otdelDao.update(otdelToChange);
    }

    @Override
    public Otdel getOtdel(int otdelId) {
        return otdelDao.getById(otdelId);
    }


}
