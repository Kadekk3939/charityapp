package pl.polsl.io.charityapp.model.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "action_ratings")
public class ActionRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "action_rating_id")
    private Long actionRatingId;

    @ManyToOne
    @JoinColumn(name = "benefactor_id")
    private User benefactorId;

    @ManyToOne
    @JoinColumn(name = "charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;
}
