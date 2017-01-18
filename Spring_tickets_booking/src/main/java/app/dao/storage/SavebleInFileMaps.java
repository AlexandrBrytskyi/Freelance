package app.dao.storage;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SavebleInFileMaps implements Serializable {


//    private Map<Integer, User> usersMap;
//    private Map<Integer, Event> eventsMap;
//    private Map<Integer, Ticket> ticketsMap;


    private HashMap<Class, AtomicInteger> sequences;
    private HashMap<Class, HashMap> entities;


    public SavebleInFileMaps() {
    }

    public HashMap<Class, HashMap> getEntities() {
        return entities;
    }

    public void setEntities(HashMap<Class, HashMap> entities) {
        this.entities = entities;
    }

    public HashMap<Class, AtomicInteger> getSequences() {
        return sequences;
    }

    public void setSequences(HashMap<Class, AtomicInteger> sequences) {
        this.sequences = sequences;
    }

    @Override
    public String toString() {
        return "SavebleInFileMaps{" +
                "sequences=" + sequences +
                ", entities=" + entities +
                '}';
    }
}
