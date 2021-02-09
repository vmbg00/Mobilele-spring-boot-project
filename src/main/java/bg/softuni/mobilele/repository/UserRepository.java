package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entities.UserEntity;
import bg.softuni.mobilele.security.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findAllByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
