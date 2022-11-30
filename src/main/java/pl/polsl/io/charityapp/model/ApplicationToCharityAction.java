package pl.polsl.io.charityapp.model;

        import lombok.Data;

        import javax.persistence.*;

@Entity
@Table(name = "application_to_charity_action")
public class ApplicationToCharityAction implements java.io.Serializable{

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
}


