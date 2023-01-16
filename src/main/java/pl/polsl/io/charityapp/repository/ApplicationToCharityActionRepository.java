package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationToCharityActionRepository extends JpaRepository<ApplicationToCharityAction, Long> {
    List<ApplicationToCharityAction> findAll();

    List<ApplicationToCharityAction> findAllByBenefactorId(User benefactorId);

    Optional<ApplicationToCharityAction> findApplicationToCharityActionByBenefactorIdAndCharityActionId(User benefactorId, CharityAction charityActionId);
}
