package app.client.console;

import app.client.ITeletextService;
import app.soap_generated.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleClient {

    @Autowired
    ITeletextService teletextService;

    private List<Channel> channels;

    public ConsoleClient() {
        sc = new Scanner(System.in);
    }

    private Scanner sc;

    public void start() {
        System.out.println("Hi in teletext soap API\navailable channels");
        channels = teletextService.getListChannels().getChannel();
        printChannels();
        while (true) {
            makeDialog();
        }
    }

    private void makeDialog() {
        System.out.println("enter number of channel and number of teletext page to watch result\n Example: 0 100\nwhere 0 = channel number, 100 = page");
        String input = sc.nextLine();
        try {
            String[] strings = input.split(" ");
            int channelNum = Integer.parseInt(strings[0]);
            int page = Integer.parseInt(strings[1]);
            System.out.println(teletextService.showChannelPage(channels.get(channelNum), page));
        } catch (Exception e) {
            tryDialogAgain(e.getMessage());
        }
    }

    private void tryDialogAgain(String message) {
        System.out.println("Wrong input\n" + message);
        sc = new Scanner(System.in);
        makeDialog();
    }

    private void printChannels() {
        for (int i = 0; i < channels.size(); i++) {
            System.out.println("# " + i + ", " + channels.get(i));
        }
    }


}
