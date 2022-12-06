package pl.polsl.io.charityapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.io.charityapp.repository.UserRepository;
import pl.polsl.io.charityapp.service.UserService;
import pl.polsl.io.charityapp.utility.CurrentUserData;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    public LoginController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        if (CurrentUserData.isAnyoneLogged()) {
            return String.format("Welcome %s\r\n with id:%d!\r\nYou are no one.",
                    CurrentUserData.getCurrentUserLogin()
                    ,CurrentUserData.getCurrentUserId()
//                ,CurrentUserData.getCurrentUserRole()
            );
        }
        return "Identify yourself!\n";
    }
}
