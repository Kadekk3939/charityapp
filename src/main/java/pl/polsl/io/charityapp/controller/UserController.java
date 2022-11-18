package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.io.charityapp.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/user")
    public String printInfo() {
        return "Info";
    }
}
