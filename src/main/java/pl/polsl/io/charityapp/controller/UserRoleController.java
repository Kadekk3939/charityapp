package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.io.charityapp.service.UserRoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/userRole")
public class UserRoleController {
    private final UserRoleService userRoleService;
    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PutMapping("/fill")
    public ResponseEntity<String> fillRoles() {
        userRoleService.addBasicRoles();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }




}
