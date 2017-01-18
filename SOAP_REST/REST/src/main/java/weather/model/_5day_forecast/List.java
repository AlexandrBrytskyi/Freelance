package weather.model._5day_forecast;


/*"list": [
    {
      "dt": 1483887600,
      "main": {
        "temp": 263.05,
        "temp_min": 258.083,
        "temp_max": 263.05,
        "pressure": 1017.13,
        "sea_level": 1055.77,
        "grnd_level": 1017.13,
        "humidity": 77,
        "temp_kf": 4.97
      },
      "weather": [
        {
          "id": 800,
          "main": "Clear",
          "description": "clear sky",
          "icon": "01d"
        }
      ],
      "clouds": {
        "all": 0
      },
      "wind": {
        "speed": 4.46,
        "deg": 279.5
      },
      "snow": {},
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2017-01-08 15:00:00"
    },...*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import weather.model.current_weather.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class List {

    private long dt;
    private Main main;
    private java.util.List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    //    private Snow snow;
    private Sys sys;
    private String dt_txt;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "List{" +
                "dt=" + dt +
                ", main=" + main +
                ", weathers=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", dt_txt='" + dt_txt + '\'' +
                '}' + "\n";
    }
}
