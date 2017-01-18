package app.model.exceptions;


public class UserMustRegisterException extends Throwable {

    private String token;

    public UserMustRegisterException(String message, String token) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
