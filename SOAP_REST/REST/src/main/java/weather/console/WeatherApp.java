package weather.console;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weather.console.commands.ICommand;
import weather.console.exceptions.WrongCommandException;
import weather.console.exceptions.WrongNumberException;

@Component
public class WeatherApp {

    @Autowired
    private CommandFromConsoleReader commandFromConsoleReader;
    @Autowired
    private CommandExecutor executor;

    public void start() {
        showGreeting();
        while (true) {
            ICommand entered = showMessageParseCommand();
            executor.executeCommand(entered);
        }
    }

    private ICommand showMessageParseCommand() {
        try {
            return commandFromConsoleReader.readNextCommand();
        } catch (WrongCommandException e) {
            System.out.println(e.getMessage());
            return showMessageParseCommand();
        } catch (WrongNumberException e) {
            System.out.println(e.getMessage());
            return showMessageParseCommand();
        }
    }

    private void showGreeting() {
        System.out.println("Hello in weather app\ncommands: \n-c current weather\n" +
                "-5 5 days/3 hours forecast\n-16 16 days forecast");
    }


}
