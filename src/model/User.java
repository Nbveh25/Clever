package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private int id;
    private String login;
    private String email;
    private String password;

    public User(String login, String email, String password) {
        this.login = login.toLowerCase();// TODO (Про ник только с нижним регистром надо подумать...)
        this.email = email.toLowerCase();
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
