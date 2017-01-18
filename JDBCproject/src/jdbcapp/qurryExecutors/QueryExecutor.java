package jdbcapp.qurryExecutors;


import jdbcapp.model.ResultSetData;

import java.sql.SQLException;

public interface QueryExecutor {

    boolean reconnect(String url, String user, String pass) throws Exception;

    ResultSetData executeQuery(String querry, int maxRows) throws Exception;

    boolean executeUpdate(String statement) throws Exception;


}
