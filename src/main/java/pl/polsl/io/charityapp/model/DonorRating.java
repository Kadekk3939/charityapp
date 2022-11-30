package pl.polsl.io.charityapp.model;

import javax.persistence.*;

public class DonorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private User donorId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employeeId;

    @ManyToOne
    @JoinColumn(name = "charity_action_id")
    private CharityAction charityActionId;



}
