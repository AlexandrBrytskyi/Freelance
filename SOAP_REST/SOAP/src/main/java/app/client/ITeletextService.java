package app.client;


import app.soap_generated.ArrayOfChannel;
import app.soap_generated.Channel;

public interface ITeletextService {

    /*get all available teletext channels*/
    ArrayOfChannel getListChannels();

    /*return link on teletext page of channel*/
    String showChannelPage(Channel channel, int page);
}
