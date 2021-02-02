package bg.softuni.mobilele.model.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginServiceModel {

    @NotNull
    @Size(min = 2)
    private String username;
    @NotNull
    @Size(min = 3)
    private String password;

    public UserLoginServiceModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
