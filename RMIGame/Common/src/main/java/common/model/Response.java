package common.model;


import java.io.Serializable;
import java.util.Arrays;

public class Response implements Serializable {


    private boolean isWinner;
    private Integer[] winNumbers;
    private Integer[] serverNumbers;
    private String message;


    public Response() {
    }

    public Response(boolean isWinner, Integer[] winNumbers, Integer[] serverNumbers, String message) {
        this.isWinner = isWinner;
        this.winNumbers = winNumbers;
        this.serverNumbers = serverNumbers;
        this.message = message;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Integer[] getWinNumbers() {
        return winNumbers;
    }

    public void setWinNumbers(Integer[] winNumbers) {
        this.winNumbers = winNumbers;
    }

    public Integer[] getServerNumbers() {
        return serverNumbers;
    }

    public void setServerNumbers(Integer[] serverNumbers) {
        this.serverNumbers = serverNumbers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "isWinner=" + isWinner +
                ", winNumbers=" + Arrays.toString(winNumbers) +
                ", serverNumbers=" + Arrays.toString(serverNumbers) +
                ", message='" + message + '\'' +
                '}';
    }
}
