package pl.polsl.io.charityapp.model.dto.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadModel {
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String role;
}