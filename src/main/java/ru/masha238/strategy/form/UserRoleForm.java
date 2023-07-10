package ru.masha238.strategy.form;


public class UserRoleForm {
    private String login;
    private String role;


    public UserRoleForm() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }
}
