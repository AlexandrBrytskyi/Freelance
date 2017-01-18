package app.client;



import app.soap_generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

public class TeletextService extends WebServiceGatewaySupport implements ITeletextService {

    private ObjectFactory of;

    public static final String URI = "http://www.teletextapi.com/api.asmx";

    public TeletextService(SaajSoapMessageFactory messageFactory, ObjectFactory of) {
        super(messageFactory);
        this.of = of;
    }


    public ArrayOfChannel getListChannels() {

        ListChannels request = new ListChannels();

        ListChannelsResponse response = (ListChannelsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request);
        return response.getListChannelsResult();
    }


    public String showChannelPage(Channel channel, int page) {

        ShowChannel request = new ShowChannel();
        request.setName(channel.getName());
        request.setPageNumber(page);

        ShowChannelResponse response = (ShowChannelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request);
        return response.getShowChannelResult();
    }



}
