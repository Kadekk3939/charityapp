package pl.polsl.io.charityapp.model.dto.write;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserWriteModel  {

    private String firstName;
    private String lastName;

    @NotBlank(message = "Login must not be blank")
    private String login;

    @NotBlank(message = "Password must not be blank")
    private String password;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Role must not be blank")
    private String role;

}
