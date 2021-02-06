package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.services.RegisterServiceModel;

public interface UserService {
    boolean authenticate(String name, String password);

    void loginUser(String username);

    void logoutCurrentUser();

    void registerUser(RegisterServiceModel RegisterServiceModel);
}
