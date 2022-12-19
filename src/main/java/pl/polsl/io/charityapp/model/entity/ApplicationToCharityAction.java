package pl.polsl.io.charityapp.model.entity;

import lombok.Data;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "applications_to_charity_action")
public class ApplicationToCharityAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name="benefactor_id")
    private User benefactorId;

    @ManyToOne
    @JoinColumn(name="charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @OneToMany
    private List<Document> documents;
}


