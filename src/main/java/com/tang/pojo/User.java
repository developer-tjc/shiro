package com.tang.pojo;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Integer role_id;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public User(Integer id, String login, String password, Integer role_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role_id = role_id;
    }
}
