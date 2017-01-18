package jdbcapp.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultSetData {
    private List<NameValuesEntry> table;

    public ResultSetData() {
        table = new LinkedList<>();
    }

    public List<NameValuesEntry> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "ResultSetData{" +
                "table=" + table +
                '}';
    }
}
