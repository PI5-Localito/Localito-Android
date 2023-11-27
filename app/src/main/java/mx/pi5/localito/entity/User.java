package mx.pi5.localito.entity;

public class User {
    public int id;
    public String email;
    public String password;
    public String phone;
    public String name;
    public String last_name;

    public String avatar;

    public User(String email, String password, String phone, String name, String last_name) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.last_name = last_name;
    }
}
