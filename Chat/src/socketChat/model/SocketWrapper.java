package socketChat.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by alexandr on 17.10.16.
 */
public class SocketWrapper {

    private Socket socket;
    private InputStream is;
    private ObjectOutputStream os;
    private String nameOfUser;

    public SocketWrapper(Socket socket, String nameOfUser) throws IOException {
        this.socket = socket;
        is = socket.getInputStream();
        os = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public InputStream getIs() {
        return is;
    }

    public ObjectOutputStream getOs() {
        return os;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketWrapper that = (SocketWrapper) o;

        return socket != null ? socket.equals(that.socket) : that.socket == null;

    }

    @Override
    public int hashCode() {
        return socket != null ? socket.hashCode() : 0;
    }

    @Override
    public String toString() {
        return nameOfUser;
    }
}
