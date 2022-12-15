package pl.polsl.io.charityapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "charity_actions")
@Data
public class CharityAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "goal")
    private Float goal;

    @Column(name = "end_time")
    private Date endDate;
}
