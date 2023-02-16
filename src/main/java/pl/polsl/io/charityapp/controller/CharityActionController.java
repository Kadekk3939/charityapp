package pl.polsl.io.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.charityapp.model.dto.read.CharityActionReadModel;
import pl.polsl.io.charityapp.model.dto.write.CharityActionWriteModel;
import pl.polsl.io.charityapp.model.entity.CharityAction;
import pl.polsl.io.charityapp.service.ActionWithDonationService;
import pl.polsl.io.charityapp.service.CharityActionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/action")
public class CharityActionController {
    private final CharityActionService charityActionService;

    private final ActionWithDonationService actionWithDonationService;
    @Autowired
    public CharityActionController(CharityActionService charityActionService, ActionWithDonationService actionWithDonationService) {
        this.charityActionService = charityActionService;
        this.actionWithDonationService = actionWithDonationService;
    }

    @PostMapping("/add")
    public ResponseEntity<CharityAction> addCharityAction(@RequestBody CharityActionWriteModel charityActionWriteModel){
        CharityAction newCharityAction = charityActionService.addCharityAction(charityActionWriteModel);
        return new ResponseEntity<>(newCharityAction, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<CharityActionReadModel>> getAllCharityActions(){
//        List<CharityActionReadModel> listOfCharityActions = actionWithDonationService.getAllActions();
        List<CharityActionReadModel> listOfCharityActions = actionWithDonationService.getOpenCharityActions();
        return new ResponseEntity<>(listOfCharityActions, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CharityActionReadModel> getCharityActionByName(@PathVariable String name) {
        CharityActionReadModel charityActionReadModel = actionWithDonationService.getCharityActionByName(name);
        return new ResponseEntity<>(charityActionReadModel, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CharityActionReadModel> getCharityActionById(@PathVariable Long id) {
        CharityActionReadModel charityActionReadModel = actionWithDonationService.getCharityActionById(id);
        return new ResponseEntity<>(charityActionReadModel, HttpStatus.OK);
    }

    @PatchMapping("/close/{name}")
    public ResponseEntity<Boolean> closeAction(@PathVariable String name) {
        Boolean result = charityActionService.closeAction(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
