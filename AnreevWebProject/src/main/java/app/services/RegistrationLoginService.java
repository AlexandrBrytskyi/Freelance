package app.services;


import app.model.exceptions.UserMustRegisterException;
import app.model.exceptions.WrongDataException;

public interface RegistrationLoginService {


    String login(int userIdentifier, String pass) throws WrongDataException, UserMustRegisterException;

    String register(String token,
                    String userName,
                    String userSurname,
                    String userPhone,
                    String userAddress,
                    String userPassport,
                    byte[] avatar,
                    String password);

}
