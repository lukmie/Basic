package entity;

public class User {
    private Integer id;
    private String login;
    private String password;
    public static final String USER_SEPARATOR = "#";

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return id + USER_SEPARATOR + login + USER_SEPARATOR + password;
    }
}
