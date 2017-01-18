package weather.console.commands;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import weather.Application;
import weather.model.current_weather.CurrentWeather;

public class GetCurrentWeatherCommand implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(GetCurrentWeatherCommand.class);

    private String place;
    private RestTemplate restTemplate;

    public GetCurrentWeatherCommand(String place, RestTemplate restTemplate) {
        this.place = place;
        this.restTemplate = restTemplate;
    }

    @Override
    public String execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org")
                .path("data/2.5/weather")
                .queryParam("q", place)
                .queryParam("units", "metric")
                .queryParam("appid", Application.APIKEY);

//        http://api.openweathermap.org/data/2.5/weather/q=Kyiv&units=metric&appid=9ff726dd7d43b4eab9c24ba2689b30b4

        try {
            CurrentWeather currentWeather = restTemplate.getForObject(builder.toUriString(), CurrentWeather.class);
            log.info(currentWeather.toString());
            return currentWeather.toString();
        } catch (Exception e) {
            log.error("Error while GET _5day_forecast", e);
            return null;
        }
    }
}
