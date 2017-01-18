package weather.model._16dayForecast;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import weather.model._5day_forecast.City;


@JsonIgnoreProperties(ignoreUnknown = true)
public class _16DayForecast {

    private int cod;
    private double message;
    private double cnt;
    private java.util.List<List> list;
    private City city;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public double getCnt() {
        return cnt;
    }

    public void setCnt(double cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "_16DayForecast{" +
                "server response=" + cod +
                ", generation time=" + message +
                ", cnt=" + cnt +
                ", lists=" + list +
                ", city=" + city +
                '}';
    }
}
