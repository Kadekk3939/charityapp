package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.dto.write.UserWriteModel;
import pl.polsl.io.charityapp.service.CharityActionService;
import pl.polsl.io.charityapp.service.UserRoleService;
import pl.polsl.io.charityapp.service.UserService;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InitializationController {
    private final UserRoleService userRoleService;
    private final UserService userService;

    private final CharityActionService charityActionService;
    @Autowired
    public InitializationController(UserRoleService userRoleService, UserService userService, CharityActionService charityActionService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.charityActionService = charityActionService;
    }

    @PutMapping("/init")
    public ResponseEntity<String> initializeData() {
        // roles
        List<String> roles = new ArrayList<>(Arrays.asList("Donor", "Benefactor", "Worker"));
        userRoleService.addBasicRoles(roles);

        // users
        userService.addBasicUsers(roles);
        UserWriteModel userWriteModel = new UserWriteModel("Jan", "Kowalski", "admin", "admin", "jk@polsl.pl", "Worker");
        userService.addUser(userWriteModel);
        userWriteModel = new UserWriteModel("Maja", "Nowak", "Majka", "123", "majka@gmail.com", "Benefactor");
        userService.addUser(userWriteModel);
        userWriteModel = new UserWriteModel("Adam", "Kowalczyk", "Kowal", "123", "adamczyk@wp.pl", "Donor");
        userService.addUser(userWriteModel);

        // charity actions
        CharityActionWriteModel charityActionWriteModel = new CharityActionWriteModel("Schronisko", "Zbiórka pieniędzy na karmę do schroniska", 1000.0F, Date.valueOf("2024-04-01"));
        charityActionService.addCharityAction(charityActionWriteModel);
        charityActionWriteModel = new CharityActionWriteModel("Dom dziecka", "Na książki do domu dziecka", 1234.56F, Date.valueOf("2024-06-30"));
        charityActionService.addCharityAction(charityActionWriteModel);
        charityActionWriteModel = new CharityActionWriteModel("Szpital", "Na kupno aparatury medycznej", 15267.55F, Date.valueOf("2024-12-06"));
        charityActionService.addCharityAction(charityActionWriteModel);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }




}
