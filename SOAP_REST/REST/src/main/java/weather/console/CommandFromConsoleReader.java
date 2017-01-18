package weather.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import weather.console.commands.Get16DayForecastCommand;
import weather.console.commands.Get5DayForecastCommand;
import weather.console.commands.GetCurrentWeatherCommand;
import weather.console.commands.ICommand;
import weather.console.exceptions.WrongCommandException;
import weather.console.exceptions.WrongNumberException;

import java.util.Scanner;

@Component
public class CommandFromConsoleReader {

    @Autowired
    private RestTemplate restTemplate;

    private Scanner consoleReader;

    private CommandFromConsoleReader() {
        this.consoleReader = new Scanner(System.in);
    }

    public ICommand readNextCommand() throws WrongCommandException, WrongNumberException, NumberFormatException {
        System.out.println("Enter command");
        String typed = consoleReader.next();
        switch (typed) {
            case "-c": {
                return new GetCurrentWeatherCommand(readParam("What city?"), restTemplate);
            }
            case "-5": {
                return new Get5DayForecastCommand(readParam("What city?"), restTemplate);
            }
            case "-16": {
                return new Get16DayForecastCommand(readParam("What city?"), Integer.valueOf(readParam("How many days forecast do you need?")), restTemplate);
            }
            default: {
                throw new WrongCommandException("Wrong command typed");
            }
        }
    }

    /*param 'message' notify user what he should enter*/
    public String readParam(String message) {
        System.out.println(message);
        String typed = consoleReader.next();
        return typed;
    }

}
