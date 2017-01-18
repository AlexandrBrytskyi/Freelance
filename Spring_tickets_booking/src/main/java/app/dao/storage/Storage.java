package app.dao.storage;


import app.model.Event;
import app.model.Ticket;
import app.model.User;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static final Logger LOGGER = Logger.getLogger(Storage.class);
    private String savableMapsPath;
    private SavebleInFileMaps maps;

    public Storage() {
    }

    public void loadMaps() {
        LOGGER.info("try to load maps from " + savableMapsPath);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savableMapsPath))) {
            maps = (SavebleInFileMaps) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("No file " + savableMapsPath);
            initMapsManually();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }

        System.out.println(maps);
    }

    private void initMapsManually() {
        LOGGER.info("initializing maps manually");
        maps = new SavebleInFileMaps();
        HashMap<Class, HashMap> entities = new HashMap<>();
        maps.setEntities(entities);

        entities.put(Event.class, new HashMap<Integer, Event>());
        entities.put(Ticket.class, new HashMap<Integer, Ticket>());
        entities.put(User.class, new HashMap<Integer, User>());


        HashMap<Class, AtomicInteger> sequences = new HashMap<>();
        sequences.put(Event.class, new AtomicInteger());
        sequences.put(Ticket.class, new AtomicInteger());
        sequences.put(User.class, new AtomicInteger());
        maps.setSequences(sequences);
        saveMaps();
    }

    public void saveMaps() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savableMapsPath))) {
            oos.writeObject(maps);
            LOGGER.info("Written storage maps in file " + savableMapsPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
    }

    public <T> T add(T entity, Class<T> tClass) {

        LOGGER.info("adding " + entity);
        /*generation of id*/
        int id = getSequences().get(tClass).getAndIncrement();
        Class entClass = entity.getClass();
        try {
            Field field1 = entClass.getDeclaredField("id");
            field1.setAccessible(true);
            field1.set(entity, id);
            getSequences().get(entity.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error(e);
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            LOGGER.error(e);
            return null;
        }

        /*persisting in map*/
        maps.getEntities().get(tClass).put(id, entity);
        return entity;
    }

    public <T> T getById(Class<T> tClass, int id) {
        return (T) maps.getEntities().get(tClass).get(id);
    }

    public <T> T update(T entity, Class<T> tClass) {
        LOGGER.info("Updating entity " + entity);

        Class entClass = entity.getClass();
        int id;
        try {
            Field field = entClass.getDeclaredField("id");
            field.setAccessible(true);
            id = (int) ((long) field.get(entity));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error(e);
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            LOGGER.error(e);
            return null;
        }
        LOGGER.info("Old value " + maps.getEntities().get(tClass).get(id));
        maps.getEntities().get(tClass).replace(id, entity);
        return entity;
    }

    public <T> List<T> getWithCriteria(Class<T> tClass, String fieldName, Object value) {
        LOGGER.info("finding in " + tClass + " entities where " + fieldName + " = " + value);
        List<T> res = new LinkedList<T>();
        for (Object t : maps.getEntities().get(tClass).values()) {
            try {
                Object invoke = tClass.getDeclaredMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)).invoke(t);
                if (value instanceof Date) {
                    Date invokeDate = (Date) invoke;
                    Date valueParametrized = (Date) value;
                    if (invokeDate.getYear() == valueParametrized.getYear() &&
                            invokeDate.getMonth() == valueParametrized.getMonth() &&
                            invokeDate.getDay() == valueParametrized.getDay()) res.add((T) t);
                } else if (value instanceof String) {
                    String valueStr = (String) value;
                    String inMapVaentVal = (String) invoke;
                    if (inMapVaentVal.contains(valueStr)) res.add((T) t);
                } else if (invoke.equals(value)) {
                    res.add((T) t);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                LOGGER.error(e);
                return null;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                LOGGER.error(e);
                return null;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                LOGGER.error(e);
                return null;
            }
        }
        LOGGER.info("Founded " + res);
        return res;
    }


    public <T> boolean remove(Class<T> tClass, int id) {
        LOGGER.info("Removing entity " + tClass + " with id " + id);

        boolean res = maps.getEntities().get(tClass).remove(id) != null;
        LOGGER.info(res ? "Removed" : "not removed");
        return res;
    }


    private Map<Class, AtomicInteger> getSequences() {
        return maps.getSequences();
    }


    public void setSavableMapsPath(String savableMapsPath) {
        this.savableMapsPath = savableMapsPath;
    }
}
