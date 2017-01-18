package weather.model._16dayForecast;


/*
* "list": [
    {
      "dt": 1483876800,
      "temp": {
        "day": 282.51,
        "min": 278.16,
        "max": 282.51,
        "night": 278.16,
        "eve": 279.86,
        "morn": 282.51
      },
      "pressure": 1038.53,
      "humidity": 98,
      "weather": [
        {
          "id": 500,
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "speed": 1.96,
      "deg": 277,
      "clouds": 76,
      "rain": 0.23
    },*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import weather.model.current_weather.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class List {

    private long dt;
    private Temp temp;
    private double pressure;
    private double humidity;
    private double speed;
    private double deg;
    private java.util.List<Weather> weather;
    private double clouds;
    private double rain;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "List{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                ", deg=" + deg +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", rain=" + rain +
                '}';
    }
}
