package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.io.charityapp.service.UserRoleService;
import pl.polsl.io.charityapp.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InitializationController {
    private final UserRoleService userRoleService;
    private final UserService userService;

    @Autowired
    public InitializationController(UserRoleService userRoleService, UserService userService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @PutMapping("/init")
    public ResponseEntity<String> initializeData() {
        // roles
        List<String> roles = new ArrayList<>(Arrays.asList("Donor", "Benefactor", "Worker"));
        userRoleService.addBasicRoles(roles);
        // users
        userService.addBasicUsers(roles);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }




}
