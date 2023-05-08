package mediator;

import java.io.Serializable;

public class Friend implements Serializable {
    private String username,status;

    public Friend(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
