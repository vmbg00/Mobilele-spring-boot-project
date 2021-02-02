package bg.softuni.mobilele.service;

public interface UserService {
    boolean authenticate(String name, String password);

    void loginUser(String username);

    void logoutCurrentUser();
}
