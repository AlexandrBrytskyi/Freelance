package jdbcapp;


import jdbcapp.gui.GUI;
import jdbcapp.model.ResultSetData;
import jdbcapp.qurryExecutors.SqlServerQuerryExecutor;
import jdbcapp.qurryExecutors.QueryExecutor;

import java.sql.Types;
import java.util.Map;

public class Controller {

    private GUI gui;
    private QueryExecutor queryExecutor;
    private ResultSetData lastReturned;


    public Controller() {
        gui = new GUI(this);
        queryExecutor = new SqlServerQuerryExecutor();
    }


    public boolean addEntity(Map<String, String> parameters) throws Exception {
        return queryExecutor.executeAdd(parameters);
    }


    public ResultSetData executeShow(String table, int maxResults) throws Exception {
        lastReturned = queryExecutor.executeQuery(table, maxResults);
        return lastReturned;
    }


    public boolean updateEntity(Map<String, String> parameters, String idFieldName, Integer id) throws Exception {
        return queryExecutor.executeUpdate(parameters, idFieldName, id);
    }

    public boolean removeEntity(String table, String idFieldName, int id) throws Exception {
        return queryExecutor.executeRemove(table, idFieldName, id);
    }

    public String[] getTableFields(String tableName) {
        try {
            ResultSetData resultSetData = queryExecutor.executeQuery(tableName, 1);
            String[] res = new String[resultSetData.getTable().size()];
            for (int i = 0; i < resultSetData.getTable().size(); i++) {
                res[i] = resultSetData.getTable().get(i).getName();
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isNull(int type) {
        return (type == Types.NULL);
    }


    public boolean isNumber(int type) {
        if (type == Types.BIGINT || type == Types.DECIMAL || type == Types.DOUBLE
                || type == Types.FLOAT || type == Types.NUMERIC || type == Types.INTEGER) return true;
        return false;
    }

    public ResultSetData getLastReturned() {
        return lastReturned;
    }

    public int defineColumnType(int column) {
        int type = lastReturned.getTable().get(column).getType();
        if (isNull(type)) return -1;
        if (isNumber(type)) return 1;
        return 0;
    }

    public boolean reconnect(String url, String user, String pass) throws Exception {
        return queryExecutor.connect(url, user, pass);
    }
}
