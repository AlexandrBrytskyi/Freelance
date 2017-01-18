package app.client;


import app.client.console.ConsoleClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);

        ConsoleClient client = applicationContext.getBean(ConsoleClient.class);

        client.start();
    }


}