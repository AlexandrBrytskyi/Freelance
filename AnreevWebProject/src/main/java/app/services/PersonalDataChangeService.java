package app.services;


import app.model.exceptions.WrongDataException;

public interface PersonalDataChangeService {

    boolean changePersonalInfo(String token,
                               String userName,
                               String userSurname,
                               String userPhone,
                               String userAddress,
                               String userPassport);

    boolean changePhoto(String token, byte[] newPhoto);

    boolean changePassWord(String token, String newPass, String oldPass) throws WrongDataException;

}
