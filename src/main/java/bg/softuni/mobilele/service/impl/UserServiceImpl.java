package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entities.UserEntity;
import bg.softuni.mobilele.model.entities.UserRoleEntity;
import bg.softuni.mobilele.model.entities.enums.UserRoleEnum;
import bg.softuni.mobilele.model.services.RegisterServiceModel;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import bg.softuni.mobilele.security.CurrentUser;
import bg.softuni.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.mobilele.DBInit.setCurrentTimestamps;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, CurrentUser currentUser, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean authenticate(String name, String password) {
        Optional<UserEntity> userEntity = userRepository.findAllByUsername(name);

        if (userEntity.isEmpty()){
            return false;
        }
        else {
            return passwordEncoder.matches(password, userEntity.get().getPassword());
        }
    }

    @Override
    public void loginUser(String username) {

        UserEntity user = this.userRepository.findAllByUsername(username).orElseThrow();

        List<UserRoleEnum> userRoles = user
                .getUserRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .collect(Collectors.toList());

        currentUser
                .setAnonymous(false)
                .setName(username)
                .setRoles(userRoles);
    }

    @Override
    public void logoutCurrentUser() {
        currentUser.setAnonymous(true);
    }

    @Override
    public void registerUser(RegisterServiceModel registerServiceModel) {
        UserEntity user = new UserEntity();

        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        userRoleRepository.saveAll(List.of(adminRole, userRole));

        user
                .setFirstName(registerServiceModel.getFirstName())
                .setLastName(registerServiceModel.getLastName())
                .setUsername(registerServiceModel.getUsername())
                .setUserRoles(Set.of(adminRole, userRole))
                .setPassword(passwordEncoder.encode(registerServiceModel.getPassword()));
        setCurrentTimestamps(user);

        userRepository.save(user);
    }
}
