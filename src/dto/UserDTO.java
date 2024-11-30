package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private int id;
    private String login;
    private String email;
    private String password;
    private String repassword;

    public UserDTO(String login, String email, String password, String repassword) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

}