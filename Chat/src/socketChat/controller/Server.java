package socketChat.controller;


import socketChat.gui.ServerGUI;
import socketChat.model.SocketWrapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private ServerSocket serverSocket;
    private List<SocketWrapper> clients;
    private int port;
    private PrintWriter historyWriter;
    private File logAppender;
    private ServerGUI gui;

    public Server(String directory, int port, ServerGUI gui) throws Exception {
        this.gui = gui;
        this.port = port;
        logAppender = new File(directory+"/" + new Date().toString());
        if (!logAppender.exists()) logAppender.createNewFile();
        historyWriter = new PrintWriter(new FileOutputStream(logAppender));
        this.serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
        Thread registerTh = new Thread(new ClientsRegister());
        registerTh.start();
    }

    private void newClientCame(SocketWrapper client) throws IOException {
        String info = makeInfo(client.getSocket());
        showGreeting(client);
        startClientLogic(client);
        sendToOther(info, client);
        clients.add(client);
    }

    private void startClientLogic(SocketWrapper client) throws IOException {
        Thread clientThread = new Thread(new ClientLogic(client));
        clientThread.start();
    }

    private void showGreeting(SocketWrapper client) throws IOException {
        String mess = "Hi, you ve connected to " + serverSocket.getInetAddress().toString() + ":" + port;
        sendMessage(mess, client);
    }


    private String makeInfo(Socket client) {
        String info = String.format("New client connected\nClient host: %s, port: %s\n", client.getInetAddress().getHostAddress(), client.getPort());
        return info;
    }

    private synchronized void sendToOther(Object message, SocketWrapper owner) throws IOException {
        String mess = message.toString();
        historyWriter.append(mess + "\n");
        historyWriter.flush();
        gui.appendToOutput(mess);
        for (SocketWrapper client : clients) {
            if (!client.equals(owner)) {
                sendMessage(message, client);
            }
        }
    }

    private void sendMessage(Object message, SocketWrapper target) throws IOException {
        Thread messageTh = new Thread(new MessageSender(message, target.getOs()));
        messageTh.start();
    }

    private class ClientLogic implements Runnable {

        private SocketWrapper owner;
        private InputStream is;

        public ClientLogic(SocketWrapper owner) throws IOException {
            this.owner = owner;
            is = owner.getIs();
        }

        @Override
        public void run() {
            Object message = null;
            try (ObjectInputStream ois = new ObjectInputStream(this.is)) {
                while (owner.getSocket().isConnected()) {
                    message = ois.readObject();
                    if (owner.getNameOfUser() == null) {
                        owner.setNameOfUser((String) message);
                    } else
                        sendToOther(owner.getNameOfUser() + ": " + message, owner);
                }
                clients.remove(owner);
                sendToOther(owner.getNameOfUser() + " have left :((", owner);
            } catch (IOException e) {
                e.printStackTrace();
                clients.remove(owner);
                try {
                    sendToOther(owner.getNameOfUser() + " has connection problem :((", owner);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    private class ClientsRegister implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    newClientCame(new SocketWrapper(client, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MessageSender implements Runnable {

        private Object message;
        private ObjectOutputStream os;

        public MessageSender(Object message, ObjectOutputStream os) throws IOException {
            this.message = message;
            this.os = os;
        }

        @Override
        public void run() {
            try {
                os.writeObject(message);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getLogAppender() {
        return logAppender;
    }
}
