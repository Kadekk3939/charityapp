package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;

import java.util.List;
import java.util.Optional;

public interface ApplicationToCharityActionRepository extends JpaRepository<ApplicationToCharityAction, Long> {
    List<ApplicationToCharityAction> findAll();

    Optional<ApplicationToCharityAction> findApplicationToCharityActionByBenefactorIdAndCharityActionId(Long benefactorId, Long charityActionId);
}
