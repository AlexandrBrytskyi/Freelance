package app.dao;


import app.model.Otdel;
import app.model.Posada;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Component
@Transactional
public class PosadaDaoImpl implements PosadaDao {

    @PersistenceContext
    private EntityManager em;

    public Posada add(Posada posada) {
        em.persist(posada);
        return posada;
    }

    public boolean remove(int id) {
        em.remove(em.find(Posada.class, id));
        return true;
    }

    public Posada getById(int id) {
        return em.find(Posada.class, id);
    }

    public Posada update(Posada posada) {
        return em.merge(posada);
    }

    @Override
    public List<Posada> getFree(int otdel) {
        List<Posada> res = new LinkedList<>();
        return em.find(Otdel.class, otdel).getPosadasInOtdel();
    }
}
