package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
