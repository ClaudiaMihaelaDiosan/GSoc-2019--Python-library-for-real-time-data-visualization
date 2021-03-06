package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Models;


import java.io.Serializable;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Enums.UserType;

public class Account implements Serializable {

    private String id;
    private String password;
    private String type;
    private String token;
    private String email;

    public Account(){
    }

    public Account(String id, String password, String type, String email) {
        this.id = id;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type.getText();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}

