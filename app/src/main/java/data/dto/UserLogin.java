package data.dto;

import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class UserLogin {

    private String username;
    private String password;
    private String token;

    public UserLogin() {

    }

    public UserLogin(String password, String username) {
        this.password = password;
        this.username = username;
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
