package app.services;

import app.dao.UserDao;
import app.model.User;
import app.model.UserInfo;
import app.model.exceptions.WrongDataException;
import app.services.tokens.TokensContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataChangeServiceImpl implements PersonalDataChangeService {

    @Autowired
    private TokensContext tokensContext;

    @Autowired
    private UserDao userDao;

    public boolean changePersonalInfo(String token, String userName, String userSurname, String userPhone, String userAddress, String userPassport) {
        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Wrong token");

        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserSurname(userSurname);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserAddress(userAddress);
        userInfo.setUserPassport(userPassport);

        userDao.update(user);

        return true;
    }

    public boolean changePhoto(String token, byte[] newPhoto) {
        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Wrong token");

        user.setAvatar(newPhoto);
        userDao.update(user);
        return true;
    }

    public boolean changePassWord(String token, String newPass, String oldPass) throws WrongDataException {
        User user = tokensContext.getTokens().get(token);

        if (user == null) throw new IllegalArgumentException("Wrong token");

        if (!oldPass.equals(user.getPassword())) throw new WrongDataException("Wrong passport");
        user.setPassword(newPass);

        userDao.update(user);
        return true;
    }


}
