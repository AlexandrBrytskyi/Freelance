package weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import weather.console.WeatherApp;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static final String APIKEY = "9ff726dd7d43b4eab9c24ba2689b30b4";

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);
        WeatherApp weatherApp = applicationContext.getBean(WeatherApp.class);
        weatherApp.start();
    }

}