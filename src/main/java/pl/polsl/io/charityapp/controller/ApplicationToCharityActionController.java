package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.ApplicationToCharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.ApplicationToCharityActionWriteModel;
import pl.polsl.io.charityapp.service.ApplicationToCharityActionService;
import pl.polsl.io.charityapp.service.DocumentService;
import pl.polsl.io.charityapp.utility.ApplicationStatus;

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

    // dodanie aplikacji bez dokumentów
    @PostMapping("/add")
    public ResponseEntity<Long> addApplicationWithoutDocuments(@RequestBody ApplicationToCharityActionWriteModel application) {
        Long applicationId = applicationToCharityActionService.addApplication(application);
        return new ResponseEntity<>(applicationId, HttpStatus.OK);
    }
    // wypisanie aplikacji bieżącego użytkownika
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationToCharityActionReadModel>> getAllUserApplications() {
        List<ApplicationToCharityActionReadModel> applicationToCharityActionReadModels = applicationToCharityActionService.getCurrentUserApplications();
        return new ResponseEntity<>(applicationToCharityActionReadModels, HttpStatus.OK);
    }

    @GetMapping("/{action}/{benefactor}")
    public ResponseEntity<ApplicationStatus> getUserApplicationStatus(@PathVariable String action, @PathVariable String benefactor) {
        return new ResponseEntity<>(applicationToCharityActionService.getUserApplication2Action(benefactor, action), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Long> getRandomApplication() {
        Long applicationId = applicationToCharityActionService.getRandomUncheckedOrPreviouslyChosenApplication();
        return new ResponseEntity<>(applicationId, HttpStatus.OK);
    }

    @GetMapping("/find/{applicationId}")
    public ResponseEntity<ApplicationToCharityActionReadModel> getApplicationByApplicationId(@PathVariable Long applicationId) {
        ApplicationToCharityActionReadModel app = applicationToCharityActionService.getApplicationById(applicationId);
        return new ResponseEntity<>(app, HttpStatus.OK);
    }

    @GetMapping("/process/{applicationId}/{verdict}")
    public ResponseEntity<String> processApplication(@PathVariable Long applicationId, @PathVariable String verdict) {
        return new ResponseEntity<>(applicationToCharityActionService.processVerdict(applicationId, verdict), HttpStatus.OK);
    }

}
