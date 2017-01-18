package socketChat.controller;

import socketChat.model.SocketWrapper;
import socketChat.gui.ClientGUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {

    private String serverhost;
    private int pserverort;
    private SocketWrapper socketWrapper;
    private ClientGUI gui;


    public Client(String host, int port, String name, ClientGUI gui) throws IOException {
        this.serverhost = host;
        this.pserverort = port;
        this.gui = gui;
        Socket socket = new Socket(host, port);
        this.socketWrapper = new SocketWrapper(socket, name);
        initInputStreamListener(socketWrapper.getIs());
        sendMessage(name);
    }


    public void sendMessage(String mess) {
        Thread th = new Thread(new MessageSender(mess));
        th.start();
    }


    private void initInputStreamListener(InputStream is) {
        Thread inputThread = new Thread(new InputStreamListener(is));
        inputThread.start();
    }


    private class MessageSender implements Runnable {
        private Object mess;
        private ObjectOutputStream oos;

        public MessageSender(Object mess) {
            this.mess = mess;
            this.oos = socketWrapper.getOs();
        }

        @Override
        public void run() {
            try {
                oos.writeObject(mess);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class InputStreamListener implements Runnable {

        private InputStream ois;

        public InputStreamListener(InputStream ois) {
            this.ois = ois;
        }

        @Override
        public void run() {
            Object message = null;
            try (ObjectInputStream ois = new ObjectInputStream(this.ois)) {
                while (socketWrapper.getSocket().isConnected()) {
                    message = ois.readObject();
                    messageRecieved(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void messageRecieved(Object message) {
      if (message!=null)  gui.appendToOutput(message.toString());
    }


}
