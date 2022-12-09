package pl.polsl.io.charityapp.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "charity_actions")
@Data
public class CharityAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "goal")
    private Float goal;

    @Column(name = "end_time")
    private Date endTime;
}
