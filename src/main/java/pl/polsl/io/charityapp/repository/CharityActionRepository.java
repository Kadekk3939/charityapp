package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.CharityAction;

import java.util.List;
import java.util.Optional;

public interface CharityActionRepository extends JpaRepository<CharityAction, Long> {
    List<CharityAction> findAll();

    Optional<CharityAction> findByName(String name);
}

