package pl.polsl.io.charityapp.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "donor_ratings")
@Data
public class DonorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donor_rating_id")
    private Long donorRatingId;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private User donorId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employeeId;

    @ManyToOne
    @JoinColumn(name = "charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "rating")
    private boolean rating;

}
