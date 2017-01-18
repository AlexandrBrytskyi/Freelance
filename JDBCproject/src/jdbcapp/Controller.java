package jdbcapp;


import jdbcapp.gui.GUI;
import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;
import jdbcapp.qurryExecutors.MySqlQuerryExecutor;
import jdbcapp.qurryExecutors.QueryExecutor;

import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {

    private GUI gui;
    private QueryExecutor queryExecutor;
    private ResultSetData lastreturned;


    public Controller() {
        gui = new GUI(this);
        queryExecutor = new MySqlQuerryExecutor();
    }

    public Object execute(String querry, int maxResults) throws Exception {
        if (querry.toUpperCase().contains("SELECT")) {
            return doExecuteQuerry(querry, maxResults);
        } else {
            return doExecuteUpdate(querry);
        }
    }

    private boolean doExecuteUpdate(String statement) throws Exception {
        return queryExecutor.executeUpdate(statement);
    }

    private ResultSetData doExecuteQuerry(String querry, int maxResults) throws Exception {
        lastreturned = queryExecutor.executeQuery(querry, maxResults);
        return lastreturned;
    }

    public boolean reconnect(String url, String userFieldText, String passFieldText) throws Exception {
        return queryExecutor.reconnect(url, userFieldText, passFieldText);
    }

    public String countFunction(String operationName, String column) throws Exception {
        NameValuesEntry columnWithValues = lastreturned.getTable().get(getIndex(column));
        switch (operationName) {
            case "MIN": {
                return Collections.min(columnWithValues.getValues(), comparator).toString();
            }
            case "MAX": {
                return Collections.max(columnWithValues.getValues(), comparator).toString();
            }
            case "AVG": {
                if (isNumber(columnWithValues.getType())) return findAVG(columnWithValues.getValues());
                throw new Exception("Not a number to count avg");
            }
            case "MEDIAN": {
                int index = columnWithValues.getValues().size() / 2 + 1;
                if (isString(columnWithValues.getType()))
                    return "Illegal";
                return columnWithValues.getValues().get(index).toString();
            }
            default:
                return "No result";
        }
    }

    private Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            try {
                Double obj1 = Double.valueOf(o1);
                Double obj2 = Double.valueOf(o2);
                return obj1.compareTo(obj2);
            } catch (Exception e) {
                try {
                    return o1.compareTo(o2);
                } catch (NullPointerException e1) {
                    return 0;
                }
            }
        }
    };

    private int getIndex(String column) {
        for (int i = 0; i < lastreturned.getTable().size(); i++) {
            if (lastreturned.getTable().get(i).getName().equals(column)) return i;
        }
        return -1;
    }

    public boolean isString(int type) {
        if (type == Types.VARCHAR || type == Types.LONGNVARCHAR || type == Types.LONGVARCHAR ||
                type == Types.NVARCHAR ) return true;
        return false;
    }

    private String findAVG(List<String> values) {
        DecimalFormat format = new DecimalFormat("0.00");
        Double sum = 0.0;
        for (String value : values) {
            try {
                sum += Double.valueOf(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return format.format(sum / values.size());
    }

    public boolean isNull(int type) {
        return (type == Types.NULL);
    }


    public boolean isNumber(int type) {
        if (type == Types.BIGINT || type == Types.DECIMAL || type == Types.DOUBLE
                || type == Types.FLOAT || type == Types.NUMERIC || type == Types.INTEGER) return true;
        return false;
    }

    public ResultSetData getLastreturned() {
        return lastreturned;
    }


    public int defineColumnType(int column) {
        int type = lastreturned.getTable().get(column).getType();
        if (isNull(type)) return -1;
        if (isNumber(type)) return 1;
        return 0;
    }
}
