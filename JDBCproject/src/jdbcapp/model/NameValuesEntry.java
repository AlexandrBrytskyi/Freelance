package jdbcapp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*like a full column with names and values*/
public class NameValuesEntry {

    private String name;
    private int type;
    private List<String> values;

    public NameValuesEntry(String name, int type) {
        this.name = name;
        this.values = new LinkedList<>();
        this.type = type;
    }

    public String getName() {
        return name;
    }


    public List<String> getValues() {
        return values;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameValuesEntry that = (NameValuesEntry) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NameValuesEntry{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", values=" + values +
                '}';
    }
}
