package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {

    private int id;
    private int user_id;
    private String role;

    public Role(int user_id, String role) {
        this.user_id = user_id;
        this.role = role;
    }

    public Role(int id, int user_id, String role) {
        this.id = id;
        this.user_id = user_id;
        this.role = role;
    }

}
