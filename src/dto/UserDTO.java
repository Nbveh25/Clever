package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String login;
    private String email;
    private String password;
    private String repassword;

    public UserDTO(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

}