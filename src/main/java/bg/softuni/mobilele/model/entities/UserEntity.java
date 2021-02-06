package bg.softuni.mobilele.model.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private boolean isActive;
  private String imageUrl;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<UserRoleEntity> userRoles;

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public UserEntity setActive(boolean active) {
    isActive = active;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public UserEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public List<UserRoleEntity> getUserRoles() {
    return userRoles;
  }

  public UserEntity setUserRoles(
          List<UserRoleEntity> userRoles) {
    this.userRoles = userRoles;
    return this;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
            "username='" + username + '\'' +
            ", password='N/A" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", isActive=" + isActive +
            ", imageUrl='" + imageUrl + '\'' +
            ", userRoles=" + userRoles +
            '}';
  }
}
