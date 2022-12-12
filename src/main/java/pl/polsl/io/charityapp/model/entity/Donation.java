package pl.polsl.io.charityapp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "donations")
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

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

}
