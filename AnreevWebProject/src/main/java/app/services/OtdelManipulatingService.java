package app.services;


import app.model.Otdel;

public interface OtdelManipulatingService {

    Otdel createOtdel(String token,
                      String description,
                      String otdelName);


    Otdel creatPodeOtdel(String token,
                         String description,
                         String otdelName,
                         int parentOtdel);

    Otdel setMentor(String token, int otdel, int mentor);

    Otdel getOtdel(int otdelId);
}
