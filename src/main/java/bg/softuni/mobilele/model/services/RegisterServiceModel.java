package bg.softuni.mobilele.model.services;

import bg.softuni.mobilele.model.entities.UserRoleEntity;
import bg.softuni.mobilele.model.entities.enums.UserRoleEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RegisterServiceModel {
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String password;

//    @NotNull
//    @NotEmpty
//    private List<UserRoleEnum> role;


    public RegisterServiceModel() {
    }

//    public List<UserRoleEnum> getRoles() {
//        return role;
//    }
//
//    public void setRoles(List<UserRoleEnum> role) {
//        this.role = role;
//    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
