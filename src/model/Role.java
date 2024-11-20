package model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
