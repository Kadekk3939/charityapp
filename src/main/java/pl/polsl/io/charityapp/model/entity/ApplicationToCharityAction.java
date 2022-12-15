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
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="benefactor_id")
    private User benefactorId;

    @ManyToOne
    @JoinColumn(name="charity_action_id")
    private CharityAction charityActionId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private ApplicationStatus status;

//    @ManyToOne
//    @JoinColumn(name = "document_id")
//    private List<Document> documents;
}


