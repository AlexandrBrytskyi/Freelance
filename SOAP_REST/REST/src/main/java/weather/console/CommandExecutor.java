package weather.console;

import org.springframework.stereotype.Component;
import weather.console.commands.ICommand;

@Component
public class CommandExecutor {

    public void executeCommand(ICommand command) {
        System.out.println("Command executed, result is " + command.execute());
    }


}