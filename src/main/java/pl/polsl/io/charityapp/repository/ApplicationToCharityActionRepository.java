package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.ApplicationToCharityAction;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import java.util.List;
import java.util.Optional;

public interface ApplicationToCharityActionRepository extends JpaRepository<ApplicationToCharityAction, Long> {
    List<ApplicationToCharityAction> findAll();

    List<ApplicationToCharityAction> findAllByBenefactorId(User benefactorId);
    List<ApplicationToCharityAction> findAllByBenefactorIdAndStatus(User benefactorId, ApplicationStatus status);

    List<ApplicationToCharityAction> findAllByStatusAndVerifiedByIsNull(ApplicationStatus applicationStatus);

    Optional<ApplicationToCharityAction> findFirstByBenefactorIdAndCharityActionIdOrderByLastUpdatedDesc(User benefactor, CharityAction charityAction);

    Optional<ApplicationToCharityAction> findApplicationToCharityActionByBenefactorIdAndCharityActionId(User benefactorId, CharityAction charityActionId);

    Optional<ApplicationToCharityAction> findByVerifiedByAndStatus(User verifiedBy, ApplicationStatus status);
}
