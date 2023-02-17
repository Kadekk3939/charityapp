package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.DonorRating;
import pl.polsl.io.charityapp.model.entity.User;

public interface DonorRatingRepository extends JpaRepository<DonorRating, Long> {
    boolean existsByDonorIdAndCharityActionId(User donorId, CharityAction charityActionId);
}
