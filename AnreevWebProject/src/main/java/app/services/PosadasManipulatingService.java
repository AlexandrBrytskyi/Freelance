package app.services;


import app.model.Otdel;
import app.model.Posada;
import app.model.User;

import java.util.List;

public interface PosadasManipulatingService {

    Posada createNewPosada(String posadaName,
                           String description,
                           int posadaOtdel,
                           double posadaSalary,
                           String userToken);


    /*add user, which it can change*/
    User addNewUser(String token,
                    int otdelId,
                    boolean canCreateOtdel);




}
