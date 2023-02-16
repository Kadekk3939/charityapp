package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.io.charityapp.model.entity.ActionRating;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;

public interface ActionRatingRepository extends JpaRepository<ActionRating, Long> {

    List<ActionRating> findAllByBenefactorId(User benefactorId);
}
