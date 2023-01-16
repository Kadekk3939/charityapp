package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAll();

    List<Donation> findAllByDonorId(User donorId);

    List<Donation> findAllByCharityActionId(CharityAction charityActionId);

    List<Donation> findAllByCharityActionIdAndPaymentConfirmedTrueAndAnonymous(CharityAction charityActionId, Boolean anonymous);

}
