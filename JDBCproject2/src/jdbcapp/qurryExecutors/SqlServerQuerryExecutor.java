package jdbcapp.qurryExecutors;


import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class SqlServerQuerryExecutor implements QueryExecutor {

    private Connection connection;

    public SqlServerQuerryExecutor() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public boolean connect(String url, String user, String pass) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = DriverManager.getConnection(url, user, pass);
        return true;
    }

    @Override
    public ResultSetData executeQuery(String tableName, int maxRows) throws Exception {
        ResultSetData toReturn = new ResultSetData();
        try (Statement statement = connection.createStatement()) {
            if (maxRows > 0) statement.setMaxRows(maxRows);
            try (ResultSet rs = statement.executeQuery("SELECT * from " + tableName)) {
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i < meta.getColumnCount() + 1; i++) {
                    toReturn.getTable().add(new NameValuesEntry(meta.getColumnName(i), meta.getColumnType(i)));
                }
                while (rs.next()) {
                    for (int i = 0; i < toReturn.getTable().size(); i++) {
                        toReturn.getTable().get(i).getValues().add(rs.getString(i + 1));
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public boolean executeAdd(Map<String, String> parameters) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT into " + parameters.remove("table_name") + getFieldNames(parameters) +
                    " VALUES " + getValues(parameters));
            return true;
        }
    }

    @Override
    public boolean executeUpdate(Map<String, String> parameters, String idFieldName, int id) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE " + parameters.remove("table_name") + " SET " + getWhatSetting(parameters, idFieldName) +
                    " WHERE " + idFieldName + "=" + id);
            return true;
        }
    }

    @Override
    public boolean executeRemove(String table, String idFieldName, int id) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + table +
                    " WHERE " + idFieldName + "=" + id);
            return true;
        }
    }

    private String getWhatSetting(Map<String, String> parameters, String idFieldName) {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            if (!stringStringEntry.getKey().equals(idFieldName)) {
                res.append(stringStringEntry.getKey() + "=" + stringStringEntry.getValue() + ",");
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    private String getValues(Map<String, String> parameters) {
        StringBuilder values = new StringBuilder("(");
        for (String s : parameters.values()) {
            values.append(s + ",");
        }
        values = values.deleteCharAt(values.length() - 1);
        values.append(")");
        return values.toString();
    }

    private String getFieldNames(Map<String, String> parameters) {
        StringBuilder res = new StringBuilder("(");
        for (String s : parameters.keySet()) {
            res.append(s + ",");
        }
        res = res.deleteCharAt(res.length() - 1);
        res.append(")");
        return res.toString();
    }
}
