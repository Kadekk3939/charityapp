package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(Long userId);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String email);
    List<User> findAll();
}
