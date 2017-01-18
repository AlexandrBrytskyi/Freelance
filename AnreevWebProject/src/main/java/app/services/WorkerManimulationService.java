package app.services;


import app.model.Posada;

import java.util.List;

public interface WorkerManimulationService {


    boolean addPosadaToExistingUser(String token,
                                    int posadaId,
                                    int userId);

    boolean fireUser(String token,
                     int userId,
                     int posadaId);


    boolean growSalary(String token,
                       int userId,
                       int posadaId,
                       String description,
                       double growVal);

    boolean dischargeSalary(String token,
                            int userId,
                            int posadaId,
                            String description,
                            double dischargeVal);


    List<Posada> getWorkOfOtdel(int otdel);
}
