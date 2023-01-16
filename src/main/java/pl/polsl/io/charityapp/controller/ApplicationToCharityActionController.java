package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.service.ApplicationToCharityActionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/application2charity")
public class ApplicationToCharityActionController {

    private final ApplicationToCharityActionService applicationToCharityActionService;

    @Autowired
    public ApplicationToCharityActionController(ApplicationToCharityActionService applicationToCharityActionService) {
        this.applicationToCharityActionService = applicationToCharityActionService;
    }

    // dodanie aplikacji
    @PostMapping("/add")
    public ResponseEntity<ApplicationToCharityActionReadModel> addApplication(ApplicationToCharityActionWriteModel application) {
        ApplicationToCharityActionReadModel newApp = applicationToCharityActionService.addApplication(application);
        return new ResponseEntity<>(newApp, HttpStatus.OK);
    }

    // wypisanie aplikacji bieżącego użytkownika
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationToCharityActionReadModel>> getAllUserApplications() {
        //List<ApplicationToCharityActionReadModel> applicationToCharityActionReadModels =
        return null;
    }

}
