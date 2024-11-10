package dto;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String email;
    private String password;

    public User(int id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String login, String email, String password) {
        this.login = login.toLowerCase();// TODO (Про ник только с нижним регистром надо подумать...)
        this.email = email.toLowerCase();
        this.password = password;
    }

    public int getId() { return id; }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) { this.id = id; }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password);
    }
}
