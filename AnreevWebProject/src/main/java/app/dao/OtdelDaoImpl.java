package app.dao;


import app.model.Otdel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class OtdelDaoImpl implements OtdelDao {

    @PersistenceContext
    private EntityManager em;

    public Otdel add(Otdel otdel) {
        em.persist(otdel);
        return otdel;
    }

    public boolean remove(int id) {
        em.remove(em.find(Otdel.class, id));
        return true;
    }

    public Otdel getById(int id) {
        return em.find(Otdel.class, id);
    }

    public Otdel update(Otdel otdel) {
        return em.merge(otdel);
    }
}
