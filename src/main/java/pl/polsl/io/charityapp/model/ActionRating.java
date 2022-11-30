package pl.polsl.io.charityapp.model;

import javax.persistence.*;

@Entity
@Table(name = "action_rating")
public class ActionRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "benefactor_id")
    private User benefactorId;

    @ManyToOne
    @JoinColumn(name = "charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "rating")
    private Integer rating;
}
