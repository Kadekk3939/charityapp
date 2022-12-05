package pl.polsl.io.charityapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Login must not be blank")
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank(message = "Password must not be blank")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email must not be blank")
    @Column(name = "email", unique = true)
    private String email;

    //Commented until working
//    @Column(name = "nip")
//    private String nip;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private UserRole roleId;
}
