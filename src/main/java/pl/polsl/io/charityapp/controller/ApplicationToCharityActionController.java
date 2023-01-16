package pl.polsl.io.charityapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/application2charity")
public class ApplicationToCharityActionController {

    // dodanie aplikacji
    @PostMapping("/add")
    public ResponseEntity<ApplicationToCharityActionReadModel> addApplication(ApplicationToCharityActionWriteModel application) {

    }

    // wypisanie aplikacji bieżącego użytkownika
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationToCharityActionReadModel>> getAllUserApplications() {

    }

}
