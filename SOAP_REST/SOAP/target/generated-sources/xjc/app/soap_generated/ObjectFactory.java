//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.08 at 03:50:09 PM EET 
//


package app.soap_generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the app.soap_generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://teletextapi.org/", "string");
    private final static QName _ArrayOfChannel_QNAME = new QName("http://teletextapi.org/", "ArrayOfChannel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: app.soap_generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShowChannel }
     * 
     */
    public ShowChannel createShowChannel() {
        return new ShowChannel();
    }

    /**
     * Create an instance of {@link ShowChannelResponse }
     * 
     */
    public ShowChannelResponse createShowChannelResponse() {
        return new ShowChannelResponse();
    }

    /**
     * Create an instance of {@link ListChannels }
     * 
     */
    public ListChannels createListChannels() {
        return new ListChannels();
    }

    /**
     * Create an instance of {@link ListChannelsResponse }
     * 
     */
    public ListChannelsResponse createListChannelsResponse() {
        return new ListChannelsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfChannel }
     * 
     */
    public ArrayOfChannel createArrayOfChannel() {
        return new ArrayOfChannel();
    }

    /**
     * Create an instance of {@link Channel }
     * 
     */
    public Channel createChannel() {
        return new Channel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teletextapi.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teletextapi.org/", name = "ArrayOfChannel")
    public JAXBElement<ArrayOfChannel> createArrayOfChannel(ArrayOfChannel value) {
        return new JAXBElement<ArrayOfChannel>(_ArrayOfChannel_QNAME, ArrayOfChannel.class, null, value);
    }

}