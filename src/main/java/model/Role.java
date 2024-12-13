package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Role {

    private int id;
    private int user_id;
    private String role;

    public Role(int user_id, String role) {
        this.user_id = user_id;
        this.role = role;
    }

}
