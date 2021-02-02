package bg.softuni.mobilele.security;

import bg.softuni.mobilele.model.entities.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private static final String ANONYMOUS = "Anonymous";
    private String name = ANONYMOUS;
    private boolean isAnonymous = true;
    private final List<UserRoleEnum> userRoles = new ArrayList<>();

    public static String getANONYMOUS() {
        return ANONYMOUS;
    }

    public CurrentUser setRoles(List<UserRoleEnum> roles){
        userRoles.clear();
        userRoles.addAll(roles);
        return this;
    }

    public boolean isAdmin(){
        return userRoles.contains(UserRoleEnum.ADMIN);
    }

    public boolean isLoggedIn(){
        return !isAnonymous();
    }

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public CurrentUser setAnonymous(boolean anonymous) {
        if (anonymous){
            this.name = ANONYMOUS;
            this.userRoles.clear();
        }
        isAnonymous = anonymous;
        return this;
    }
}
