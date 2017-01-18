package jdbcapp.qurryExecutors;


import jdbcapp.model.NameValuesEntry;
import jdbcapp.model.ResultSetData;

import java.sql.*;

public class MySqlQuerryExecutor implements QueryExecutor {

    private Connection connection;

    public MySqlQuerryExecutor() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public boolean reconnect(String url, String user, String pass) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = DriverManager.getConnection(url, user, pass);
        return true;
    }

    @Override
    public ResultSetData executeQuery(String querry, int maxRows) throws Exception {
        ResultSetData toReturn = new ResultSetData();
        try (Statement statement = connection.createStatement()) {
            if (maxRows > 0) statement.setMaxRows(maxRows);
            try (ResultSet rs = statement.executeQuery(querry)) {
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i < meta.getColumnCount() + 1; i++) {
                    toReturn.getTable().add(new NameValuesEntry(meta.getColumnName(i), meta.getColumnType(i)));
                }
                while (rs.next()) {
                    for (int i = 0; i < toReturn.getTable().size(); i++) {
                        toReturn.getTable().get(i).getValues().add(rs.getString(i+1));
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public boolean executeUpdate(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql) == 1 ? true : false;
        }
    }
}
