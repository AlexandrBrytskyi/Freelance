package app.services;


import app.dao.UserDao;
import app.model.User;
import app.model.UserInfo;
import app.model.exceptions.UserMustRegisterException;
import app.model.exceptions.WrongDataException;
import app.services.tokens.TokenGenerator;
import app.services.tokens.TokensContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationLoginServiceImpl implements RegistrationLoginService {


    @Autowired
    private TokensContext tokensContext;


    @Autowired
    private UserDao userDao;


    public String login(int userIdentifier, String pass) throws WrongDataException, UserMustRegisterException {


        User user = userDao.getUser(userIdentifier);
        if (user == null) throw new WrongDataException("Wrong Login");

        String token = TokenGenerator.getToken();
        if (user.getPassword() != null) {
            if (!user.getPassword().equals(pass)) throw new WrongDataException("Wrong password");
            tokensContext.getTokens().put(token, user);
            return token;
        } else {
            tokensContext.getTokens().put(token, user);
            throw new UserMustRegisterException(" " + user.getUserPersonalNum(), token);
        }

    }

    public String register(String token,
                           String userName, String userSurname, String userPhone,
                           String userAddress, String userPassport, byte[] avatar,
                           String password) {

        User user = tokensContext.getTokens().get(token);
        if (user == null) throw new IllegalArgumentException("wrong token");
        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserSurname(userSurname);
        userInfo.setUserAddress(userAddress);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserPassport(userPassport);
        user.setPassword(password);
        user.setAvatar(avatar);

        userDao.update(user);

        return token;
    }
}
