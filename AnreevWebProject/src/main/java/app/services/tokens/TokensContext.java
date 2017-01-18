package app.services.tokens;


import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component()
public class TokensContext {

    @Autowired
    private UserDao userDao;


    private ConcurrentHashMap<String, User> tokens;


    private TokensContext() {
        initTokensMap();
    }

    private void initTokensMap() {
        tokens = new ConcurrentHashMap<String, User>();
    }

    public ConcurrentHashMap<String, User> getTokens() {
        return tokens;
    }

}
