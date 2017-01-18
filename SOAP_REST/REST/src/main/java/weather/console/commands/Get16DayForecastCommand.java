package weather.console.commands;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import weather.Application;
import weather.console.exceptions.WrongNumberException;
import weather.model._16dayForecast._16DayForecast;

public class Get16DayForecastCommand implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(Get16DayForecastCommand.class);

    private String place;
    private int numOfDays;
    private RestTemplate restTemplate;

    public Get16DayForecastCommand(String place, int numOfDays, RestTemplate restTemplate) throws WrongNumberException {
        this.place = place;
        if (numOfDays <= 0 || numOfDays > 16) throw new WrongNumberException("Number must be between 1 and 16");
        this.restTemplate = restTemplate;
    }

    @Override
    public String execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org")
                .path("data/2.5/forecast/daily")
                .queryParam("q", place)
                .queryParam("mode", "json")
                .queryParam("units", "metric")
                .queryParam("cnt", numOfDays) //from 1 to 16
                .queryParam("appid", Application.APIKEY);
        try {
            _16DayForecast forecast = restTemplate.getForObject(builder.toUriString(), _16DayForecast.class);
            String res = forecast.toString();
            log.info(res);
            return res;
        } catch (Exception e) {
            log.error("Error while GET _16day_forecast", e);
            return null;
        }

    }
}
