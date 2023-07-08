package ru.masha238.strategy.form;


public class RegisterForm {
    private String login;
    private String password;

    private String username;

    public RegisterForm() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
