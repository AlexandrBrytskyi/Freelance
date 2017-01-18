package app.dao;


import app.model.Otdel;

public interface OtdelDao {


    Otdel add(Otdel otdel);

    boolean remove(int id);

    Otdel getById(int id);

    Otdel update(Otdel otdel);

}
