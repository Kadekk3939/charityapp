package pl.polsl.io.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.model.entity.Donation;
import pl.polsl.io.charityapp.model.entity.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAll();


    List<Donation> findAllByDonorIdOrderByDonationIdDesc(User donorId);

    @Query("SELECT SUM(d.amount) from Donation as d where d.charityActionId = :charityActionId")
    Float getRaisedAmount(CharityAction charityActionId);


    @Query("SELECT new pl.polsl.io.charityapp.model.entity.Donation(" +
            "d.donorId, :charityActionId, SUM(d.amount)) " +
            "FROM Donation as d WHERE d.paymentConfirmed is true and d.anonymous is false and d.charityActionId = :charityActionId " +
            "GROUP BY d.donorId ORDER BY SUM(d.amount)")
    List<Donation> getGroupedDonationsByAction(CharityAction charityActionId);

    @Query("SELECT new pl.polsl.io.charityapp.model.entity.Donation(" +
            "d.donorId, d.charityActionId, SUM(d.amount)) " +
            "FROM Donation as d GROUP BY d.donorId, d.charityActionId")
    List<Donation> getAllDonations();
}
