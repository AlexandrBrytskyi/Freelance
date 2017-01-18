package jdbcapp.qurryExecutors;


import jdbcapp.model.ResultSetData;

import java.sql.SQLException;
import java.util.Map;

public interface QueryExecutor {

    boolean connect(String url, String user, String pass) throws Exception;

    ResultSetData executeQuery(String tableName, int maxRows) throws Exception;

    boolean executeAdd(Map<String, String> parameters) throws SQLException, Exception;

    boolean executeUpdate(Map<String, String> parameters, String idFieldName, int id) throws Exception;


    boolean executeRemove(String table, String idFieldName, int id) throws Exception;
}
