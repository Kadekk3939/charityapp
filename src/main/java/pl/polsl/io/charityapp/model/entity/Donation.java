package pl.polsl.io.charityapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "donations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donation_id")
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private User donorId;

    @ManyToOne
    @JoinColumn(name = "charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "payment_confirmed")
    private Boolean paymentConfirmed;

    @Column(name = "anonymous")
    private Boolean anonymous;

    // constructor for DonationRepository
    public Donation(User donorId, double amount) {
        this.donorId = donorId;
        this.amount = (float) amount;
        this.anonymous = false;
        this.paymentConfirmed = true;
    }

}
