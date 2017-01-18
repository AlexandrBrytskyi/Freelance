package weather.model._5day_forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import weather.model.current_weather.Coord;

/*"city": {
    "id": 4517009,
    "name": "London",
    "coord": {
      "lat": 39.8865,
      "lon": -83.4483
    },
    "country": "US"
  }*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    private int id;
    private String name;
    private Coord coord;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                '}';
    }
}
