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
import pl.polsl.io.charityapp.utility.FileManager;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/application2charity")
public class ApplicationToCharityActionController {

    private final ApplicationToCharityActionService applicationToCharityActionService;

    private final DocumentService documentService;

    @Autowired
    public ApplicationToCharityActionController(ApplicationToCharityActionService applicationToCharityActionService, DocumentService documentService) {
        this.applicationToCharityActionService = applicationToCharityActionService;
        this.documentService = documentService;
    }

    // dodanie aplikacji
    @PostMapping("/add")
    public ResponseEntity<ApplicationToCharityActionReadModel> addApplication(@RequestBody ApplicationToCharityActionWriteModel application) {
        Long applicationId = applicationToCharityActionService.addApplication(application);


        //List<String> documents = FileManager.uploadFiles()
        // DocWriteModel -> MultipartFile?

        ApplicationToCharityActionReadModel newApp = applicationToCharityActionService.addDocumentsToApplication();

        return new ResponseEntity<>(newApp, HttpStatus.OK);
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

}
