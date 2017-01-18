package app.dao;


import app.model.Posada;

import java.util.List;

public interface PosadaDao {


    Posada add(Posada posada);

    boolean remove(int id);

    Posada getById(int id);

    Posada update(Posada posada);

    List<Posada> getFree(int otdel);
}
