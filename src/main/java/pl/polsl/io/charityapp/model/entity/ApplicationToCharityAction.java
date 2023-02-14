package pl.polsl.io.charityapp.model.entity;

import lombok.Data;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @NotBlank(message = "Reason must not be blank")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @OneToMany
    private List<Document> documents = new ArrayList<>();

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}


