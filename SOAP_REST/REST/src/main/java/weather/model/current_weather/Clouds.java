package weather.model.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*
* "clouds":{"all":92},*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {

    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }

}
