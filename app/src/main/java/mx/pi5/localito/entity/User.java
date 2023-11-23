package mx.pi5.localito.entity;

public class User {
    private int id;
    private String email;
    private String password;
    private String phone;
    private String name;
    private String last_name;

    private String avatar;

    public User(String email, String password, String phone, String name, String last_name) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.last_name = last_name;
    }
}
