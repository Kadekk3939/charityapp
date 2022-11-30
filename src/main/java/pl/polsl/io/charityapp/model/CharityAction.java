package pl.polsl.io.charityapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "charity_action")
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
}
