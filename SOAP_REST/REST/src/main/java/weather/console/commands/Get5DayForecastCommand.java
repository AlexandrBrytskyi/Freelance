package weather.console.commands;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import weather.Application;
import weather.model._5day_forecast._5DayForecast;
import weather.model.current_weather.CurrentWeather;

public class Get5DayForecastCommand implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(Get5DayForecastCommand.class);

    private String place;
    private RestTemplate restTemplate;

    public Get5DayForecastCommand(String place, RestTemplate restTemplate) {
        this.place = place;
        this.restTemplate = restTemplate;
    }

    @Override
    public String execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org")
                .path("data/2.5/forecast")
                .queryParam("q", place)
                .queryParam("units", "metric")
                .queryParam("mode", "json")
                .queryParam("appid", Application.APIKEY);
        try {
            _5DayForecast forecast = restTemplate.getForObject(builder.toUriString(), _5DayForecast.class);
            String res = forecast.toString();
            log.info(res);
            return res;
        } catch (Exception e) {
            log.error("Error while GET _5day_forecast", e);
            return null;
        }

    }
}
