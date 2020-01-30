package ingsw.bullet.client.NetworkUtility;

public class LoginInfo {
    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
