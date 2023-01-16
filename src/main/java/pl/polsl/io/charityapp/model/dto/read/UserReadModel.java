package pl.polsl.io.charityapp.model.dto.read;

import lombok.Data;

@Data
public class UserReadModel {
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String role;
}